(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('TechnologyFinancingModalController', TechnologyFinancingModalController);

    TechnologyFinancingModalController.$inject = ['$timeout', '$uibModalInstance','items','InnovateService','Upload'];

    function TechnologyFinancingModalController($timeout, $uibModalInstance,items,InnovateService,Upload) {
        var vm = this;
        vm.financingMessage = {};
        vm.cancel = cancel;

        getFinancingById();
        function getFinancingById() {
            InnovateService.getFinancingById({id:items},function (rel) {
                vm.financingMessage = rel.response;
            })
        }

        function cancel() {
            $uibModalInstance.dismiss('cancel');
        }

    }
})();