(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('KnowledgeAssessModalController', KnowledgeAssessModalController);

    KnowledgeAssessModalController.$inject = ['$uibModalInstance','items','ConsultationService','toaster'];

    function KnowledgeAssessModalController($uibModalInstance,items,ConsultationService,toaster) {
        var vm = this;
        vm.data = {};
        vm.init = init;
        vm.cancel = cancel; // 关闭
        vm.data = {};
        vm.type;
        init();//页面初始化


        function init() {
            var id = items.id;
            queryData(id);
        }

        function queryData(id) {
            ConsultationService.getAssessDetail({"id": id}, queryDataSuccess, queryDataErroe);
        }

        function queryDataSuccess(result) {
            var status = result.status;
            if(status.toLowerCase() == "fail".toLowerCase()) {
                //alert(result.message);
                return;
            }
            vm.data = result.response;
        }

        function queryDataErroe(result) {
            toaster.pop('info', '请求数据失败');
        }

        function cancel() {
            $uibModalInstance.dismiss('cancel');
        }
    }
})();