(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('FruitsModalController', FruitsModalController);

    FruitsModalController.$inject = ['$scope', '$uibModalInstance','items','InnovateService','Upload','SYDB','toaster'];

    function FruitsModalController($scope, $uibModalInstance,items,InnovateService,Upload,SYDB,toaster) {
        var vm = this;

        vm.cancel = cancel; // 关闭
        vm.editor = editor; // 编辑
        vm.save = save; // 保存
        vm.uploadImage = uploadImage; // 上传图片

        vm.items = items;
        vm.updateItems={};
        vm.syBannerDetail={};
        vm.queryExpertModel = {
            q: null
        };
        vm.dataImage = {file: null};
        vm.editShow = false;

        initSyBanner();
        function initSyBanner() {
            // 是编辑还是查看
            if(vm.items.showModel == "show"){
                vm.isEditor = false;
            }else{
                vm.isEditor = true;
            }
            if (vm.items.id==null){
                vm.syBannerDetail={};
                vm.updateItems={};
            } else {
                InnovateService.getFruit({id:vm.items.id},function (rel) {
                    if (rel.code == 20){
                        vm.syBannerDetail = rel.response;
                        vm.updateItems = angular.copy(vm.syBannerDetail);
                        $scope.ueditorSetContent("newsAddContainer",vm.updateItems.content);
                    }else {
                        toaster.clear();
                        toaster.pop('error', rel.message+":"+rel.description);
                        $uibModalInstance.dismiss("cancel");
                    }
                },function (err) {
                    //console.log(err);
                });
            }
            initExpert();
        }
        
        function initExpert() {
            InnovateService.getExpertSelect(vm.queryExpertModel,function (rel) {
                if (rel.code == 20){
                    vm.updateItems.expertSelect = [{id:0,name:''}];
                    rel.response.forEach(function(item,index,array){
                        vm.updateItems.expertSelect.push(item);
                    });
                }else {
                    //console.log(rel);
                }
            },function (err) {
                //console.log(err);
            });
        }

        function save () {
            vm.updateItems.content = $scope.ueditorGetContent("newsAddContainer");
            if($scope.ueditorGetContentTxt("newsAddContainer").trim() === ""){
                vm.editShow = true;
                return;
            } else {
                vm.editShow = false;
            }
            if (vm.items.showModel == "editor"){
                InnovateService.updateFruit({'id':vm.syBannerDetail.id},vm.updateItems, function success(obj) {
                    if (obj.code == 20) {
                        toaster.clear();
                        toaster.pop('success', obj.message);
                        cancel();
                    } else {
                        toaster.clear();
                        toaster.pop('error', obj.message+":"+obj.description);
                    }
                });
            } else if(vm.items.showModel == "add"){
                InnovateService.saveFruit(vm.updateItems, function success(obj) {
                    if (obj.code == 20) {
                        toaster.clear();
                        toaster.pop('success', obj.message);
                        cancel();
                    } else {
                        toaster.clear();
                        toaster.pop('error', obj.message+":"+obj.description);
                    }
                });
            } else {
                return;
            }
        }

        function editor () {
            vm.isEditor = true;
            vm.items.showModel = "editor";
        }
        function cancel () {
            $uibModalInstance.dismiss('cancel');
        }

        function uploadImage() {
            var url = SYDB+'/common/upload';
            var data = angular.copy(vm.dataImage || {});
            data.file = vm.dataImage.file;
            if (data.file.type.substring(0, data.file.type.indexOf('/')) !== 'image') {
                toaster.clear();
                toaster.pop('warning', '上传文件格式不对！');
                return;
            }
            Upload.upload({
                url: url,
                data: data
            }).success(function (data) {
                vm.updateItems.coverImgUrl = data.response.url;
                toaster.clear();
                toaster.pop('info', '上传图片成功');
            }).error(function () {
                toaster.clear();
                toaster.pop('warning', '上传图片失败！');
            });
        }

    }
})();