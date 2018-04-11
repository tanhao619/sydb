(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('repeatPasswordController', repeatPasswordController);

    repeatPasswordController.$inject = ['$rootScope','$uibModal','$stateParams', '$state','forgetPasswordService'];

    function repeatPasswordController($rootScope,$uibModal,$stateParams, $state,forgetPasswordService) {
        var vm = this;
        vm.tel = $stateParams.tel;
        vm.password = "";
        vm.rePassword = "";
        vm.save = save;

        function save() {
            if(vm.password == vm.rePassword && vm.password !="" &&  vm.rePassword!=""){
                forgetPasswordService.forgetPassword({tel:vm.tel,password:vm.password},function (rel) {
                    $uibModal.open({
                        animation : true,
                        templateUrl : "layouts/messageTips/messageTipsModal.html",
                        controller : "messageTipsModalController",
                        controllerAs:"vm",
                        size : "sm",
                        resolve : {
                            items : function() {
                                return vm.modalTips = "成功！";
                            }
                        }
                    });
                    $state.go("findPasswordSuccess");
                },function (err) {
                    $uibModal.open({
                        animation : true,
                        templateUrl : "layouts/messageTips/messageTipsModal.html",
                        controller : "messageTipsModalController",
                        controllerAs:"vm",
                        size : "sm",
                        resolve : {
                            items : function() {
                                return vm.modalTips = "修改失败！";
                            }
                        }
                    });
                })
            }else{
                $uibModal.open({
                    animation : true,
                    templateUrl : "layouts/messageTips/messageTipsModal.html",
                    controller : "messageTipsModalController",
                    controllerAs:"vm",
                    size : "sm",
                    resolve : {
                        items : function() {
                            return vm.modalTips = "两次密码不一致！";
                        }
                    }
                });
            }

        }
    }
})();
