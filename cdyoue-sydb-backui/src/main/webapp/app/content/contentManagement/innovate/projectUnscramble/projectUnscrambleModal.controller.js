(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('ProjectUnscrambleModalController', ProjectUnscrambleModalController);

    ProjectUnscrambleModalController.$inject = ['$scope','$timeout', '$uibModalInstance','items','InnovateService','Upload','toaster'];

    function ProjectUnscrambleModalController($scope,$timeout, $uibModalInstance,items,InnovateService,Upload,toaster) {
        var vm = this;
        vm.cancel=cancel; // 关闭
        vm.editor=editor; // 编辑
        vm.save=save; // 保存

        vm.items = items;
        vm.updateItems={};
        vm.syBannerDetail={};
        vm.queryProjectUnscrambleModel = {
            q: null
        };
        vm.dataImage = {file: null};

        initSyBanner();
        function initSyBanner() {
            // 是编辑还是查看
            if(items.showModel == "show"){
                vm.isEditor = false;
            }else{
                vm.isEditor = true;
            }
            if (items.id==null){
                vm.ProjectUnscrambleDetail={};
                vm.updateItems={};
            } else {
                InnovateService.getProjectUnscrambleDetail({id:items.id},function (rel) {
                    if (rel.status == 'SUCCESS'){
                        vm.ProjectUnscrambleDetail = rel.response;
                        vm.updateItems = angular.copy(vm.ProjectUnscrambleDetail);
                        $scope.ueditorSetContent("newsAddContainer",vm.updateItems.content);
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
            if(vm.updateItems.content == ''|| vm.updateItems.content == null){
                vm.editShow=true;
                return;
            }
            if (items.showModel == "editor"){
                InnovateService.updateProjectUnscramble({'id':items.id},vm.updateItems, function success(obj) {
                    if (obj.status == 'SUCCESS') {
                        toaster.clear();
                        toaster.pop('success', obj.message);
                        cancel();
                    }
                });
            } else if(items.showModel == "add"){
                InnovateService.createProjectUnscramble(vm.updateItems, function success(obj) {
                    if (obj.status == 'SUCCESS') {
                        toaster.clear();
                        toaster.pop('success', obj.message);
                        cancel();
                    }
                });
            } else {
                return;
            }
        }

        function editor() {
            vm.isEditor = true;
            items.showModel = "editor";
        }
        function cancel() {
            $uibModalInstance.dismiss('cancel');
        }

    }
})();