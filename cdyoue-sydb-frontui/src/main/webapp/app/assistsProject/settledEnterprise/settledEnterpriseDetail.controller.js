(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('settledEnterpriseDetailController', settledEnterpriseDetailController);

    settledEnterpriseDetailController.$inject = ['$scope', '$state', 'settledEnterpriseService','$stateParams'];

    function settledEnterpriseDetailController($scope, $state, settledEnterpriseService,$stateParams) {
        var vm = this;
        vm.enterpriseDetail={};
        var id=$stateParams.id;
        console.log($stateParams)
        function getEnterpriseDetail() {
            settledEnterpriseService.getEnterpriseDetails({'id':id},function (data) {
                vm.enterpriseDetail=data.response;
            },function (err) {
                console.log(err);
            })
        }
        getEnterpriseDetail();
    }
})();
