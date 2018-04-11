/**
 * Created by PC-45 on 2017/5/17.
 */

/**
 * Created by Administrator on 2017/4/7 0007.
 */
(function() {
    'use strict';

    angular
        .module('sydbApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('personalCenter', {  //demo页面路由
            url: "/personalCenter",
            parent:"app",
            active: "personalCenter",
            views: {
                'content@': {
                    templateUrl: "app/personalCenter/index.html"
                },
                'QYlayout@personalCenter': {
                    templateUrl: "app/personalCenter/layout/layout.html",
                    controller: "layoutsController",
                    controllerAs: "vm"
                },
                'QYPatent@personalCenter': {
                    templateUrl: "app/personalCenter/basicData/basicData.html",
                    controller: "basicDataController",
                    controllerAs: "vm"
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND['personalCenter']);
                }]
            }
        }).state('collections', {  //我的收藏页面路由
            url: "/collections",
            parent:"personalCenter",
            active: "collections",
            views: {
                'QYPatent@personalCenter': {
                    templateUrl: "app/personalCenter/collections/collections.html",
                    controller: "collectionsController",
                    controllerAs: "vm"
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND['collections']);
                }]
            }
        })
            .state('knowledge', {  //我的知产页面路由
                url: "/knowledge",
                parent:"personalCenter",
                active: "knowledge",
                views: {
                    'QYPatent@personalCenter': {
                        templateUrl: "app/personalCenter/knowledge/knowledge.html",
                        controller: "knowledgeController",
                        controllerAs: "vm"
                    }
                },
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                        return $ocLazyLoad.load(LOAD_DEMAND['knowledge']);
                    }]
                }
            }) .state('financing', {  //我的融资页面路由
            url: "/financing",
            parent:"personalCenter",
            active: "financing",
            views: {
                'QYPatent@personalCenter': {
                    templateUrl: "app/personalCenter/financing/financing.html",
                    controller: "financingController",
                    controllerAs: "vm"
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND['financing']);
                }]
            }
        }).state('declare', {  //我的申报页面路由
            url: "/declare",
            parent:"personalCenter",
            active: "declare",
            views: {
                'QYPatent@personalCenter': {
                    templateUrl: "app/personalCenter/declare/declare.html",
                    controller: "declareController",
                    controllerAs: "vm"
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND['declare']);
                }]
            }
        }).state('modifyPassword', {  //修改密码页面路由
            url: "/modifyPassword",
            parent:"personalCenter",
            active: "modifyPassword",
            views: {
                'QYPatent@personalCenter': {
                    templateUrl: "app/personalCenter/modifyPassword/modifyPassword.html",
                    controller: "modifyPasswordController",
                    controllerAs: "vm"
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND['modifyPassword']);
                }]
            }
        }).state('enterpriseCertification', {  //企业认证页面路由
            url: "/enterpriseCertification",
            parent:"personalCenter",
            active: "enterpriseCertification",
            views: {
                'QYPatent@personalCenter': {
                    templateUrl: "app/personalCenter/basicData/enterpriseCertification.html",
                    controller: "enterpriseCertificationController",
                    controllerAs: "vm"
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND['personalCenter']);
                }]
            }
        })
    }
})();