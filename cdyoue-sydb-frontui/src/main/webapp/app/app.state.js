(function() {
    'use strict';

    angular
        .module('sydbApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('app', {
            abstract: true,
            views: {
                'navbar@': {
                    templateUrl: 'layouts/navbar/navbar.html',
                    controller: 'NavbarController',
                    controllerAs: 'vm'
                },
                'foot@': {
                    templateUrl: 'layouts/footer/foot.html',
                    controller: 'FootController',
                    controllerAs: 'vm'
                }
            }
        });
    }
})();