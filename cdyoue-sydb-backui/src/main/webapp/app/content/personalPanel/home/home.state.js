(function() {
    'use strict';

    angular
        .module('oddApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('home', {
            parent: 'app',
            url: '/',
            views: {
                'content@': {
                    templateUrl: 'app/content/home/home.html',
                    controller: 'HomeController',
                    controllerAs: 'vm'
                }
            }
        })
    }
})();
