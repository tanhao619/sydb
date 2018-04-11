/**
 * Created by Administrator on 2017/7/12 0012.
 */
(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('basicDataModalController', basicDataModalController);

    basicDataModalController.$inject = ['$scope', '$uibModalInstance', 'toaster', 'entity', 'basicDataService'];

    function basicDataModalController($scope, $uibModalInstance, toaster, entity, basicDataService) {
        var vm = this;

        vm.editShow = false;

        vm.cancel = cancel;
        vm.save = save;
        vm.checkEmail = checkEmail;
        vm.checkTel = checkTel;

        // 页面加载完成执行
        $scope.$watch('$viewContentLoaded', function() {
            init();
        });

        function init() {
            vm.enterAccountSumary = angular.copy(entity.enterAccountSumary);
            $scope.ueditorSetContent("basicEditContainer",vm.enterAccountSumary.enterpriseDetail);
            vm.enterAccountSumary.checkEmail = false;
            vm.enterAccountSumary.checkTel = false;
        }

        function cancel() {
            $uibModalInstance.dismiss('cancel');
        }

        function save() {
            vm.enterAccountSumary.enterpriseDetail = $scope.ueditorGetContent("basicEditContainer");
            if($scope.ueditorGetContentTxt("basicEditContainer").trim() === ""){
                vm.editShow = true;
                return;
            } else {
                vm.editShow = false;
            }
            basicDataService.editBase(vm.enterAccountSumary, function (data) {
                switch (data.code){
                    case 20:
                        //alert(data.message);
                        toaster.clear();
                        toaster.pop('info', data.message);
                        cancel();
                        init();
                        break;
                    default:
                        //alert(data.message+":"+data.description);
                        toaster.clear();
                        toaster.pop('info', data.message + "：" + data.description);
                        break;
                }
            });
        }
        
        function checkEmail() {
            if (vm.enterAccountSumary.email !== entity.enterAccountSumary.email) {
                basicDataService.checkBase({tel:"",email:vm.enterAccountSumary.email},function (data) {
                    if (data.response === true) {
                        vm.enterAccountSumary.checkEmail = true;
                    } else {
                        vm.enterAccountSumary.checkEmail = false;
                    }
                });
            } else {
                vm.enterAccountSumary.checkEmail = false;
            }
        }
        
        function checkTel() {
            if (vm.enterAccountSumary.tel !== entity.enterAccountSumary.tel) {
                basicDataService.checkBase({tel:vm.enterAccountSumary.tel,email:""},function (data) {
                    if (data.response === true) {
                        vm.enterAccountSumary.checkTel = true;
                    } else {
                        vm.enterAccountSumary.checkTel = false;
                    }
                });
            } else {
                vm.enterAccountSumary.checkTel = false;
            }
        }
    }
})();
