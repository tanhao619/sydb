/**
 * Created by Administrator on 2017/7/12 0012.
 */
(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('enterpriseCertificationSubmitModalController', enterpriseCertificationSubmitModalController);

    enterpriseCertificationSubmitModalController.$inject = ['$scope', '$state','$uibModalInstance', '$interval'];

    function enterpriseCertificationSubmitModalController($scope, $state, $uibModalInstance, $interval) {
        var vm = this;

        vm.djs = 5;

        vm.cancel = cancel;
        vm.jy = jy;

        var timer = $interval(vm.jy,1000);
        function cancel() {
            $uibModalInstance.dismiss('cancel');
            $state.go("personalCenter");
        }
        $scope.$on('$destroy',function(){
            $interval.cancel(timer);
        });

        function jy() {
            vm.djs = vm.djs - 1;
            if (vm.djs <= 0) {
                cancel();
            }
        }

    }
})();
