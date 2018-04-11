(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('deviceLibraryDetailController', deviceLibraryDetailController);

    deviceLibraryDetailController.$inject = ['$scope','$uibModal', '$rootScope','$state','$stateParams','deviceLibraryDetailService','loginService'];

    function deviceLibraryDetailController($scope,$uibModal,$rootScope, $state,$stateParams,deviceLibraryDetailService,loginService) {
        var vm = this;

        vm.apply ={
            id:0,
            eid:'',
            contacts:'',
            phone:'',
            count:'',
            createTime:'',
            status:0
        }

         vm.name = "";
        //获取企业详情
        getEquipmentById();
        function getEquipmentById() {
            deviceLibraryDetailService.getEquipmentById({id:$stateParams.id}, function (rel) {
                vm.deviceMessage = rel.response;
            }, function (err) {

            })
        }

        //申请设备
        vm.ifLogin = function ifLogin(id) {
            var token = $.cookie('syFrontUserTokenKey');
            loginService.checkLogin({token:token},function (rel) {
                if (rel.code == 20){
                    loginService.getUserMessageByToken({token:token},function (rel) {
                        vm.name = rel.response.userName;
                    })

                    var out = $uibModal.open({
                        animation : true,
                        templateUrl : "app/assistsInnovation/deviceLibrary/applyDeviceLibrary.html",
                        controller : "applyDeviceLibraryController",
                        controllerAs:"vm",
                        size : "md",
                        resolve : {
                            id : function() {
                                return id;
                            },
                            name: function() {
                                return vm.name;
                            }
                        }
                    });
                    out.result.then(function (result) {
                    }, function () {
                    });
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
