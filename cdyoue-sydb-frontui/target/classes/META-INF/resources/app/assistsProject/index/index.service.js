(function() {

    'use strict';

    angular
        .module('sydbApp')
        .factory('assistsProjectService', assistsProjectService);
    assistsProjectService.$inject = ['$resource', 'SYDB'];

    function assistsProjectService($resource, SYDB) {
        var resourceUrl = "/sy/api";
        return $resource(resourceUrl, {}, {
            'getAds': {url:SYDB+"/advertisement/:view",method: 'GET'}, //获取助力创业广告轮播图
            'getEnterprises': {url:SYDB + "/enterprise/top",method: 'GET'}, //获取推荐的企业列表
            'getTrainings': {url:SYDB + "/training/top",method: 'GET'}, //获取推荐的培训机构
            'getAreaBanner': {url:SYDB + "/spaces/areaBanners",method: 'GET'}, //获取场地banner图
            'getActivityBanner': {url:SYDB + "/spaces/activityBanners",method: 'GET'}, //获取场地banner图
            'getUrls': {url:SYDB + "/website/list",method: 'GET'}, //获取网址站列表
        });
    }
})();
