(function () {
    'use strict';

    angular
        .module('oddApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('modifyPersonalInfo', {
            parent: 'personalPanel',
            url: '/modifyPersonalInfo',
            active: 'modifyPersonalInfo',
            views: {
                'content@': {
                    templateUrl: 'app/content/personalPanel/modifyPersonalInfo/modifyPersonalInfo.html',
                    controller: 'ModifyPersonalInfo',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND["modifyPersonalInfo"]);
                }]
            }
        })
            .state('modifyPassword', {
            parent: "personalPanel",
            url: "/modifyPassword",
            active: 'modifyPassword',
            views: {
                'content@': {
                    templateUrl: "app/content/personalPanel/modifyPersonalInfo/modifyPassword.html",
                    controller: "ModifyPassword",
                    controllerAs: "vm"
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND["modifyPassword"]);
                }]
            }
        })

        //  内容管理
        // 助力创新内容管理
        .state('innovateAdRelease', {
            parent: "contentManagement",
            url: "/innovateAdRelease",
            active: 'innovateAdRelease',
            views: {
                'content@': {
                    templateUrl: "app/content/contentManagement/innovate/innovateAdRelease/innovateAdRelease.html",
                    controller: "InnovateAdReleaseController",
                    controllerAs: "vm"
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND["contentManagement"]);
                }]
            }
        })
            .state('equipment', {
            parent: "contentManagement",
            url: "/equipment",
            active: 'equipment',
            views: {
                'content@': {
                    templateUrl: "app/content/contentManagement/innovate/equipment/equipment.html",
                    controller: "EquipmentController",
                    controllerAs: "vm"
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND["equipment"]);
                }]
            }
        })
            .state('experts', {
            parent: "contentManagement",
            url: "/experts",
            active: 'experts',
            views: {
                'content@': {
                    templateUrl: "app/content/contentManagement/innovate/experts/experts.html",
                    controller: "ExpertsController",
                    controllerAs: "vm"
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND["experts"]);
                }]
            }
        })
            .state('fruits', {
            parent: "contentManagement",
            url: "/fruits",
            active: 'fruits',
            views: {
                'content@': {
                    templateUrl: "app/content/contentManagement/innovate/fruits/fruits.html",
                    controller: "FruitsController",
                    controllerAs: "vm"
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND["fruits"]);
                }]
            }
        })
            .state('innovateKnowledgeAssess', {
            parent: "contentManagement",
            url: "/innovateKnowledgeAssess",
            active: 'innovateKnowledgeAssess',
            views: {
                'content@': {
                    templateUrl: "app/content/contentManagement/innovate/innovateKnowledgeAssess/innovateKnowledgeAssess.html",
                    controller: "InnovateKnowledgeAssessController",
                    controllerAs: "vm"
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND["innovateKnowledgeAssess"]);
                }]
            }
        })
            .state('innovateProjectDeclare', {
            parent: "contentManagement",
            url: "/innovateProjectDeclare",
            active: 'innovateProjectDeclare',
            views: {
                'content@': {
                    templateUrl: "app/content/contentManagement/innovate/innovateProjectDeclare/innovateProjectDeclare.html",
                    controller: "InnovateProjectDeclareController",
                    controllerAs: "vm"
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND["innovateProjectDeclare"]);
                }]
            }
        })
            .state('innovateStairRecommend', {
            parent: "contentManagement",
            url: "/innovateStairRecommend",
            active: 'innovateStairRecommend',
            views: {
                'content@': {
                    templateUrl: "app/content/contentManagement/innovate/innovateStairRecommend/innovateStairRecommend.html",
                    controller: "InnovateStairRecommendController",
                    controllerAs: "vm"
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND["innovateStairRecommend"]);
                }]
            }
        })
            .state('knowledgeBuy', {
            parent: "contentManagement",
            url: "/knowledgeBuy",
            active: 'knowledgeBuy',
            views: {
                'content@': {
                    templateUrl: "app/content/contentManagement/innovate/knowledgeBuy/knowledgeBuy.html",
                    controller: "KnowledgeBuyController",
                    controllerAs: "vm"
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND["knowledgeBuy"]);
                }]
            }
        })
            .state('knowledgeSell', {
            parent: "contentManagement",
            url: "/knowledgeSell",
            active: 'knowledgeSell',
            views: {
                'content@': {
                    templateUrl: "app/content/contentManagement/innovate/knowledgeSell/knowledgeSell.html",
                    controller: "KnowledgeSellController",
                    controllerAs: "vm"
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND["knowledgeSell"]);
                }]
            }
        })
            .state('lecture', {
            parent: "contentManagement",
            url: "/lecture",
            active: 'lecture',
            views: {
                'content@': {
                    templateUrl: "app/content/contentManagement/innovate/lecture/lecture.html",
                    controller: "LectureController",
                    controllerAs: "vm"
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND["lecture"]);
                }]
            }
        })
            .state('projectUnscramble', {
            parent: "contentManagement",
            url: "/projectUnscramble",
            active: 'projectUnscramble',
            views: {
                'content@': {
                    templateUrl: "app/content/contentManagement/innovate/projectUnscramble/projectUnscramble.html",
                    controller: "ProjectUnscrambleController",
                    controllerAs: "vm"
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND["projectUnscramble"]);
                }]
            }
        })
            .state('unscramble', {
            parent: "contentManagement",
            url: "/unscramble",
            active: 'unscramble',
            views: {
                'content@': {
                    templateUrl: "app/content/contentManagement/innovate/unscramble/unscramble.html",
                    controller: "UnscrambleController",
                    controllerAs: "vm"
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND["unscramble"]);
                }]
            }
        })
            .state('technologyFinancing', {
            parent: "contentManagement",
            url: "/technologyFinancing",
            active: 'technologyFinancing',
            views: {
                'content@': {
                    templateUrl: "app/content/contentManagement/innovate/technologyFinancing/technologyFinancing.html",
                    controller: "TechnologyFinancingController",
                    controllerAs: "vm"
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND["technologyFinancing"]);
                }]
            }
        })
            // 助力创业内容管理
            .state('adRelease', {
            parent: "contentManagement",
            url: "/adRelease",
            active: 'adRelease',
            views: {
                'content@': {
                    templateUrl: "app/content/contentManagement/business/adRelease/adRelease.html",
                    controller: "AdReleaseController",
                    controllerAs: "vm"
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND["adRelease"]);
                }]
            }
        })
            .state('settledEnterprise', {
            parent: "contentManagement",
            url: "/settledEnterprise",
            active: 'settledEnterprise',
            views: {
                'content@': {
                    templateUrl: "app/content/contentManagement/business/settledEnterprise/settledEnterprise.html",
                    controller: "SettledEnterpriseController",
                    controllerAs: "vm"
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND["settledEnterprise"]);
                }]
            }
        })
            .state('site', {
            parent: "contentManagement",
            url: "/site",
            active: 'site',
            views: {
                'content@': {
                    templateUrl: "app/content/contentManagement/business/site/site.html",
                    controller: "SiteController",
                    controllerAs: "vm"
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND["site"]);
                }]
            }
        })
            .state('stairRecommend', {
            parent: "contentManagement",
            url: "/stairRecommend",
            active: 'stairRecommend',
            views: {
                'content@': {
                    templateUrl: "app/content/contentManagement/business/stairRecommend/stairRecommend.html",
                    controller: "StairRecommendController",
                    controllerAs: "vm"
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND["stairRecommend"]);
                }]
            }
        })
            .state('trainingInstitution', {
            parent: "contentManagement",
            url: "/trainingInstitution",
            active: 'trainingInstitution',
            views: {
                'content@': {
                    templateUrl: "app/content/contentManagement/business/trainingInstitution/trainingInstitution.html",
                    controller: "TrainingInstitutionController",
                    controllerAs: "vm"
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND["trainingInstitution"]);
                }]
            }
        })
            .state('url', {
            parent: "contentManagement",
            url: "/url",
            active: 'url',
            views: {
                'content@': {
                    templateUrl: "app/content/contentManagement/business/url/url.html",
                    controller: "UrlController",
                    controllerAs: "vm"
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND["url"]);
                }]
            }
        })
            .state('roadshow', {
            parent: "contentManagement",
            url: "/roadshow",
            active: 'roadshow',
            views: {
                'content@': {
                    templateUrl: "app/content/contentManagement/business/roadshow/roadshow.html",
                    controller: "RoadshowController",
                    controllerAs: "vm"
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND["roadshow"]);
                }]
            }
        })
            // 注册用户咨询管理
            .state('knowledgeAssess', {
            parent: "contentManagement",
            url: "/knowledgeAssess",
            active: 'knowledgeAssess',
            views: {
                'content@': {
                    templateUrl: "app/content/contentManagement/consultation/knowledgeAssess/knowledgeAssess.html",
                    controller: "KnowledgeAssessController",
                    controllerAs: "vm"
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND["knowledgeAssess"]);
                }]
            }
        })
            .state('contactExperts', {
            parent: "contentManagement",
            url: "/contactExperts",
            active: 'contactExperts',
            views: {
                'content@': {
                    templateUrl: "app/content/contentManagement/consultation/contactExperts/contactExperts.html",
                    controller: "ContactExpertsController",
                    controllerAs: "vm"
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND["contactExperts"]);
                }]
            }
        })
            .state('applyTutors', {
            parent: "contentManagement",
            url: "/applyTutors",
            active: 'applyTutors',
            views: {
                'content@': {
                    templateUrl: "app/content/contentManagement/consultation/applyTutors/applyTutors.html",
                    controller: "ApplyTutorsController",
                    controllerAs: "vm"
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND["applyTutors"]);
                }]
            }
        })
            .state('applyEquipment', {
            parent: "contentManagement",
            url: "/applyEquipment",
            active: 'applyEquipment',
            views: {
                'content@': {
                    templateUrl: "app/content/contentManagement/consultation/applyEquipment/applyEquipment.html",
                    controller: "ApplyEquipmentController",
                    controllerAs: "vm"
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND["applyEquipment"]);
                }]
            }
        })
            .state('applyBase', {
            parent: "contentManagement",
            url: "/applyBase",
            active: 'applyBase',
            views: {
                'content@': {
                    templateUrl: "app/content/contentManagement/consultation/applyBase/applyBase.html",
                    controller: "ApplyBaseController",
                    controllerAs: "vm"
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND["applyBase"]);
                }]
            }
        })
            .state('projectDeclare', {
            parent: "contentManagement",
            url: "/projectDeclare",
            active: 'projectDeclare',
            views: {
                'content@': {
                    templateUrl: "app/content/contentManagement/consultation/projectDeclare/projectDeclare.html",
                    controller: "ProjectDeclareController",
                    controllerAs: "vm"
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND["projectDeclare"]);
                }]
            }
        })

        //  后台管理
        .state('userInformation', {
            parent: "backManagement",
            url: "/userInformation",
            active: 'userInformation',
            views: {
                'content@': {
                    templateUrl: "app/content/backManagement/portalManagement/userInfoManage/userInfo.html",
                    controller: "UserInfoController",
                    controllerAs: "vm"
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND["userInformation"]);
                }]
            }
        })
            .state('userAuthority', {
            parent: "backManagement",
            url: "/userAuthority",
            active: 'userAuthority',
            views: {
                'content@': {
                    templateUrl: "app/content/backManagement/portalManagement/permissionsManage/userAuthority.html",
                    controller: "UserAuthorityController",
                    controllerAs: "vm"
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND["userAuthority"]);
                }]
            }
        })
    }
})();
