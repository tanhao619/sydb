/**
 * Created by PC-45 on 2017/4/20.
 */
(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('SaleIPDetailsController', SaleIPDetailsController);

    SaleIPDetailsController.$inject = ['$scope', 'SaleIPService', '$stateParams'];

    function SaleIPDetailsController($scope, SaleIPService, $stateParams) {
        var vm = this;
        vm.data = {};
        vm.init = init;
        vm.type;
        init();//页面初始化


        function init() {
            document.title = '知识产权出售详情'//设置页面title
            var id = $stateParams.id;
            vm.type = $stateParams.type;
            queryData(id, vm.type);
        }

        function queryData(id, type) {
            switch (type) {
                case '1':
                    SaleIPService.getTrademarkDetails({"id": id}, queryDataSuccess, queryDataErroe);
                    break;
                case '2':
                    SaleIPService.getPatentDetails({"id": id}, queryDataSuccess, queryDataErroe);
                    break;
                case '3':
                    SaleIPService.getCopyrightDetails({"id": id}, queryDataSuccess, queryDataErroe);
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
            } else if(result.response.patent != undefined) {
                vm.data.intellectual = result.response.patent;
            } else if(result.response.trademark != undefined) {
                vm.data.intellectual = result.response.trademark;
            }
        }

        function queryDataErroe(result) {
            //alert('请求数据失败');
            // ModalTips.setMessage(false,'请求数据失败');
        }


    }
})();
