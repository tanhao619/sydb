/**
 * Created by Administrator on 2017/9/25 0025.
 */
(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('declareController', declareController);

    declareController.$inject = ['loginService', '$scope', '$state', '$location', '$timeout', 'toaster', 'declareService'];

    function declareController(loginService, $scope, $state, $location, $timeout, toaster, declareService) {
        var vm = this;
        vm.queryModel = {
            pageNumber: 0,
            pageSize: 10,
        };
        vm.selectPage=selectPage; //设置分页
        vm.selectPageSize = selectPageSize; //设置分页
        init();//初始化
        function init() {
            var token = $.cookie('syFrontUserTokenKey');
            loginService.checkLogin({token:token}, function (rel) {
                if (rel.code === 20) {
                    declareService.getMy(vm.queryModel, function (data) {
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
                declareService.getMy(vm.queryModel, function (rlt) {
                    vm.pager = rlt.response;
                    vm.pager.pageNum = select;
                })
            }
        };

        function selectPageSize(pageSize) {
            vm.queryModel.pageNumber = 0;
            vm.queryModel.pageSize = pageSize;
            // declareService.getMy(vm.queryModel,function(data){
            //     vm.pager = data.response;
            // })
        }
    }
})();
