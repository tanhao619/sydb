    (function () {
    'use strict';
    angular
        .module('sydbApp')
        .controller('applyDeviceLibraryController', applyDeviceLibraryController);
        applyDeviceLibraryController.$inject = ['$scope','toaster','$uibModal','loginService', '$state','deviceLibraryDetailService','$uibModalInstance','id','name'];
    function applyDeviceLibraryController($scope,toaster,$uibModal,loginService, $state,deviceLibraryDetailService,$uibModalInstance,id,name) {
        var vm = this;
        vm.cancel = cancel;
        vm.applyEquipment = applyEquipment;
        vm.name = name;
        vm.apply = {
            id:0,
            eId:id,
            contacts:"",
            phone:"",
            count:"",
            createTime:"",
            status:""
        };


        //提交申请
        function applyEquipment() {
            var token = $.cookie('syFrontUserTokenKey');
            loginService.checkLogin({token:token},function (rel) {
                if (rel.code == 20){
                    deviceLibraryDetailService.applyEquipment(vm.apply,function () {
                        toaster.pop('info', "操作成功！");
                        cancel();
                    },function () {
                        toaster.pop('info', "操作失败！");
                    })
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

        //关闭窗口
        function cancel() {
            $uibModalInstance.close ('true');
        }
    }
})();
