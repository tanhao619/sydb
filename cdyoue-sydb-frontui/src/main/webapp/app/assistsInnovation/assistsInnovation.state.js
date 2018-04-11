(function () {
    'use strict';

    angular
        .module('sydbApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('financingRecommendation', { //融资推荐详情
            parent: "assistsInnovation",
            url: "/financingRecommendation",
            active: "assistsInnovation",
            views:{
                'content@':{
                    templateUrl:"app/assistsInnovation/financingRecommendation/financingRecommendation.html",
                    controller:"financingRecommendationController",
                    controllerAs:"vm"
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND['financingRecommendation']);
                }]
            }
        }).state('deviceLibrary', { //设备库详情
            parent: "assistsInnovation",
            url: "/deviceLibrary",
            active: "assistsInnovation",
            views:{
                'content@':{
                    templateUrl:"app/assistsInnovation/deviceLibrary/deviceLibrary.html",
                    controller:"deviceLibraryController",
                    controllerAs:"vm"
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND['deviceLibrary']);
                }]
            }
        })
            .state('projectInterpretation', { //项目解读
                parent: "assistsInnovation",
                url: "/projectInterpretation",
                active: "assistsInnovation",
                views:{
                    'content@':{
                        templateUrl:"app/assistsInnovation/projectInterpretation/projectInterpretation.html",
                        controller:"projectInterpretationController",
                        controllerAs:"vm"
                    }
                },
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                        return $ocLazyLoad.load(LOAD_DEMAND['projectInterpretation']);
                    }]
                }
            })
            .state('projectLecture', { //项目讲座
                parent: "assistsInnovation",
                url: "/projectLecture",
                active: "assistsInnovation",
                views:{
                    'content@':{
                        templateUrl:"app/assistsInnovation/projectLecture/projectLecture.html",
                        controller:"projectLectureController",
                        controllerAs:"vm"
                    }
                },
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                        return $ocLazyLoad.load(LOAD_DEMAND['projectLecture']);
                    }]
                }
            })
            .state('expertInterpretation', { //专家推荐
            parent: "assistsInnovation",
            url: "/expertInterpretation",
            active: "assistsInnovation",
            views:{
                'content@':{
                    templateUrl:"app/assistsInnovation/expertInterpretation/expertInterpretation.html",
                    controller:"expertInterpretationController",
                    controllerAs:"vm"
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND['expertInterpretation']);
                }]
            }
        })
            .state('expertAchievement', { //专家成果
                parent: "assistsInnovation",
                url: "/expertAchievement",
                active: "assistsInnovation",
                views:{
                    'content@':{
                        templateUrl:"app/assistsInnovation/expertAchievement/expertAchievement.html",
                        controller:"expertAchievementController",
                        controllerAs:"vm"
                    }
                },
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                        return $ocLazyLoad.load(LOAD_DEMAND['expertAchievement']);
                    }]
                }
            })
            .state('projectDeclaration', { //项目申报
                parent: "assistsInnovation",
                url: "/projectDeclaration",
                active: "assistsInnovation",
                views:{
                    'content@':{
                        templateUrl:"app/assistsInnovation/projectDeclaration/projectDeclaration.html",
                        controller:"projectDeclarationController",
                        controllerAs:"vm"
                    }
                },
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                        return $ocLazyLoad.load(LOAD_DEMAND['projectDeclaration']);
                    }]
                }
            })
            .state('professionalInterpretation', { //专业解读
                parent: "assistsInnovation",
                url: "/professionalInterpretation",
                active: "assistsInnovation",
                views:{
                    'content@':{
                        templateUrl:"app/assistsInnovation/professionalInterpretation/professionalInterpretation.html",
                        controller:"professionalInterpretationController",
                        controllerAs:"vm"
                    }
                },
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                        return $ocLazyLoad.load(LOAD_DEMAND['professionalInterpretation']);
                    }]
                }
            })
            .state('publishNeed', { //融资申请
                parent: "financingRecommendation",
                url: "/publishNeed",
                active: "assistsInnovation",
                views:{
                    'content@':{
                        templateUrl:"app/assistsInnovation/financingRecommendation/publishNeed.html",
                        controller:"publishNeedController",
                        controllerAs:"vm"
                    }
                },
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                        return $ocLazyLoad.load(LOAD_DEMAND['publishNeed']);
                    }]
                }
            })
            .state('financingDetail', { //技术融资详情
                parent: "financingRecommendation",
                url: "/financingDetail/:id",
                active: "assistsInnovation",
                views:{
                    'content@':{
                        templateUrl:"app/assistsInnovation/financingRecommendation/financingDetail.html",
                        controller:"financingDetailController",
                        controllerAs:"vm"
                    }
                },
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                        return $ocLazyLoad.load(LOAD_DEMAND['financingDetail']);
                    }]
                }
            })
            .state('deviceLibraryDetail', { //设备详情
                parent: "deviceLibrary",
                url: "/deviceLibraryDetail/:id",
                active: "assistsInnovation",
                views:{
                    'content@':{
                        templateUrl:"app/assistsInnovation/deviceLibrary/deviceLibraryDetail.html",
                        controller:"deviceLibraryDetailController",
                        controllerAs:"vm"
                    }
                },
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                        return $ocLazyLoad.load(LOAD_DEMAND['deviceLibraryDetail']);
                    }]
                }
            })
            .state('projectInterpretationDetail', { //项目解读详情
                parent: "projectInterpretation",
                url: "/projectInterpretationDetail/:id",
                active: "assistsInnovation",
                views:{
                    'content@':{
                        templateUrl:"app/assistsInnovation/projectInterpretation/projectInterpretationDetail.html",
                        controller:"projectInterpretationDetailController",
                        controllerAs:"vm"
                    }
                },
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                        return $ocLazyLoad.load(LOAD_DEMAND['projectInterpretationDetail']);
                    }]
                }
            })
            .state('projectLectureDetail', { //项目讲座详情
                parent: "projectLecture",
                url: "/projectLectureDetail/:id",
                active: "assistsInnovation",
                views:{
                    'content@':{
                        templateUrl:"app/assistsInnovation/projectLecture/projectLectureDetail.html",
                        controller:"projectLectureDetailController",
                        controllerAs:"vm"
                    }
                },
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                        return $ocLazyLoad.load(LOAD_DEMAND['projectLectureDetail']);
                    }]
                }
            })
            .state('projectDeclarationDetail', { //申报详情页
                parent: "projectDeclaration",
                url: "/projectDeclarationDetail/:id",
                active: "assistsInnovation",
                views:{
                    'content@':{
                        templateUrl:"app/assistsInnovation/projectDeclaration/projectDeclarationDetail.html",
                        controller:"projectDeclarationDetailController",
                        controllerAs:"vm"
                    }
                },
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                        return $ocLazyLoad.load(LOAD_DEMAND['projectDeclarationDetail']);
                    }]
                }
            })
            .state('professionalInterpretationDetail', { //专家解读详情页
                parent: "professionalInterpretation",
                url: "/professionalInterpretationDetail/:id",
                active: "assistsInnovation",
                views:{
                    'content@':{
                        templateUrl:"app/assistsInnovation/professionalInterpretation/professionalInterpretationDetail.html",
                        controller:"professionalInterpretationDetailController",
                        controllerAs:"vm"
                    }
                },
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                        return $ocLazyLoad.load(LOAD_DEMAND['professionalInterpretationDetail']);
                    }]
                }
            })
            .state('expertAchievementDetail', { //专家成果详情页
                parent: "expertAchievement",
                url: "/expertAchievementDetail/:id",
                active: "assistsInnovation",
                views:{
                    'content@':{
                        templateUrl:"app/assistsInnovation/expertAchievement/expertAchievementDetail.html",
                        controller:"expertAchievementDetailController",
                        controllerAs:"vm"
                    }
                },
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                        return $ocLazyLoad.load(LOAD_DEMAND['expertAchievementDetail']);
                    }]
                }
            })
            .state('expertInterpretationDetail', { //专家库详情页
                parent: "expertInterpretation",
                url: "/expertInterpretationDetail/:id",
                active: "assistsInnovation",
                views:{
                    'content@':{
                        templateUrl:"app/assistsInnovation/expertInterpretation/expertInterpretationDetail.html",
                        controller:"expertInterpretationDetailController",
                        controllerAs:"vm"
                    }
                },
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                        return $ocLazyLoad.load(LOAD_DEMAND['expertInterpretationDetail']);
                    }]
                }
            })
            .state('knowledgeAssess', { //知产评估
                parent: "assistsInnovation",
                url: "/knowledgeAssess",
                active: "assistsInnovation",
                views:{
                    'content@':{
                        templateUrl:"app/assistsInnovation/knowledgeAssess/knowledgeAssess.html",
                        controller:"knowledgeAssessController",
                        controllerAs:"vm"
                    }
                },
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                        return $ocLazyLoad.load(LOAD_DEMAND['knowledgeAssess']);
                    }]
                }
            })

    }

})();
