(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('securityValidateController', securityValidateController);

    securityValidateController.$inject = ['$rootScope','$uibModal','$scope','$interval', '$state','$stateParams','forgetPasswordService'];

    function securityValidateController($rootScope,$uibModal, $scope,$interval,$state,$stateParams,forgetPasswordService) {
        var vm = this;
        vm.nav = [];
        vm.captcha = "";
        vm.tel = $stateParams.tel;
        vm.getCaptcha = getCaptcha;
        vm.checkCaptcha = checkCaptcha;
        vm.paracont="发送验证码";
        vm.time = 60;
        vm.b= false;
        function getCaptcha() {
                forgetPasswordService.getTelCaptcha({tel:vm.tel},function (rel) {
                    $uibModal.open({
                        animation : true,
                        templateUrl : "layouts/messageTips/messageTipsModal.html",
                        controller : "messageTipsModalController",
                        controllerAs:"vm",
                        size : "sm",
                        resolve : {
                            items : function() {
                                return vm.modalTips = "发送成功！";
                            }
                        }
                    });
                    var timeInterval = $interval(function () {
                        if(vm.time > 0){
                            vm.paracont = "("+vm.time+"s)后重新获取"
                            vm.b = true;
                            vm.time -- ;
                        }else if(vm.time == 0){
                            vm.paracont = "发送验证码";
                            vm.b = false;
                            vm.time = 60;
                            $interval.cancel(timeInterval)
                        }
                    },1000)
                },function (err) {
                })
        }

        //验证验证码
        function checkCaptcha() {
            forgetPasswordService.checkTelCaptcha({tel:vm.tel,captcha:vm.captcha},function (rel) {
                if(rel.code == 20){
                    $state.go("repeatPassword",{tel:vm.tel});
                }else{
                }
            },function (err) {
                $uibModal.open({
                    animation : true,
                    templateUrl : "layouts/messageTips/messageTipsModal.html",
                    controller : "messageTipsModalController",
                    controllerAs:"vm",
                    size : "sm",
                    resolve : {
                        items : function() {
                            return vm.modalTips = "失败！";
                        }
                    }
                });
            })
        }
    }
})();
