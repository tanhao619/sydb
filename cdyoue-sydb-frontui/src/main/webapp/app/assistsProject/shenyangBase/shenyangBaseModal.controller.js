/**
 * Created by sky on 2017/10/13.
 */
(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('shenyangBaseModalController', shenyangBaseModalController);

    shenyangBaseModalController.$inject = ['$scope', '$state', 'shenyangBaseService','Upload','SYDB','toaster','$uibModalInstance','UserToken'];

    function shenyangBaseModalController($scope, $state, shenyangBaseService,Upload,SYDB,toaster,$uibModalInstance,UserToken) {
        var vm = this;
         vm.cancel = cancel;


        function cancel() {
            $uibModalInstance.dismiss('cancel');
        }
    }
})();