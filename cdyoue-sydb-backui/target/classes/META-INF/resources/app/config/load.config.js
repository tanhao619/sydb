(function () {
    'use strict';
    //配置路由
    angular.module('oddApp')
        .config(['$controllerProvider', '$compileProvider', '$filterProvider', '$provide', function ($controllerProvider, $compileProvider, $filterProvider, $provide) {
            var myApp = angular.module('oddApp');
            myApp.controller = $controllerProvider.register;
            myApp.directive = $compileProvider.directive;
            myApp.filter = $filterProvider.register;
            myApp.factory = $provide.factory;
            myApp.service = $provide.service;
            myApp.constant = $provide.constant;
            myApp.value = $provide.value;
        }])
        .factory("LOAD_DEMAND", LOAD_DEMAND);
    function LOAD_DEMAND() {
        return {
            // 登录
            "logIn": [
                'app/content/logIn/logIn.controller.js',
                'app/content/logIn/logIn.service.js'
            ],

            // 个人面板
            "personalPanel": [
                'app/content/personalPanel/modifyPersonalInfo/modifyPersonalInfo.service.js',
                'app/content/personalPanel/home/home.controller.js',
                'app/content/personalPanel/home/home.state.js'
            ],
            "modifyPersonalInfo": [
                'app/content/personalPanel/modifyPersonalInfo/modifyPersonalInfo.service.js',
                'app/content/personalPanel/modifyPersonalInfo/modifyPersonalInfo.controller.js'
            ],
            "modifyPassword": [
                'app/content/personalPanel/modifyPersonalInfo/modifyPersonalInfo.service.js',
                'app/content/personalPanel/modifyPersonalInfo/modifyPassword.controller.js'
            ],

            // 内容管理
            // 助力创新内容管理
            'contentManagement':[
                'app/content/contentManagement/innovate/innovate.service.js',
                'app/content/contentManagement/business/business.service.js',
                'app/content/contentManagement/consultation/consultation.service.js',
                'app/content/contentManagement/innovate/innovateAdRelease/innovateAdRelease.controller.js',
                'app/content/contentManagement/innovate/innovateAdRelease/innovateAdReleaseModal.controller.js'
            ],
            'experts':[
                'app/content/contentManagement/innovate/experts/experts.controller.js',
                'app/content/contentManagement/innovate/experts/expertsModal.controller.js',
                'app/content/contentManagement/innovate/experts/topModal.controller.js'
            ],
            'equipment':[
                'app/content/contentManagement/innovate/equipment/equipment.controller.js',
                'app/content/contentManagement/innovate/equipment/equipmentModal.controller.js',
                'app/content/contentManagement/innovate/equipment/equipmentEditor.controller.js',
                'app/content/contentManagement/innovate/equipment/equipmentTopModal.controller.js',
                'app/content/contentManagement/innovate/equipment/equipmentTopCoverModal.controller.js',
                'app/content/contentManagement/innovate/equipment/equipmentAdd.controller.js'
            ],
            'innovateStairRecommend':[
                'app/content/contentManagement/innovate/innovateStairRecommend/innovateStairRecommend.controller.js',
                'app/content/contentManagement/innovate/innovateStairRecommend/innovateStairRecommendModal.controller.js'
            ],
            'innovateProjectDeclare':[
                'app/content/contentManagement/innovate/innovateProjectDeclare/innovateProjectDeclare.controller.js',
                'app/content/contentManagement/innovate/innovateProjectDeclare/innovateProjectDeclareModal.controller.js'
            ],
            'innovateKnowledgeAssess':[
                'app/content/contentManagement/innovate/innovateKnowledgeAssess/innovateKnowledgeAssess.controller.js',
                'app/content/contentManagement/innovate/innovateKnowledgeAssess/innovateKnowledgeAssessModal.controller.js',
                'app/content/contentManagement/innovate/innovateKnowledgeAssess/innovateKnowledgeAssessUpdate.controller.js'

            ],
            'fruits':[
                'app/content/contentManagement/innovate/fruits/fruits.controller.js',
                'app/content/contentManagement/innovate/fruits/fruitsModal.controller.js',
                'app/content/contentManagement/innovate/fruits/topModal.controller.js'
            ],
            'knowledgeBuy':[
                'app/content/contentManagement/innovate/knowledgeBuy/knowledgeBuy.controller.js',
                'app/content/contentManagement/innovate/knowledgeBuy/knowledgeBuyModal.controller.js',
                'app/content/contentManagement/innovate/knowledgeBuy/topModal.controller.js'
            ],
            'knowledgeSell':[
                'app/content/contentManagement/innovate/knowledgeSell/knowledgeSell.controller.js',
                'app/content/contentManagement/innovate/knowledgeSell/knowledgeSellModal.controller.js',
                'app/content/contentManagement/innovate/knowledgeSell/topModal.controller.js'
            ],
            'lecture':[
                'app/content/contentManagement/innovate/lecture/lecture.controller.js',
                'app/content/contentManagement/innovate/lecture/lectureModal.controller.js',
                'app/content/contentManagement/innovate/lecture/lectureTopModal.controller.js'
            ],
            'projectUnscramble':[
                'app/content/contentManagement/innovate/projectUnscramble/projectUnscramble.controller.js',
                'app/content/contentManagement/innovate/projectUnscramble/projectUnscrambleModal.controller.js'
            ],
            'unscramble':[
                'app/content/contentManagement/innovate/unscramble/unscramble.controller.js',
                'app/content/contentManagement/innovate/unscramble/unscrambleModal.controller.js',
                'app/content/contentManagement/innovate/unscramble/topModal.controller.js'
            ],
            'technologyFinancing':[
                'app/content/contentManagement/innovate/technologyFinancing/technologyFinancing.controller.js',
                'app/content/contentManagement/innovate/technologyFinancing/technologyFinancingModal.controller.js',
                'app/content/contentManagement/innovate/technologyFinancing/technologyFinancingShModal.controller.js',
                'app/content/contentManagement/innovate/technologyFinancing/technologyFinancingTopModal.controller.js'
            ],
            // 助力创业内容管理
            'adRelease':[
                'app/content/contentManagement/business/adRelease/adRelease.controller.js',
                'app/content/contentManagement/business/adRelease/adReleaseModal.controller.js'
            ],
            'settledEnterprise':[
                'app/content/contentManagement/business/settledEnterprise/settledEnterprise.controller.js',
                'app/content/contentManagement/business/settledEnterprise/settledEnterpriseModal.controller.js',
                'app/content/contentManagement/business/settledEnterprise/settledEnterpriseTopModal.controller.js'
            ],
            'site':[
                'app/content/contentManagement/business/site/site.controller.js',
                'app/content/contentManagement/business/site/siteModal.controller.js',
                'app/content/contentManagement/business/site/siteSave.controller.js',
                'app/content/contentManagement/business/site/topModal.controller.js',
            ],
            'stairRecommend':[
                'app/content/contentManagement/business/stairRecommend/stairRecommend.controller.js',
                'app/content/contentManagement/business/stairRecommend/stairRecommendModal.controller.js'
            ],
            'trainingInstitution':[
                'app/content/contentManagement/business/trainingInstitution/trainingInstitution.controller.js',
                'app/content/contentManagement/business/trainingInstitution/trainingInstitutionModal.controller.js',
                'app/content/contentManagement/business/trainingInstitution/trainingTopModal.controller.js'
            ],
            'url':[
                'app/content/contentManagement/business/url/url.controller.js',
                'app/content/contentManagement/business/url/urlModal.controller.js'
            ],
            'roadshow':[
                'app/content/contentManagement/business/roadshow/roadshow.controller.js',
                'app/content/contentManagement/business/roadshow/roadshowModal.controller.js',
                'app/content/contentManagement/business/roadshow/roadShowSave.controller.js',
                'app/content/contentManagement/business/roadshow/topModal.controller.js'
            ],

            // 注册用户咨询管理
            'knowledgeAssess':[
                'app/content/contentManagement/consultation/knowledgeAssess/knowledgeAssess.controller.js',
                'app/content/contentManagement/consultation/knowledgeAssess/knowledgeAssessModal.controller.js'
            ],
            'contactExperts':[
                'app/content/contentManagement/consultation/contactExperts/contactExperts.controller.js',
                'app/content/contentManagement/consultation/contactExperts/contactExpertsModal.controller.js'
            ],
            'applyEquipment':[
                'app/content/contentManagement/consultation/applyEquipment/applyEquipment.controller.js',
                'app/content/contentManagement/consultation/applyEquipment/applyEquipmentModal.controller.js',
            ],
            'applyBase':[
                'app/content/contentManagement/consultation/applyBase/applyBase.controller.js',
                'app/content/contentManagement/consultation/applyBase/applyBaseModal.controller.js'
            ],
            'applyTutors':[
                'app/content/contentManagement/consultation/applyTutors/applyTutors.controller.js',
                'app/content/contentManagement/consultation/applyTutors/applyTutorsModal.controller.js'
            ],
            'projectDeclare':[
                'app/content/contentManagement/consultation/projectDeclare/projectDeclare.controller.js',
                'app/content/contentManagement/consultation/projectDeclare/projectDeclareModal.controller.js'
            ],

            // 后台管理
            'backManagement':[
                'app/content/backManagement/portalManagement/portalManageService.service.js',
                'app/content/backManagement/portalManagement/userInfoManage/userInfo.controller.js',
                'app/content/backManagement/portalManagement/userInfoManage/roleDistributionModal.controller.js'
            ],
            'userInformation':[
                'app/content/backManagement/portalManagement/portalManageService.service.js',
                'app/content/backManagement/portalManagement/userInfoManage/userInfo.controller.js',
                'app/content/backManagement/portalManagement/userInfoManage/roleDistributionModal.controller.js'
            ],
            'userAuthority':[
                'app/content/backManagement/portalManagement/portalManageService.service.js',
                'app/content/backManagement/portalManagement/permissionsManage/userAuthority.controller.js',
                'app/content/backManagement/portalManagement/permissionsManage/userAuthorityModal.controller.js'
            ],
            'userInfoUpdate':[
                'app/content/backManagement/portalManagement/portalManageService.service.js',
                'app/content/backManagement/portalManagement/userInfoManage/userInfoUpdate.controller.js'
            ],
            'authenticationInfo':[
                'app/content/backManagement/portalManagement/portalManageService.service.js',
                'app/content/backManagement/portalManagement/userInfoManage/userAuthentication.controller.js',
                'app/content/backManagement/portalManagement/userInfoManage/userAuthenticationImageModal.controller.js',
                'app/content/backManagement/portalManagement/userInfoManage/userAuthenticationModal.controller.js',
                'app/content/backManagement/portalManagement/userInfoManage/userDataModal.controller.js'
            ]
        }
    }
})();
