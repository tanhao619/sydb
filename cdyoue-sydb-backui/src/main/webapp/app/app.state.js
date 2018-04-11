(function() {
    'use strict';

    angular
        .module('oddApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('app', {
            abstract: true,
            views: {
                'header': {
                    templateUrl: 'app/layouts/header/header.html',
                    controller: 'HeaderController',
                    controllerAs: 'vm'
                }
            }
        });
    }
})();