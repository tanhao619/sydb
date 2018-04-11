(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('trainingDetailController', trainingDetailController);

    trainingDetailController.$inject = ['$scope', '$state', 'trainingDetailService'];

    function trainingDetailController($scope, $state, trainingDetailService) {
        var vm = this;
        vm.queryModel = {
            pageNumber: 0,
            pageSize: 10,
            sort:'-updateTime',
            q: null
        };
        vm.selectPage = selectPage; //设置分页
        vm.selectPageSize = selectPageSize; //设置分页大小
        vm.query = query; // 关键字搜索
        function init() {
            trainingDetailService.getTraining(vm.queryModel, function (data) {
                vm.pager = data.response;
                console.log(vm.pager)
            })
        }

        init();//初始化
        function query(queryModel) {
            queryModel.pageNumber = 0;
            queryModel.pageSize = 10;
            trainingDetailService.getTraining(queryModel, function (data) {
                vm.pager = data.response;
            })
        }

        function selectPage(select, current, max) {
            if (select !== current) {
                if (select > max) {
                    return;
                }

                vm.queryModel.pageNumber = select;
                trainingDetailService.getTraining(vm.queryModel, function (rlt) {
                    vm.pager = rlt.response;
                    vm.pager.pageNum = select;
                })
            }
        };

        function selectPageSize(pageSize) {
            vm.queryModel.pageNumber = 0;
            vm.queryModel.pageSize = pageSize;
        }
    }
})();
