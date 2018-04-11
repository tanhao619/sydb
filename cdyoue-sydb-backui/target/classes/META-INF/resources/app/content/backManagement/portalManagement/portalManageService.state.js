(function () {
    'use strict';

    angular
        .module('oddApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('authenticationInfo', {
            parent: 'backManagement',
            url: '/authenticationInfo/:id/:role',
            active: 'authenticationInfo',
            views: {
                'content@': {
                    templateUrl: 'app/content/backManagement/portalManagement/userInfoManage/userAuthentication.html',
                    controller: 'UserAuthenticationController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND["authenticationInfo"]);
                }]
            }
        }).state('userInfoUpdate', {
            parent: 'backManagement',
            url: '/userInfoUpdate/:id/:role',
            active: 'userInfoUpdate',
            views: {
                'content@': {
                    templateUrl: 'app/content/backManagement/portalManagement/userInfoManage/userInfoUpdate.html',
                    controller: 'UserInfoUpdateController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND["userInfoUpdate"]);
                }]
            }
        })

    }
})();
