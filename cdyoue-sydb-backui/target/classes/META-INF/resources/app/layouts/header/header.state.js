(function () {
    'use strict';

    angular
        .module('oddApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state("personalPanel", {
            parent: 'app',
            url: "/personalPanel",
            active:"personalPanel",
            views: {
                'navbar@': {
                    templateUrl: 'app/layouts/navbar/navbar.html',
                    controller: 'NavbarController',
                    controllerAs: 'vm'
                },
                'content@': {
                    templateUrl: "app/content/personalPanel/home/home.html",
                    controller: "HomeController",
                    controllerAs: "vm"
                }
            },
            resolve: {
                name: function() {
                    return 'personalPanel';
                },
                loadMyCtrl: ['$ocLazyLoad','LOAD_DEMAND', function ($ocLazyLoad,LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND["personalPanel"]);
                }]
            }
        })
            .state('contentManagement', {
            parent: 'app',
            url: '/contentManagement',
            active: 'innovateAdRelease',
            views: {
                'navbar@': {
                    templateUrl: 'app/layouts/navbar/navbar.html',
                    controller: 'NavbarController',
                    controllerAs: 'vm'
                },
                'content@': {
                    templateUrl: "app/content/contentManagement/innovate/innovateAdRelease/innovateAdRelease.html",
                    controller: "InnovateAdReleaseController",
                    controllerAs: "vm"
                }
            },
            resolve: {
                name: function() {
                    return 'contentManagement';
                },
                loadMyCtrl: ['$ocLazyLoad','LOAD_DEMAND', function ($ocLazyLoad,LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND['contentManagement']);
                }]
            }
        })
            .state('backManagement', {
            parent: 'app',
            url: '/backManagement',
            active: 'userInformation',
            views: {
                'navbar@': {
                    templateUrl: 'app/layouts/navbar/navbar.html',
                    controller: 'NavbarController',
                    controllerAs: 'vm'
                },
                'content@': {
                    templateUrl: "app/content/backManagement/portalManagement/userInfoManage/userInfo.html",
                    controller: "UserInfoController",
                    controllerAs: "vm"
                }
            },
            resolve: {
                name: function() {
                    return 'backManagement';
                },
                loadMyCtrl: ['$ocLazyLoad','LOAD_DEMAND', function ($ocLazyLoad,LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND['backManagement']);
                }]
            }
        })
    }
})();
