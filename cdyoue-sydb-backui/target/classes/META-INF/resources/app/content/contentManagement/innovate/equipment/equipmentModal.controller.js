(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('EquipmentModalController', EquipmentModalController);

    EquipmentModalController.$inject = ['$timeout','$scope', '$uibModalInstance','items','InnovateService','Upload'];

    function EquipmentModalController($timeout,$scope, $uibModalInstance,items,InnovateService,Upload) {
        var vm = this;
        vm.id = items;
        vm.equipment = {};
        vm.getMessage = getMessage();
        vm.cancel = cancel;
        vm.industryList = "";
        vm.selected=""

        getMessage();
        function getMessage() {
            InnovateService.getEquipmentById({id:vm.id},function (rel) {
                vm.equipment = rel.response;
            })
        }

        getIndustrySelect();
        function getIndustrySelect() {
            InnovateService.getIndustrySelect({type:1},function (data) {
                vm.industryList = data.response;
                vm.selected = vm.equipment
            })
        }
        
        function cancel() {
            $uibModalInstance.close('cancel');
        }
    }
})();