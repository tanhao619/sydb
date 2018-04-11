(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('settledEnterpriseController', settledEnterpriseController);

    settledEnterpriseController.$inject = ['$scope', '$state', 'settledEnterpriseService'];

    function settledEnterpriseController($scope, $state, settledEnterpriseService) {
        var vm = this;
        vm.queryModel = {
            pageNumber: 0,
            pageSize: 10,
            sort:'-updateTime',
            q: null
        };
        vm.goEnterpriseDetail=goEnterpriseDetail;
        vm.selectPage = selectPage; //设置分页
        vm.selectPageSize = selectPageSize; //设置分页大小
        vm.query = query; // 关键字搜索
        function init() {
            settledEnterpriseService.getEnterprise(vm.queryModel, function (data) {
                vm.pager = data.response;
            })
        }

        init();//初始化
        function query(queryModel) {
            queryModel.pageNumber = 0;
            queryModel.pageSize = 10;
            settledEnterpriseService.getEnterprise(queryModel, function (data) {
                vm.pager = data.response;
            })
        }

        function selectPage(select, current, max) {
            if (select !== current) {
                if (select > max) {
                    return;
                }

                vm.queryModel.pageNumber = select;
                settledEnterpriseService.getEnterprise(vm.queryModel, function (rlt) {
                    vm.pager = rlt.response;
                    vm.pager.pageNum = select;
                })
            }
        };

        function selectPageSize(pageSize) {
            vm.queryModel.pageNumber = 0;
            vm.queryModel.pageSize = pageSize;
        }

        function goEnterpriseDetail(id) {
            $state.go("settledEnterpriseDetail",{id:id})
        }
    }
})();
