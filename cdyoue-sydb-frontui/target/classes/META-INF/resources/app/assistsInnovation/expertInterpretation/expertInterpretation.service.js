(function() {

    'use strict';

    angular
        .module('sydbApp')
        .factory('expertInterpretationService', expertInterpretationService);
    expertInterpretationService.$inject = ['$resource', 'SYDB'];

    function expertInterpretationService($resource, SYDB) {
        var resourceUrl = "/sy/api";
        return $resource(resourceUrl, {}, {
            'getIndustryType': {url:SYDB + "/industrys",method: 'GET'}, //获取专家行业分类
            'getExpertInterpretations': {url:SYDB + "/experts",method: 'GET'}, //获取专家列表
            'getExpertInterpretationDetail': {url:SYDB + "/expert/:id",method: 'GET'}, //获取专家详情
            'getExpertCareer': {url:SYDB + "/expert/:expertId/career",method: 'GET'}, //获取专家的职业经历
            'getExpertAchievement': {url:SYDB + "/expert/:expertId/expertAchievement",method: 'GET'}, //获取专家的专家成果
            'getProfessionalInterpretation': {url:SYDB + "/expert/:expertId/professionalInterpretation",method: 'GET'}, //获取专家的专业解读
            'contactExpert': {url:SYDB + "/expert/:id/contact",method: 'POST'}, //联系专家
        });
    }
})();
