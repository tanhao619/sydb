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
        $stateProvider.state('publishAss', {  //发布
            parent: "knowledgeAssess",
            url: "/publishAss",
            active: 'publishAss',
            views: {
                'content@': {
                    templateUrl: "app/assistsInnovation/knowledgeAssess/knowledgeAssess.html",
                    controller: "knowledgeAssessController",
                    controllerAs: "vm"
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND['publishAss']);
                }]
            }
        })
    }
})();