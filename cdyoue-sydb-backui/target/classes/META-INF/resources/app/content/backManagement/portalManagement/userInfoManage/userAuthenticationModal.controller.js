(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('UserAuthenticationModalController', UserAuthenticationModalController);
    UserAuthenticationModalController.$inject = ['$rootScope', '$uibModalInstance', 'items','PortalManageService','$uibModal'];
    function UserAuthenticationModalController($rootScope, $uibModalInstance, items,PortalManageService,$uibModal) {
        var vm = this;
        vm.cancel = cancel; // 关闭
        vm.save = save; // 保存
        vm.reviewStatus = reviewStatus; //审核状态改变事件
        vm.items = items;
        vm.errorMessage = false; // 显示必填警告
        vm.radioStatusAudit = ""; // 单选框的值
        vm.reviewReason = ""; // 审核备注

        function save() {
            if(vm.radioStatusAudit==1 || vm.radioStatusAudit==2){
                // 通过与否（1是通过，2是拒绝）
                if (vm.radioStatusAudit==1) {
                    PortalManageService.authApprove({'id': vm.items.id,type:typeConversion()},{'reason':vm.reviewReason}, function success(obj) {
                        if (obj.status != "SUCCESS") {
                            if (obj.message) {
                               // alert(obj.message + "(" + obj.description + ")！");
                                var out = $uibModal.open({
                                    animation : true,
                                    templateUrl : "app/layouts/messageTips/messageTips.html",
                                    controller : "messageTipsController",
                                    controllerAs:"vm",
                                    size : "md",
                                    resolve : {
                                        items : function() {
                                            return  obj.message + "(" + obj.description + ")！";
                                        }
                                    }
                                });
                            }
                            return;
                        }
                        cancel();
                    }, function error(obj) {
                        console.log(obj);
                    });
                } else if(vm.radioStatusAudit == 2) {
                    PortalManageService.authReject({'id': vm.items.id,type:typeConversion()},{'reason':vm.reviewReason}, function success(obj) {
                        if (obj.status != "SUCCESS") {
                            if (obj.message) {
                              //  alert(obj.message + "(" + obj.description + ")！");
                                var out = $uibModal.open({
                                    animation : true,
                                    templateUrl : "app/layouts/messageTips/messageTips.html",
                                    controller : "messageTipsController",
                                    controllerAs:"vm",
                                    size : "md",
                                    resolve : {
                                        items : function() {
                                            return  obj.message + "(" + obj.description + ")！";
                                        }
                                    }
                                });
                            }
                            return;
                        }
                        cancel();
                    }, function error(obj) {
                        console.log(obj);
                    });
                }

            }else {
                vm.errorMessage = true;
                return;
            }
        }
        function reviewStatus() {
            vm.errorMessage = false;
        }
        function typeConversion() {
            switch (vm.items.type) {
                case 1:
                    return 'realname';
                case 2:
                    return 'daka';
                case 3:
                    return 'mentor';
                default:
                    return 'realname';
            }
        }
        function cancel() {
            $uibModalInstance.dismiss('cancel');
        }

    }
})();