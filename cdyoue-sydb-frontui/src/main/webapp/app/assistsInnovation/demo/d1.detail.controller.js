(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('EnterpriseDetailController', EnterpriseDetailController);

    EnterpriseDetailController.$inject = ['Demo', '$uibModalInstance','entity'];


    function EnterpriseDetailController(Demo, $uibModalInstance,entity) {
        var vm = this;
        console.log(333);
        vm.enterprise = {
            id:null,
            enterpriseName:null,
            provinceName:null,
            cityName:null
        };
        init(); //初始化
        vm.cancel = cancel; //取消

        function init(){
            Demo.getDetail({id:entity.enterpriseId},function(data){
                console.log(data);
                vm.enterprise = data;
            })
        }
        function cancel() {
            $uibModalInstance.dismiss('cancel');
        }
    }
})();