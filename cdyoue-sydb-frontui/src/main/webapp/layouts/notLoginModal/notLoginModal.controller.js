(function() {
    'use strict';

    angular
        .module('sydbApp')
        .controller('notLoginModalController', notLoginModalController);

    notLoginModalController.$inject = ['$scope', '$state','$uibModalInstance'];

    function notLoginModalController ($scope, $state,$uibModalInstance) {
        var vm = this;
        vm.cancel = cancel;
        vm.login=login;
        function cancel(){
            $uibModalInstance.dismiss('cancel');
        }

        function login() {
            $state.go('login');
            cancel();
        }
    }

})();
