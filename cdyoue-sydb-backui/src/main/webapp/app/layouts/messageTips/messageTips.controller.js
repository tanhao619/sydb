(function() {
    'use strict';

    angular
        .module('oddApp')
        .controller('messageTipsController', messageTipsController);

    messageTipsController.$inject = ['$scope', '$state','$uibModalInstance','items','$timeout'];

    function messageTipsController ($scope, $state,$uibModalInstance,items,$timeout) {
        var vm = this;
        vm.cancel = cancel;
        vm.messageTips = "";
        vm.messageTips = items;
        vm.ok = ok;
        //console.log(vm.messageTips);
        function cancel(){
            $uibModalInstance.dismiss('cancel');
            if(vm.messageTips == "退出成功")
            {
                $state.go("login");
            }
        }

        function ok(){
            $uibModalInstance.close('1');
        }

    }

})();
