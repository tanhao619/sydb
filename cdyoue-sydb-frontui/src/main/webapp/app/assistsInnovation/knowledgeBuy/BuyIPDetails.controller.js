/**
 * Created by PC-45 on 2017/4/20.
 */
(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('BuyIPDetailsController', BuyIPDetailsController);

    BuyIPDetailsController.$inject = ['$scope', 'BuyIPService','$stateParams'];

    function BuyIPDetailsController($scope, BuyIPService,$stateParams) {
        var vm = this;
        vm.data = {};
        vm.init = init;
        init();//页面初始化

        function init() {
            var id = $stateParams.id;
            queryData(id);
        }

        function queryData(id) {
            BuyIPService.getDetails({'id':id},queryDataSuccess,queryDataError);
        }

        function queryDataSuccess(result) {
            var status = result.status;
            if(status.toLowerCase() == "fail".toLowerCase()) {
                //alert(result.message);
                return;
            }
            //console.log(result);
            vm.data = result.response;
            // document.title = vm.data.intellectual.title + ' - 知产求购 - 知识产权';//设置页面title
        }

        function queryDataError(result) {
           // alert('请求数据失败');
        }
    }
})();
