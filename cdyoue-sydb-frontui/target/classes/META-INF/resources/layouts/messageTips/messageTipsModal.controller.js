(function() {
    'use strict';

    angular
        .module('sydbApp')
        .controller('messageTipsModalController', messageTipsModalController);

    messageTipsModalController.$inject = ['$scope', '$state','$uibModalInstance','items','$timeout'];

    function messageTipsModalController ($scope, $state,$uibModalInstance,items,$timeout) {
        var vm = this;
        vm.cancel = cancel;
        vm.close = close;
        vm.messageTips = "";
        vm.messageTips = items;
        function cancel(){
            $uibModalInstance.dismiss('cancel');
            if(vm.messageTips == "退出成功")
            {
                 $state.go("login");
            }
        }
function close(){
    $uibModalInstance.dismiss('close');
}
            $timeout(function(){
                $(".fade").click(function(){
                    // alert(123);
                    if(vm.messageTips == "退出成功") {
                        $state.go("login");
                    }
                })
            },50);
            // $state.go("login");


        // setTimeout(cancel,50);
    }

})();
