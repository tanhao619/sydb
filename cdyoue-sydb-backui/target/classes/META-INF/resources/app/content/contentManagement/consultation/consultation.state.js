(function () {
    'use strict';

    angular
        .module('oddApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('dataAnalysisDetail', {
            parent: 'businessManagement',
            url: '/dataAnalysisDetail/:id/:isEditor',
            active: 'dataAnalysisDetail',
            views: {
                'content@': {
                    templateUrl: 'app/content/businessManagement/communityContent/dataAnalysis/dataAnalysisDetailModal.html',
                    controller: 'DataAnalysisDetailModalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND["dataAnalysisDetail"]);
                }]
            }
        }).state('separatePageReleaseDetail', {
            parent: 'businessManagement',
            url: '/separatePageReleaseDetail/:id/:isEditor',
            active: 'separatePageReleaseDetail',
            views: {
                'content@': {
                    templateUrl: 'app/content/businessManagement/communityContent/separatePageRelease/separatePageReleaseDetailModal.html',
                    controller: 'SeparatePageReleaseDetailModalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND["separatePageReleaseDetail"]);
                }]
            }
        })
    }
})();
