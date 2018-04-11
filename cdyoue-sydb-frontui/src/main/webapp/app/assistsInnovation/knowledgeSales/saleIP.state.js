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
        $stateProvider.state('saleIPDetails', {  //出售詳情页面路由
            parent: "knowledgeSales",
            url: "/details/:type/:id",
            active:'saleIPDetails',
            views: {
                'content@': {
                    templateUrl: "app/assistsInnovation/knowledgeSales/saleIPDetails.html",
                    controller: "SaleIPDetailsController",
                    controllerAs: "vm"
                }
            },
            data:{
                title : '知识产权 - 知产出售'
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND['saleIPDetails']);
                }]
            }
        }).state('publishSaleIP', {  //发布页面路由
            parent: "knowledgeSales",
            url: "/publishSaleIP",
            active:'publishSaleIP',
            views: {
                'content@': {
                    templateUrl: "app/assistsInnovation/knowledgeSales/publishSaleIP.html",
                    controller: "publishSaleIPController",
                    controllerAs: "vm"
                }
            },
            data:{
                title : '知识产权 - 发布知产出售'
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND['publishSaleIP']);
                }]
            }
        })
    }
})();