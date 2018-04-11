/**
 * Created by PC-45 on 2017/4/20.
 */
(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('projectInterpretationDetailController', projectInterpretationDetailController);

    projectInterpretationDetailController.$inject = ['$scope',  '$state', 'Upload', 'toaster', '$stateParams','projectInterpretationService'];

    function projectInterpretationDetailController($scope, $state, Upload, toaster, $stateParams,projectInterpretationService) {
        var vm = this;
        vm.projectInterpretationDetail={};
        var iid=$stateParams.id;
        console.log(iid);
        function getProjectInterpretation() {
            projectInterpretationService.getProjectInterpretationDetail({'id':iid},function (data) {
                vm.projectInterpretationDetail=data.response;
            },function (err) {
                console.log(err);
            })
        }
        getProjectInterpretation();

    }

})
();
