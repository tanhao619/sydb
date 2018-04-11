(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('ApplyTutorsModalController', ApplyTutorsModalController);

    ApplyTutorsModalController.$inject = ['toaster', '$uibModalInstance','items','ConsultationService'];

    function ApplyTutorsModalController(toaster, $uibModalInstance,items,ConsultationService) {
        var vm = this;

        vm.cancel = cancel; // 关闭

        vm.items = items;
        vm.syBannerDetail={};

        initSyBanner();
        function initSyBanner() {
            ConsultationService.getApplyTutor({"id":vm.items.id}, function (obj) {
                if (obj.code == 20) {
                    vm.syBannerDetail = obj.response;
                    vm.syBannerDetail.createEnter = vm.items.createEnter;
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