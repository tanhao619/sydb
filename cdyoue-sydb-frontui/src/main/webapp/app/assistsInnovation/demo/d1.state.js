(function() {
    'use strict';

    angular
        .module('sydbApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('demoNew', {  //demo页面路由
            parent: 'assistsInnovation',
            url: '/demo',
            views: {
                'content@': {
                    templateUrl: 'app/demo/d1/d1.html',
                    controller: 'DemoNewController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND['demoNew']);
                }]
            }
        });
    }
})();