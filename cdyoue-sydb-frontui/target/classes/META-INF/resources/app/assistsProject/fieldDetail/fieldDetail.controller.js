(function(){
    'use strict';
    angular.module('sydbApp')
        .controller('fieldDetailController',fieldDetailController);

    fieldDetailController.$inject=['$scope','FieldService','$stateParams'];

    function fieldDetailController($scope,FieldService,$stateParams){
        var vm=this;
        getPlaceDetail();

        function getPlaceDetail() {
            FieldService.getFieldDetail({id:$stateParams.id},function (rel) {
                // console.log(rel);
                vm.place = rel.response;
            })
        }
    }
})();