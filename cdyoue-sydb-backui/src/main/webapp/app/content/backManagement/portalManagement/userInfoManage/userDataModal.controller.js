(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('UserDataModalController', UserDataModalController);
    UserDataModalController.$inject = ['$uibModalInstance', 'items','PortalManageService'];
    function UserDataModalController( $uibModalInstance, items,PortalManageService) {
        var vm = this;
        vm.items = items;
        vm.cancel = cancel; // 关闭
        init();

        function init() {
            if(vm.items.role==0){
                PortalManageService.getPerson({id:vm.items.id},function (obj) {
                    if(obj.status != "SUCCESS"){
                        if(obj.message){
                            console.log(obj.message +"("+ obj.description+")！");
                        }
                        return;
                    }
                    vm.data = obj.response;
                   var addr = angular.fromJson(vm.data.location);
                   vm.addressConversion = addr.province + addr.city;
                },function error (obj) {
                    if(obj.message){
                        console.log(obj.message +"("+ obj.description+")！");
                    }
                });
            }else {
                PortalManageService.getEnterprise({id:vm.items.id},function (obj) {
                    if(obj.status != "SUCCESS"){
                        if(obj.message){
                            console.log(obj.message +"("+ obj.description+")！");
                        }
                        return;
                    }
                    vm.data = obj.response;
                    var addr = angular.fromJson(vm.data.location);
                    vm.addressConversion = addr.province + addr.city;
                },function error (obj) {
                    if(obj.message){
                        console.log(obj.message +"("+ obj.description+")！");
                    }
                });
            }
        }
        function cancel() {
            $uibModalInstance.dismiss('cancel');
        }
    }
})();