(function(){
    'use strict';
    angular.module('sydbApp')
        .controller('ActivityDetailController',ActivityDetailController);

    ActivityDetailController.$inject=['$http','ActivityService','$state','$stateParams'];

    function ActivityDetailController($http,ActivityService,$state,$stateParams) {
        var vm = this;
        getPlaceDetail();

        function getPlaceDetail() {
            ActivityService.getActivityDetail({id:$stateParams.id},function (rel) {
                // console.log(rel);
                vm.place = rel.response;
            })
        }
    }
})();