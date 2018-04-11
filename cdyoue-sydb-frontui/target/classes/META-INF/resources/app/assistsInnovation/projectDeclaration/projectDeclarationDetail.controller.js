/**
 * Created by PC-45 on 2017/4/20.
 */
(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('projectDeclarationDetailController', projectDeclarationDetailController);

    projectDeclarationDetailController.$inject = ['$scope','$stateParams','projectDeclarationService','$rootScope','$uibModal','loginService'];

    function projectDeclarationDetailController($scope, $stateParams,projectDeclarationService,$rootScope,$uibModal,loginService) {
        var vm = this;
        vm.checkLogin=checkLogin;//申报时检查是否登录
        vm.entity={};
        vm.projectDeclarationDetail={};
        var id=$stateParams.id;
        vm.entity.projectId=id;
        function getProjectDeclaration() {
            projectDeclarationService.getProjectDeclarationDetail({'id':id},function (rel) {
                vm.projectDeclarationDetail=rel.response;
            },function (err) {
                console.log(err);
            })
        }
        getProjectDeclaration();

        // if(JSON.parse(sessionStorage.getItem("syFrontUserTokenKey"))){
        //     $rootScope.SY_IsLogin = true;
        // }else {
        //     $rootScope.SY_IsLogin = false;
        // }
        vm.booleanLogin=false;
        var token = $.cookie('syFrontUserTokenKey');
        loginService.checkLogin({token:token},function (rel) {
            if (rel.code == 20){
                vm.booleanLogin=true;
            }
        },function (err) {
            console.log(err);
        });
        function openModal2 () {
            var out = $uibModal.open({
                animation : true,
                templateUrl : "app/assistsInnovation/projectDeclaration/projectBouncesModal.html",
                controller : "ProjectBouncesModalController",
                controllerAs:"vm",
                size : "lg",
                resolve : {
                    items : function() {
                        return id;
                    }
                }
            });
            out.result.then(function (result) {
                console.log(result); //result关闭是回传的值
            }, function (reason) {
                //init();
            });
        }
        function checkLogin() {
            if(vm.booleanLogin){
                openModal2 ();
            }else {
                $uibModal.open({
                    animation : true,
                    templateUrl : "layouts/notLoginModal/notLoginModal.html",
                    controller : "notLoginModalController",
                    controllerAs:"vm",
                    size : "sm",
                });
            }
        }

    }

})
();
