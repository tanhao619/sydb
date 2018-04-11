(function () {
    'use strict';
    angular
        .module('oddApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {

        $stateProvider.state('logIn', { // 登陆
            url: '/logIn',
            views: {
                'content@': {
                    templateUrl: 'app/content/logIn/logIn.html',
                    controller: 'LogInController',
                    controllerAs: 'vm',
                    resolve: {
                        loadMyCtrl: ['$ocLazyLoad','LOAD_DEMAND', function ($ocLazyLoad,LOAD_DEMAND) {
                            return $ocLazyLoad.load(LOAD_DEMAND['logIn']);
                        }]
                    }
                }
            }
        })
    }
})();