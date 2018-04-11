(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('ApplyBaseModalController', ApplyBaseModalController);

    ApplyBaseModalController.$inject = ['toaster', '$uibModalInstance','items','ConsultationService'];

    function ApplyBaseModalController(toaster, $uibModalInstance,items,ConsultationService) {
        var vm = this;

        vm.cancel = cancel; // 关闭

        vm.items = items;
        vm.syBannerDetail={};

        initSyBanner();
        function initSyBanner() {
            ConsultationService.getApplyBase({"id":vm.items.id}, function (obj) {
                if (obj.code == 20) {
                    vm.syBannerDetail = obj.response;
                } else {
                    toaster.clear();
                    toaster.pop('error', obj.message+":"+obj.description);
                    cancel();
                }
            })
        }

        function cancel () {
            $uibModalInstance.dismiss('cancel');
        }
    }
})();