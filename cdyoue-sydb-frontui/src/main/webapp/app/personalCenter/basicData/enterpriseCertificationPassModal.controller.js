/**
 * Created by Administrator on 2017/7/12 0012.
 */
(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('enterpriseCertificationPassModal', enterpriseCertificationPassModal);

    enterpriseCertificationPassModal.$inject = ['$scope', '$timeout','$uibModalInstance','toaster', 'Upload','SELECT_ITEMS','basicDataService'];

    function enterpriseCertificationPassModal($scope,$timeout, $uibModalInstance,toaster, Upload,SELECT_ITEMS,basicDataService) {
        var vm = this;
        vm.editShow = false;
        vm.cancel = cancel;
        // 页面加载完成执行
        $scope.$watch('$viewContentLoaded', function() {
            init();
        });

        function init() {
            // vm.enterAccountSumary = angular.copy(entity.enterAccountSumary);
            basicDataService.getCertification({type: 'realname'}, function (data) {
                        vm.certification = data.response;
            });
        }

        function cancel() {
            $uibModalInstance.dismiss('cancel');
        }






    }
})();
