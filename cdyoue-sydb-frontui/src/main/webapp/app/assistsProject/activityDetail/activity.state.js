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
        $stateProvider.state('ActivitiesList', {  //活动列表页面路由
            parent: "assistsProject",
            url: "/ActivitiesList",
            active:'ActivitiesList',
            views: {
                'content@': {
                    templateUrl: "app/assistsProject/activityDetail/activity.html",
                    controller: "ActivitiesController",
                    controllerAs: "vm"
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND['ActivitiesList']);
                }]
            }
        }).state('ActivityDetail', {  //场地详情页面路由
            parent: "ActivitiesList",
            url: "/ActivityDetail/:id",
            active:'ActivityDetail',
            views: {
                'content@': {
                    templateUrl: "app/assistsProject/activityDetail/viewActivityDetail.html",
                    controller: "ActivityDetailController",
                    controllerAs: "vm"
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND['ActivityDetail']);
                }]
            }
        })
    }
})();