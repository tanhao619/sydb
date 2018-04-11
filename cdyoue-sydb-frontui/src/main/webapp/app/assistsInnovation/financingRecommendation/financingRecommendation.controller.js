(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('financingRecommendationController', financingRecommendationController);

    financingRecommendationController.$inject = ['$scope','$rootScope',"$uibModal", '$state','financingRecommendationService','loginService'];

    function financingRecommendationController($scope,$rootScope,$uibModal, $state,financingRecommendationService,loginService) {
        var vm = this;
        vm.boolean = true;
        vm.query={
            pageSize:10,
            pageNumber:0,
            sort:"-createTime",
            type:"",
            q:""
        }
        vm.page = {};

        getFinancingPageList();
        vm.selectPage = selectPage;
        vm.ifLogin = ifLogin;

        //获取设备分页列表
        function getFinancingPageList() {
            financingRecommendationService.getFinancingPageList(vm.query,function (rel) {
                if(rel.code == 20){
                    vm.page = rel.response;
                    vm.financing = rel.response.list;
                    vm.boolean = true;
                }else{
                    vm.boolean = false;
                }
            },function (err) {
                vm.boolean = false;
            })
        }

        //分页插件
        function selectPage(select, current, max) {
            if (select != current) {
                if (select > max) {
                    return;
                }
                vm.query.pageNumber = select;
                getFinancingPageList();
            }
        }

        //跳转发布融资
        function ifLogin() {
            var token = $.cookie('syFrontUserTokenKey');
            loginService.checkLogin({token:token},function (rel) {
                if (rel.code == 20){
                    $state.go('publishNeed');
                }else{
                    $uibModal.open({
                        animation : true,
                        templateUrl : "layouts/notLoginModal/notLoginModal.html",
                        controller : "notLoginModalController",
                        controllerAs:"vm",
                        size : "sm",
                    });
                }
            },function (err) {
                $uibModal.open({
                    animation : true,
                    templateUrl : "layouts/notLoginModal/notLoginModal.html",
                    controller : "notLoginModalController",
                    controllerAs:"vm",
                    size : "sm",
                });
            })

        }





    }
})();
