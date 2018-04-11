(function () {
    'use strict';

    angular
        .module('sydbApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('assistsProject', { //助力创业
            parent: "app",
            url: "/assistsProject",
            active: "assistsProject",
            views:{
                'content@':{
                    templateUrl:"app/assistsProject/index/index.html",
                    controller:"assistsProjectController",
                    controllerAs:"vm"
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND['assistsProject']);
                }]
            }
        })
            .state('trainingDetail', { //培训机构
                parent: "assistsProject",
                url: "/trainingDetail",
                active: "assistsProject",
                views:{
                    'content@':{
                        templateUrl:"app/assistsProject/trainingDetail/trainingDetail.html",
                        controller:"trainingDetailController",
                        controllerAs:"vm"
                    }
                },
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                        return $ocLazyLoad.load(LOAD_DEMAND['trainingDetail']);
                    }]
                }
            })
            .state('viewTrainingDetail', { //培训机构详情
                parent: "trainingDetail",
                url: "/viewTrainingDetail/:id",
                active: "assistsProject",
                views:{
                    'content@':{
                        templateUrl:"app/assistsProject/trainingDetail/viewTrainingDetail.html",
                        controller:"viewTrainingDetailController",
                        controllerAs:"vm"
                    }
                },
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                        return $ocLazyLoad.load(LOAD_DEMAND['viewTrainingDetail']);
                    }]
                }
            })
            .state('activityDetail', { //路演活动
                parent: "assistsProject",
                url: "/activityDetail",
                active: "assistsProject",
                views:{
                    'content@':{
                        templateUrl:"app/assistsProject/activityDetail/activityDetail.html",
                        controller:"activityDetailController",
                        controllerAs:"vm"
                    }
                },
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                        return $ocLazyLoad.load(LOAD_DEMAND['activityDetail']);
                    }]
                }
            })
            .state('viewActivityDetail', { //路演活动详情
                parent: "activityDetail",
                url: "/viewActivityDetail",
                active: "assistsProject",
                views:{
                    'content@':{
                        templateUrl:"app/assistsProject/activityDetail/viewActivityDetail.html",
                        controller:"viewActivityDetailController",
                        controllerAs:"vm"
                    }
                },
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                        return $ocLazyLoad.load(LOAD_DEMAND['viewActivityDetail']);
                    }]
                }
            })
            .state('projectGuidance', { //创业指导
                parent: "assistsProject",
                url: "/projectGuidance",
                active: "assistsProject",
                views:{
                    'content@':{
                        templateUrl:"app/assistsProject/projectGuidance/projectGuidance.html",
                        controller:"projectGuidanceController",
                        controllerAs:"vm"
                    }
                },
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                        return $ocLazyLoad.load(LOAD_DEMAND['projectGuidance']);
                    }]
                }
            })
         .state('shenyangBase', { //基地介绍
            parent: "assistsProject",
            url: "/shenyangBase",
            active: "assistsProject",
            views:{
                'content@':{
                    templateUrl:"app/assistsProject/shenyangBase/shenyangBase.html",
                    controller:"shenyangBaseController",
                    controllerAs:"vm"
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND['shenyangBase']);
                }]
            }
        })
            .state('settledEnterprise', { //入驻企业
                parent: "assistsProject",
                url: "/settledEnterprise",
                active: "assistsProject",
                views:{
                    'content@':{
                        templateUrl:"app/assistsProject/settledEnterprise/settledEnterprise.html",
                        controller:"settledEnterpriseController",
                        controllerAs:"vm"
                    }
                },
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                        return $ocLazyLoad.load(LOAD_DEMAND['settledEnterprise']);
                    }]
                }
            })
            .state('settledEnterpriseDetail', { //入驻企业详情
                parent: "settledEnterprise",
                url: "/settledEnterprise/:id",
                active: "assistsProject",
                views:{
                    'content@':{
                        templateUrl:"app/assistsProject/settledEnterprise/settledEnterpriseDetail.html",
                        controller:"settledEnterpriseDetailController",
                        controllerAs:"vm"
                    }
                },
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                        return $ocLazyLoad.load(LOAD_DEMAND['settledEnterpriseDetail']);
                    }]
                }
            })

    }
})();
