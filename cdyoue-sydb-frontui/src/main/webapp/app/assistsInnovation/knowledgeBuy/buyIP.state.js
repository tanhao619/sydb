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
        $stateProvider.state('buyIP', {  //求购列表
            parent: "assistsInnovation",
            url: "/buyIP",
            active: 'buyIP',
            views: {
                'content@': {
                    templateUrl: "app/assistsInnovation/knowledgeBuy/buyIP.html",
                    controller: "BuyIPController",
                    controllerAs: "vm"
                }
            },
            data:{
                title : '知识产权 - 知产求购'
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND['buyIP']);
                }]
            }
        }).state('buyIPDetails', {  //求购详情
            parent: "buyIP",
            url: "/details/:id",
            views: {
                'content@': {
                    templateUrl: "app/assistsInnovation/knowledgeBuy/BuyIPDetails.html",
                    controller: "BuyIPDetailsController",
                    controllerAs: "vm"
                }
            },
            data:{
                title : '知识产权 - 知产求购 - 知产求购详情'
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND['buyIPDetails']);
                }]
            }
        }).state('publishBuyIP', {  //发布求购
            parent: "buyIP",
            url: "/publish",
            views: {
                'content@': {
                    templateUrl: "app/assistsInnovation/knowledgeBuy/publishBuyIP.html",
                    controller: "PublishBuyIPController",
                    controllerAs: "vm"
                }
            },
            data:{
                title : '知识产权 - 发布知产求购'
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND['publishBuyIP']);
                }]
            }
        }).state('buyPublishInfo', {  //demo页面路由
            parent: "buyIP",
            url: "/:status",
            views: {
                'header@': {
                    templateUrl: 'app/layouts/header/header1.html',
                    controller: 'HeaderController',
                    controllerAs: 'vm'
                },
                'content@': {
                    templateUrl: "app/assistsInnovation/knowledgeBuy/publishInfo.html",
                    controller: "BuyPublishInfoController",
                    controllerAs: "vm"
                }
            },
            data:{
                title : '知识产权 - 知产求购'
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND['buyPublishInfo']);
                }]
            }
        })
    }
})();