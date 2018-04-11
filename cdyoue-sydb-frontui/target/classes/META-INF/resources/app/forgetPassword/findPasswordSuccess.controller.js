(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('findPasswordSuccessController', findPasswordSuccessController);

    findPasswordSuccessController.$inject = ['$rootScope', '$state','$interval'];

    function findPasswordSuccessController($rootScope, $state,$interval) {
        var vm = this;
        vm.nav = [];
        vm.nav = ['确认账号','安全验证','重置密码'];
        vm.show = 1;
        vm.next = next;
        vm.paracont="5秒";
        vm.time = 4;
        next();
        function next(){
            var timeInterval = $interval(function () {
                if(vm.time > 0){
                    vm.paracont = vm.time+"秒"
                    vm.time -- ;
                }else if(vm.time == 0){
                    vm.time = 4;
                    $state.go("login");
                    $interval.cancel(timeInterval)
                }
            },1000)
        }
    }
})();
