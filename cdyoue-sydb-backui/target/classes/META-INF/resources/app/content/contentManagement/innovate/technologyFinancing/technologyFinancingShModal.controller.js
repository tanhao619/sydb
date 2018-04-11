/**
 * Created by Administrator on 2017/10/12 0012.
 */
(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('technologyFinancingShModalController', technologyFinancingShModalController);

    technologyFinancingShModalController.$inject = ['$timeout', '$uibModalInstance','items','InnovateService','Upload','toaster'];

    function technologyFinancingShModalController($timeout, $uibModalInstance,items,InnovateService,Upload,toaster) {
        var vm = this;
        vm.financingMessage = {
            id : items
        };
        vm.cancel = cancel;
        vm.save = save;



        function save() {
            InnovateService.toExamine(vm.financingMessage,function (rel) {
                toaster.pop('info', "操作成功！");
                cancel();
            },function (err) {
                toaster.pop('info', "操作失败！");
            })
        }

        function cancel() {
            $uibModalInstance.close(true);
        }


    }
})();