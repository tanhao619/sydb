(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('AdReleaseModalController', AdReleaseModalController);

    AdReleaseModalController.$inject = ['SYDB','$scope','$timeout', '$uibModalInstance','items','BusinessService','Upload','toaster'];

    function AdReleaseModalController(SYDB,$scope,$timeout, $uibModalInstance,items,BusinessService,Upload,toaster) {
        var vm = this;
        vm.cancel=cancel; // 关闭
        vm.editor=editor; // 编辑
        vm.save=save; // 保存
        vm.uploadImage = uploadImage; // 上传图片

        vm.items = items;
        vm.updateItems={};
        vm.dataImage = {file: null};

        vm.selectLocation =  {
            province:'北京市',
            city:'东城区',
            address:'东街28号'
        };
        // 初始化城市创建
        $timeout(function() {
            $('#locations').citys({
                required: true,
                onChange: function (data) {
                    console.log(data);
                    vm.selectLocation =  {
                        province:data.province,
                        city:data.city
                    };
                }
            });
        },5);

        initSyBanner();
        function initSyBanner() {
            // 是编辑还是查看
            if(items.showModel == "show"){
                vm.isEditor = false;
            }else{
                vm.isEditor = true;

            }
            if (items.id==null){
                vm.EnterpriseDetail={};
                vm.updateItems={};
            } else {
                BusinessService.getEnterpriseDetail({id:items.id},function (rel) {
                    if (rel.status == 'SUCCESS'){
                        vm.EnterpriseDetail = rel.response;
                        vm.updateItems = angular.copy(vm.EnterpriseDetail);
                        $scope.ueditorSetContent("newsAddContainer",vm.updateItems.content);
                        if(rel.response.address != null && rel.response.address != ''){
                            vm.selectLocation = angular.fromJson(rel.response.address);
                            vm.selectLocation.address = angular.fromJson(rel.response.address).address;
                        }
                        // 城市插件回显
                        $timeout(function(){
                            var address = JSON.parse(rel.response.address);
                            $("#locations").citys({
                                province:address.province,
                                city:address.city
                            });
                        },14)
                    }else {
                        toaster.clear();
                        toaster.pop('success', rel.message);
                        $uibModalInstance.dismiss("cancel");
                    }
                },function (err) {
                    console.log(err);
                });
            }

        }
        vm.editShow=false;
        function save() {
            vm.updateItems.content = $scope.ueditorGetContent("newsAddContainer");
            if(vm.updateItems.content =="" || vm.updateItems.content == null){
                vm.editShow=true;
                return;
            }
            vm.updateItems.address=angular.toJson(vm.selectLocation);
            if (items.showModel == "editor"){
                BusinessService.updateEnterprise({'id':items.id},vm.updateItems, function success(obj) {
                    if (obj.status == 'SUCCESS') {
                        toaster.clear();
                        toaster.pop('success', obj.message);
                        cancel();
                    }
                });
            } else if(items.showModel == "add"){
                BusinessService.createEnterprise(vm.updateItems, function success(obj) {
                    if (obj.status == 'SUCCESS') {
                        toaster.clear();
                        toaster.pop('success', obj.message);
                        cancel();
                    }
                });
            }
        }
        function editor() {
            vm.isEditor = true;
            items.showModel = "editor";
        }
        function cancel() {
            $uibModalInstance.dismiss('cancel');
        }
        function uploadImage() {
            console.log(vm.items);
            var url = SYDB+'/common/upload';
            var data = angular.copy(vm.dataImage || {});
            data.file = vm.dataImage.file;
            //限制只能上传图片
            if((data.file.type.substring(0,data.file.type.indexOf('/'))) !== "image"){
                toaster.clear();
                toaster.pop("warning", "上传文件格式不对!");
                return;
            }
            Upload.upload({
                url: url,
                data: data
            }).success(function (data) {
                vm.updateItems.logo = data.response.url;
                toaster.clear();
                toaster.pop("info", "上传成功!");
            });
        }
    }
})();