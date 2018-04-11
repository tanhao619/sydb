// (function () {
//     'use strict';
//
//     angular
//         .module('sydbApp')
//         .config(stateConfig);
//
//     stateConfig.$inject = ['$stateProvider'];
//
//     function stateConfig($stateProvider) {
//         $stateProvider.state('knowledgeBuy', { //助力创新
//             parent: "assistsInnovation",
//             url: "/knowledgeBuy",
//             active: "assistsInnovation",
//             views:{
//                 'content@':{
//                     templateUrl:"app/assistsInnovation/knowledgeBuy/buyIP.html",
//                     controller:"knowledgeBuyController",
//                     controllerAs:"vm"
//                 }
//             },
//             resolve: {
//                 loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
//                     return $ocLazyLoad.load(LOAD_DEMAND['knowledgeBuy']);
//                 }]
//             }
//         })
//             .state('publishBuyIP', { //发布求购需求
//                 parent: "knowledgeBuy",
//                 url: "/publishBuyIP",
//                 active: "assistsInnovation",
//                 views:{
//                     'content@':{
//                         templateUrl:"app/assistsInnovation/knowledgeBuy/publishBuyIP.html",
//                         controller:"publishBuyIPController",
//                         controllerAs:"vm"
//                     }
//                 },
//                 data:{
//                     title : '知识产权 - 发布知产求购'
//                 },
//                 resolve: {
//                     loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
//                         return $ocLazyLoad.load(LOAD_DEMAND['publishBuyIP']);
//                     }]
//                 }
//             })
//             .state('buyIPDetails', {  //demo页面路由
//                 parent: "knowledgeBuy",
//                 url: "/details/:id",
//                 active: "assistsInnovation",
//                 views: {
//                     'content@': {
//                         templateUrl: "app/assistsInnovation/knowledgeBuy/BuyIPDetails.html",
//                         controller: "BuyIPDetailsController",
//                         controllerAs: "vm"
//                     }
//                 },
//                 data:{
//                     title : '知识产权 - 知产求购 - 知产求购详情'
//                 },
//                 resolve: {
//                     loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
//                         return $ocLazyLoad.load(LOAD_DEMAND['buyIPDetails']);
//                     }]
//                 }
//             })
//     }
// })();
