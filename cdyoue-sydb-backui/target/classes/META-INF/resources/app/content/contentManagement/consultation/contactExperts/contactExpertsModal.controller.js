(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('ContactExpertsModalController', ContactExpertsModalController);

    ContactExpertsModalController.$inject = ['$timeout', '$uibModalInstance','items','ConsultationService','Upload'];

    function ContactExpertsModalController($timeout, $uibModalInstance,items,ConsultationService,Upload) {
        var vm = this;
        vm.cancel = cancel; // 关闭

        vm.items = items;
        vm.syBannerDetail={};

        initSyBanner();
        function initSyBanner() {
            vm.syBannerDetail = vm.items.detail;
        }

        function cancel () {
            $uibModalInstance.dismiss('cancel');
        }

    }
})();