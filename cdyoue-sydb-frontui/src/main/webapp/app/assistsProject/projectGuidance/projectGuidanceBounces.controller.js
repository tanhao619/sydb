/**
 * Created by sky on 2017/10/16.
 */
(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('projectGuidanceBouncesController', projectGuidanceBouncesController);

    projectGuidanceBouncesController.$inject = ['$scope', '$state', 'projectGuidanceService','$uibModalInstance','loginService'];

    function projectGuidanceBouncesController($scope, $state, projectGuidanceService,$uibModalInstance,loginService) {
        var vm = this;
        vm.cancel=cancel;
        vm.userName="";

        vm.direction={};
        vm.saveDirection=saveDirection;
        vm.getUserMessage=getUserMessage;//获取用户信息
        function saveDirection() {
            projectGuidanceService.saveEnterpriseDirection(vm.direction,function (data) {
                console.log("保存成功");
                cancel();
            },function (err) {
                console.log(err);
            })
        }

        getUserMessage();
        function getUserMessage() {
            var token = $.cookie('syFrontUserTokenKey');
            loginService.getUserMessageByToken({token:token},function (data) {
                vm.userName= data.response.userName;
            },function (err) {
                console.log(err);
            })
        }

        function cancel() {
            $uibModalInstance.dismiss('cancel');
        }
    }
})();