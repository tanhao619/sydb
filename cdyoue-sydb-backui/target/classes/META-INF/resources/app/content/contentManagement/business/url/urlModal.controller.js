(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('UrlModalController', UrlModalController);

    UrlModalController.$inject = ['toaster', '$uibModalInstance','items','BusinessService'];

    function UrlModalController(toaster, $uibModalInstance,items,BusinessService) {
        var vm = this;

        vm.cancel = cancel; // 关闭
        vm.editor = editor; // 编辑
        vm.save = save; // 保存

        vm.items = items;
        vm.updateItems={};
        vm.syBannerDetail={};

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
                vm.syBannerDetail = vm.items.detail;
                vm.updateItems = angular.copy(vm.syBannerDetail);
            }
            vm.updateItems.contentReg="<a href=\"http://www.baidu.com\" target=\"_blank\">百度5</a>";
        }

        function save () {
            if (vm.items.showModel == "editor"){
                BusinessService.updUrl({'id':vm.syBannerDetail.id},vm.updateItems, function success(obj) {
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
                BusinessService.saveUrl(vm.updateItems, function success(obj) {
                    if (obj.status == 'SUCCESS') {
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

    }
})();