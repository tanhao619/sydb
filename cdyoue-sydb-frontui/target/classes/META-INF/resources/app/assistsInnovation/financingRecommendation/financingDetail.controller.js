/**
 * Created by Administrator on 2017/9/20 0020.
 */
/**
 * Created by PC-45 on 2017/4/20.
 */
(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('financingDetailController', financingDetailController);

    financingDetailController.$inject = ['$scope',  '$state', 'Upload', 'toaster', '$stateParams','financingDetailService'];

    function financingDetailController($scope, $state, Upload, toaster, $stateParams,financingDetailService) {
        var vm = this;

        getFinancingById();
        function getFinancingById() {
            financingDetailService.getFinancingById({id:$stateParams.id}, function (rel) {
                vm.financingMessage = rel.response;
            }, function (err) {

            })
        }
        

    }

})
();
