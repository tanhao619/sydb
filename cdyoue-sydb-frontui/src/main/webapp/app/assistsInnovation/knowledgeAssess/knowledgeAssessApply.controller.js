(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('knowledgeAssessApplyController', knowledgeAssessApplyController);

    knowledgeAssessApplyController.$inject = ['$rootScope','$uibModalInstance', 'UserToken','AssessService','$uibModal'];

    function knowledgeAssessApplyController($rootScope ,$uibModalInstance,UserToken,AssessService,$uibModal) {
        var vm = this;
        vm.cancel = cancel;
        vm.showTable =1;
        vm.changeTable = changeTable;
        vm.save = save;//提交事件
        vm.assess = {
            type:1
        };
        vm.companyName=UserToken.getUserToken().userMine.name;
        function cancel() {
            $uibModalInstance.dismiss('cancel');
        }

        function changeTable(id){
            if (id == null || id == ""){
                id = 1;
            }
            vm.assess.type = id;
            if(id == 1){
                vm.showTable = 1;
            }
            if(id == 2)
            {
                vm.showTable = 2;
            }
            if(id == 3)
            {
                vm.showTable = 3;
            }
        }

        function save() {
            AssessService.publishAssess(vm.assess,publishSuccess);
        }
        function publishSuccess() {
            cancel();
            var out = $uibModal.open({
                animation : true,
                templateUrl : "layouts/messageTips/messageTipsModal.html",
                controller : "messageTipsModalController",
                controllerAs:"vm",
                size : "sm",
                resolve : {
                    items : function() {
                        return "申请成功！！！";
                    }
                }
            });
        }

    }
})();
