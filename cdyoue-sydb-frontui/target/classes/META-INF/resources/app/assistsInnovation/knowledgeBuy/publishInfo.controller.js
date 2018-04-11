(function(){
    'use strict';
    angular.module('sydbApp')
        .controller('BuyPublishInfoController',BuyPublishInfoController);

    BuyPublishInfoController.$inject=['$scope', '$stateParams'];
    function BuyPublishInfoController($scope, $stateParams){
        var vm = this;
        vm.pageStatus = $stateParams.status;
        showStatus();//判断页面显示状态

        function showStatus() {
            switch (vm.pageStatus) {
                case '0':
                    vm.showContent = true;
                    return;
                case '1':
                    vm.showContent = false;
                    return;
            }
        }
    }

})();
