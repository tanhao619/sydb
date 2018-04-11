(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('ApplyEquipmentModalController', ApplyEquipmentModalController);

    ApplyEquipmentModalController.$inject = ['$timeout', '$uibModalInstance','items','ConsultationService','Upload'];

    function ApplyEquipmentModalController($timeout, $uibModalInstance,items,ConsultationService,Upload) {
        var vm = this;
        vm.applyMessage={};
        vm.getEquipmentApply = getEquipmentApplyById();
        vm.cancel = cancel;

        function getEquipmentApplyById() {
            ConsultationService.getEquipmentApplyById({id:items},function (rel) {
                vm.applyMessage = rel.response;
            },function (err) {

            })
        }

        function cancel() {
            $uibModalInstance.dismiss('cancel');
        }

    }
})();