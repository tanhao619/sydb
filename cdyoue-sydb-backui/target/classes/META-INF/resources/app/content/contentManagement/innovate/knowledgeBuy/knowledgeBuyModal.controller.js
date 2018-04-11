(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('KnowledgeBuyModalController', KnowledgeBuyModalController);

    KnowledgeBuyModalController.$inject = ['$uibModalInstance','items','InnovateService'];

    function KnowledgeBuyModalController($uibModalInstance,items,InnovateService) {
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
                    InnovateService.getBuyDetails({"id": id}, queryDataSuccess, queryDataErroe);
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
            //alert('请求数据失败');
            // ModalTips.setMessage(false,'请求数据失败');
        }

        function cancel() {
            $uibModalInstance.dismiss('cancel');
        }
    }
})();