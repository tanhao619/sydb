/**
 * Created by Administrator on 2017/9/25 0025.
 */
(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('financingController', financingController);

    financingController.$inject = ['loginService', '$scope', '$state', '$location', '$timeout', 'toaster', 'financingService'];

    function financingController(loginService, $scope, $state, $location, $timeout, toaster, financingService) {
        var vm = this;
        vm.queryModel = {
            pageNumber: 0,
            pageSize: 10,
            sort:'-createTime'
        };
        vm.selectPage=selectPage; //设置分页
        vm.selectPageSize = selectPageSize; //设置分页
        init();//初始化
        function init() {
            var token = $.cookie('syFrontUserTokenKey');
            loginService.checkLogin({token:token}, function (rel) {
                if (rel.code === 20) {
                    financingService.getMy(vm.queryModel, function (data) {
                        vm.pager = data.response;
                    })
                } else {
                    $state.go("login");
                }
            },function (err) {
                $state.go("login");
            })
        }


        function selectPage(select, current, max) {
            if (select !== current) {
                if (select > max) {
                    return;
                }

                vm.queryModel.pageNumber = select;
                financingService.getMy(vm.queryModel, function (rlt) {
                    vm.pager = rlt.response;
                    vm.pager.pageNum = select;
                })
            }
        };

        function selectPageSize(pageSize) {
            vm.queryModel.pageNumber = 1;
            vm.queryModel.pageSize = pageSize;
            financingService.getMy(vm.queryModel,function(data){
                vm.pager = data.response;
            })
        }
    }
})();
