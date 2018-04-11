(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('viewActivityDetailController', viewActivityDetailController);

    viewActivityDetailController.$inject = ['$scope', '$state', 'projectLectureService'];

    function viewActivityDetailController($scope, $state, projectLectureService) {
        var vm = this;

    }
})();
