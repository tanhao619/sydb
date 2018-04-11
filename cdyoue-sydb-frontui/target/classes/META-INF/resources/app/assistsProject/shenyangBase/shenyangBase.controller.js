(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('shenyangBaseController', shenyangBaseController);

    shenyangBaseController.$inject = ['$scope','$uibModal','$rootScope','loginService'];

    function shenyangBaseController($scope,$uibModal,$rootScope,loginService) {
        var vm = this;

        vm.checkLogin=checkLogin;

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
        function openModal2 () {
            var out = $uibModal.open({
                animation : true,
                templateUrl : "app/assistsProject/shenyangBase/shenyangBaseBounces.html",
                controller : "shenyangBaseBouncesController",
                controllerAs:"vm",
                size : "md",
                resolve : {
                    items : function() {

                    }
                }
            });
            out.result.then(function (result) {
                console.log(result); //result关闭是回传的值
            }, function (reason) {
            });
        }

    }
})();
