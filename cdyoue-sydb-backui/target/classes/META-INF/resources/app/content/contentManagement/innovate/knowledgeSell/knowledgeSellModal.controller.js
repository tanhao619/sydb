(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('KnowledgeSellModalController', KnowledgeSellModalController);

    KnowledgeSellModalController.$inject = ['$uibModalInstance','items','InnovateService'];

    function KnowledgeSellModalController($uibModalInstance,items,InnovateService) {
        var vm = this;
        vm.data = {};
        vm.init = init;
        vm.cancel = cancel; // 关闭
        vm.data = {
            copyright: {},
            patent: {},
            trademark: {}
        };
        vm.type;
        init();//页面初始化


        function init() {
            var id = items.id;
            vm.type = items.type;
            queryData(id, vm.type);
        }

        function queryData(id, type) {
            switch (type) {
                case 1:
                    InnovateService.getTrademarkDetails({"id": id}, queryDataSuccess, queryDataErroe);
                    break;
                case 2:
                    InnovateService.getPatentDetails({"id": id}, queryDataSuccess, queryDataErroe);
                    break;
                case 3:
                    InnovateService.getCopyrightDetails({"id": id}, queryDataSuccess, queryDataErroe);
                    break;
            }
        }

        function queryDataSuccess(result) {
            var status = result.status;
            if(status.toLowerCase() == "fail".toLowerCase()) {
                //alert(result.message);
                return;
            }
            vm.data = result.response;
            if (result.response.copyright != undefined) {
                vm.data.intellectual = result.response.copyright;
                vm.data.intellectual.type = 3;
            } else if(result.response.patent != undefined) {
                vm.data.intellectual = result.response.patent;
                vm.data.intellectual.type = 2;
            } else if(result.response.trademark != undefined) {
                vm.data.intellectual = result.response.trademark;
                vm.data.intellectual.type = 1;
            }
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