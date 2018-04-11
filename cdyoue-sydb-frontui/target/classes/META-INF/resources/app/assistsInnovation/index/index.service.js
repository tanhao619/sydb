(function() {

    'use strict';

    angular
        .module('sydbApp')
        .factory('assistsInnovationService', assistsInnovationService);
    assistsInnovationService.$inject = ['$resource', 'SYDB'];

    function assistsInnovationService($resource, SYDB) {
        var resourceUrl = "/sy/api";
        return $resource(resourceUrl, {}, {
            'getAds': {url:SYDB+"/advertisement/:view",method: 'GET'}, //获取助力创新广告轮播图
            'getExpertRecommendation': {url:SYDB + "/expert/top",method: 'GET'}, //获取推荐的专家列表
            'getProfessionalInterpretationRecommendation': {url:SYDB + "/professionalInterpretation/top",method: 'GET'}, //获取推荐的专业解读列表
            'getExpertAchievementRecommendation': {url:SYDB + "/expertAchievement/top",method: 'GET'}, //获取推荐的专家成果列表
            'getEquipmentCover': {url:SYDB+"/advertisement/:view/:id",method: 'GET'}, //获取设备库的封面图片
            'getEquipmentTop': {url:SYDB + "/equipment/top",method: 'GET'}, //获取推荐的设备列表
            'getProjectDeclaration':{url:SYDB + "/projects",method:'GET'},//获取项目申报列表
            'getProjectInterpretation':{url:SYDB + "/projectInterpretation",method:'GET'},//获取项目解读列表
            'getFinancingTop':{url:SYDB + "/financing/top",method:'GET'},//获取融资top
            'getTopProjectLecture':{url:SYDB + "/projectLecture/top",method:'GET'},//获取置顶项目讲座列表
            'getBuysBanner':{url:SYDB + "/intellectuals/buysBanner",method:'GET'},//获取知产求购banner图
            'getSalesBanner':{url:SYDB + "/intellectuals/salesBanner",method:'GET'},//获取知产出售banner图
        });
    }
})();
