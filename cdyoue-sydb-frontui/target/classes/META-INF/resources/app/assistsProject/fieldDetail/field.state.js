/**
 * Created by PC-45 on 2017/4/21.
 */
(function () {
    'use strict';

    angular
        .module('sydbApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('fieldsList', {  //场地列表页面路由
            parent: "assistsProject",
            url: "/fieldsList",
            active:'fieldsList',
            views: {
                'content@': {
                    templateUrl: "/app/assistsProject/fieldDetail/field.html",
                    controller: "FieldsController",
                    controllerAs: "vm"
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND['fieldsList']);
                }]
            }
        }).state('fieldDetail', {  //场地详情页面路由
            parent: "fieldsList",
            url: "/fieldDetail/:id",
            active:'fieldDetail',
            views: {
                'content@': {
                    templateUrl: "app/assistsProject/fieldDetail/fieldDetail.html",
                    controller: "fieldDetailController",
                    controllerAs: "vm"
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND['fieldDetail']);
                }]
            }
        })
    }
})();