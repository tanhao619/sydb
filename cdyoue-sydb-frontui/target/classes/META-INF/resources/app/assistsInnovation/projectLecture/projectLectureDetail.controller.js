/**
 * Created by PC-45 on 2017/4/20.
 */
(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('projectLectureDetailController', projectLectureDetailController);

    projectLectureDetailController.$inject = ['$scope',  '$state', 'Upload', 'toaster', '$stateParams','projectLectureService'];

    function projectLectureDetailController($scope, $state, Upload, toaster, $stateParams,projectLectureService) {
        var vm = this;
        vm.projectLectureDetail={};
        var iid=$stateParams.id;
        function getProjectLecture() {
            projectLectureService.getProjectLectureDetail({'id':iid},function (data) {
                vm.projectLectureDetail=data.response;
            },function (err) {
                console.log(err);
            })
        }
        getProjectLecture();


    }

})
();
