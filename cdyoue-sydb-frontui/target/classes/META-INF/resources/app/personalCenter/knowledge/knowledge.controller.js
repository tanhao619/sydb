/**
 * Created by Administrator on 2017/9/25 0025.
 */
(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('knowledgeController', knowledgeController);

    knowledgeController.$inject = ['loginService', '$scope', '$state', '$location', '$timeout', 'toaster', 'knowledgeService'];

    function knowledgeController(loginService, $scope, $state, $location, $timeout, toaster, knowledgeService) {
        var vm = this;
        vm.querySeeksModel = {
            pageNumber: 0,
            pageSize: 10,
            type:null,
            transactionType:null
        };
        vm.querySalesModel = {
            pageNumber: 0,
            pageSize: 10,
        };

        init();//初始化
        vm.selectSeeksPage=selectSeeksPage; //设置求购分页
        vm.selectSeeksPageSize = selectSeeksPageSize; //设置求购分页
        vm.selectSalesPage=selectSalesPage; //设置出售分页
        vm.selectSalesPageSize = selectSalesPageSize; //设置出售分页

        function init() {
            var token = $.cookie('syFrontUserTokenKey');
            loginService.checkLogin({token:token}, function (rel) {
                if (rel.code === 20) {
                    knowledgeService.getMySeeks(vm.querySeeksModel, function (data) {
                        vm.seeksPager = data.response;
                    });
                    knowledgeService.getMySales(vm.querySalesModel, function (data) {
                        vm.salesPager = data.response;
                    });
                } else {
                    $state.go("login");
                }
            },function (err) {
                $state.go("login");
            })
        }

        function selectSeeksPage(select, current, max) {
            if (select !== current) {
                if (select > max) {
                    return;
                }
                vm.querySeeksModel.pageNumber = select;
                knowledgeService.getMySeeks(vm.querySeeksModel, function (rlt) {
                    vm.seeksPager = rlt.response;
                    vm.seeksPager.pageNum = select;
                })
            }
        };

        function selectSeeksPageSize(pageSize) {
            vm.querySeeksModel.pageNumber = 1;
            vm.querySeeksModel.pageSize = pageSize;
            knowledgeService.getMySeeks(vm.querySeeksModel,function(data){
                vm.seeksPager = data.response;
            })
        };

        function selectSalesPage(select, current, max) {
            if (select !== current) {
                if (select > max) {
                    return;
                }
                vm.querySalesModel.pageNumber = select;
                knowledgeService.getMySales(vm.querySalesModel, function (rlt) {
                    vm.salesPager = rlt.response;
                    vm.salesPager.pageNum = select;
                })
            }
        };

        function selectSalesPageSize(pageSize) {
            vm.querySalesModel.pageNumber = 1;
            vm.querySalesModel.pageSize = pageSize;
            knowledgeService.getMySales(vm.querySalesModel,function(data){
                vm.salesPager = data.response;
            })
        };

    }
})();
