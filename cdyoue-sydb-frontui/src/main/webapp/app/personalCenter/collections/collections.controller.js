/**
 * Created by Administrator on 2017/9/25 0025.
 */
(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('collectionsController', collectionsController);

    collectionsController.$inject = ['loginService', '$scope', '$state', '$location', '$timeout', 'toaster', 'collectionsService'];

    function collectionsController(loginService, $scope, $state, $location, $timeout, toaster, collectionsService) {
        var vm = this;
        vm.queryEAModel = {
            pageNumber: 1,
            pageSize: 10,
        };
        vm.queryPIModel = {
            pageNumber: 1,
            pageSize: 10,
        };

        init();//初始化
        vm.selectEAPage=selectEAPage; //设置专家成果分页
        vm.selectEAPageSize = selectEAPageSize; //设置专家成果分页
        vm.selectPIPage=selectPIPage; //设置专业解读分页
        vm.selectPIPageSize = selectPIPageSize; //设置专业解读分页

        function init() {
            var token = $.cookie('syFrontUserTokenKey');
            loginService.checkLogin({token:token}, function (rel) {
                if (rel.code === 20) {
                    collectionsService.getMyEA(vm.queryEAModel, function (data) {
                        vm.EAPager = data.response;
                    });
                    collectionsService.getMyPI(vm.queryPIModel, function (data) {
                        vm.PIPager = data.response;
                    });
                } else {
                    $state.go("login");
                }
            },function (err) {
                $state.go("login");
            })
        }

        function selectEAPage(select, current, max) {
            if (select !== current) {
                if (select > max) {
                    return;
                }
                vm.queryEAModel.pageNumber = select+1;
                collectionsService.getMyEA(vm.queryEAModel, function (rlt) {
                    vm.EAPager = rlt.response;
                    vm.EAPager.pageNum = select;
                })
            }
        };

        function selectEAPageSize(pageSize) {
            vm.queryEAModel.pageNumber = 1;
            vm.queryEAModel.pageSize = pageSize;
            collectionsService.getMyEA(vm.queryEAModel,function(data){
                vm.EAPager = data.response;
            })
        };

        function selectPIPage(select, current, max) {
            if (select !== current) {
                if (select > max) {
                    return;
                }
                vm.queryPIModel.pageNumber = select+1;
                collectionsService.getMyPI(vm.queryPIModel, function (rlt) {
                    vm.PIPager = rlt.response;
                    vm.PIPager.pageNum = select;
                })
            }
        };

        function selectPIPageSize(pageSize) {
            vm.queryPIModel.pageNumber = 1;
            vm.queryPIModel.pageSize = pageSize;
            collectionsService.getMyPI(vm.queryPIModel,function(data){
                vm.PIPager = data.response;
            })
        };



    }
})();
