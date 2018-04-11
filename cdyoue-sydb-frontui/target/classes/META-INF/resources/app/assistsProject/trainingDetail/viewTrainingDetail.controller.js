(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('viewTrainingDetailController', viewTrainingDetailController);

    viewTrainingDetailController.$inject = ['$scope', '$state', 'trainingDetailService','$stateParams'];

    function viewTrainingDetailController($scope, $state, trainingDetailService,$stateParams) {
        var vm = this;
        vm.trainingDetail={};
        var id=$stateParams.id;
        console.log($stateParams)
        function getTrainingDetail() {
            trainingDetailService.getTrainingDetails({'id':id},function (data) {
                vm.trainingDetail=data.response;
            },function (err) {
                console.log(err);
            })
        }
        getTrainingDetail();
    }
})();
