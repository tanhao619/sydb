/**
 * Created by Administrator on 2017/9/19 0019.
 */
(function () {
    'use strict';

    angular
        .module('sydbApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('knowledgeSales', { //知产出售
            parent: "assistsInnovation",
            url: "/knowledgeSales",
            active: "knowledgeSales",
            views:{
                'content@':{
                    templateUrl:"app/assistsInnovation/knowledgeSales/saleIP.html",
                    controller:"knowledgeSalesController",
                    controllerAs:"vm"
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND['knowledgeSales']);
                }]
            }
        })
    }
})();
