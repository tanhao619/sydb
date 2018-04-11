(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('UnscrambleModalController', UnscrambleModalController);

    UnscrambleModalController.$inject = ['$scope', '$uibModalInstance', 'items', 'InnovateService', 'toaster'];

    function UnscrambleModalController($scope, $uibModalInstance, items, InnovateService, toaster) {
        var vm = this;

        vm.cancel = cancel; // 关闭
        vm.editor = editor; // 编辑
        vm.save = save; // 保存

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
                InnovateService.getUnscramble({id:vm.items.id},function (rel) {
                    if (rel.status == 'SUCCESS'){
                        vm.syBannerDetail = rel.response;
                        vm.updateItems = angular.copy(vm.syBannerDetail);
                        $scope.ueditorSetContent("newsAddContainer",vm.updateItems.content);
                    }else {
                        toaster.clear();
                        toaster.pop('error', rel.message+":"+rel.description);
                        $uibModalInstance.dismiss("cancel");
                    }
                });
            }
            initExpert();
        }

        function initExpert() {
            InnovateService.getExpertSelect(vm.queryExpertModel,function (rel) {
                if (rel.status == 'SUCCESS'){
                    vm.updateItems.expertSelect = [{id:0,name:''}];
                    rel.response.forEach(function(item,index,array){
                        vm.updateItems.expertSelect.push(item);
                    });
                }else {
                    //console.log(rel.message);
                }
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
                InnovateService.updUnscramble({'id':vm.syBannerDetail.id},vm.updateItems, function success(obj) {
                    if (obj.status == 'SUCCESS') {
                        toaster.clear();
                        toaster.pop('success', obj.message);
                        cancel();
                    } else {
                        toaster.clear();
                        toaster.pop('error', obj.message+":"+obj.description);
                    }
                });
            } else if(vm.items.showModel == "add"){
                InnovateService.saveUnscramble(vm.updateItems, function success(obj) {
                    if (obj.status == 'SUCCESS') {
                        toaster.clear();
                        toaster.pop('success', obj.message);
                        cancel();
                    } else {
                        toaster.clear();
                        toaster.pop('error', obj.message+":"+obj.description);
                    }
                });
            }
        }

        function editor () {
            vm.isEditor = true;
            vm.items.showModel = "editor";
            $scope.ueditorSetContent("newsAddContainer",vm.updateItems.content);
        }

        function cancel () {
            $uibModalInstance.dismiss('cancel');
        }

    }
})();