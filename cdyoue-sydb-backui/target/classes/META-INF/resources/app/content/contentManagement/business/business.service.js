(function() {

    'use strict';

    angular
        .module('oddApp')
        .factory('BusinessService', BusinessService);
    BusinessService.$inject = ['$resource','SYDB'];

    function BusinessService($resource,SYDB) {
        var resourceUrl = "/sy/api";
        return $resource(resourceUrl, {}, {
            
            // 助力创业广告发布管理
            'getAds': {url:SYDB+"/advertisement/:view",method: 'GET'},//获取助力创业广告列表
            'getAd': {url:SYDB+"/advertisement/:view/:id",method: 'GET'},//获取助力创业广告详情
            'updAd': {url:SYDB+"/advertisement/:view/:id",method: 'PUT'},//编辑助力创业广告详情

            //场地
            'getSpaces': {url:SYDB+"/spaces",method: 'GET'},//获取场地列表
            'deleteSpace': {url:SYDB+"/spaces/:id",method: 'DELETE'},//删除场地
            'deleteAllSpaces': {url:SYDB+"/spaces/delAll",method: 'DELETE'},//批量删除场地
            'getSpaceDetail': {url:SYDB+"/spaceDetail/:id",method: 'GET'}, // 获取场地详情
            'saveSpace': {url:SYDB+"/insertSpace",method: 'POST'}, // 发布场地
            'updateSpace': {url:SYDB+"/spaces/:id",method: 'PUT'}, // 编辑场地
            'siteTop':{url:SYDB+"/spaces/siteTop",method: 'POST'}, //场地置顶
            'removeSiteTop':{url:SYDB+"/spaces/removeSiteTop/:id",params: {id:'@id'},method: 'PUT'}, //取消场地置顶

            //企业库
            'getEnterprise':{url:SYDB+"/enterprise",method: 'GET'},//获取企业列表
            'getEnterpriseDetail':{url:SYDB+"/enterprise/:id",method:"GET"},//获取企业详情
            'createEnterprise':{url:SYDB+"/create/enterprise",method:"POST"},//新增企业
            'updateEnterprise':{url:SYDB+"/enterprise/:id",method:"PUT"},//编辑企业
            'deleteEnterprise':{url:SYDB+"/enterprise",method:"DELETE"},//删除企业
            'topEnterprise':{url:SYDB+"/enterprise/top/:id",params: {id:'@id',topImage:'@topImage'},method:"PUT"},//是否置顶企业
            //培训机构库
            'getTraining':{url:SYDB+"/training",method: 'GET'},//获取培训机构列表
            'getTrainingDetail':{url:SYDB+"/training/:id",method:"GET"},//获取培训机构详情
            'createTraining':{url:SYDB+"/create/training",method:"POST"},//新增培训机构
            'updateTraining':{url:SYDB+"/training/:id",method:"PUT"},//编辑培训机构
            'deleteTraining':{url:SYDB+"/training",method:"DELETE"},//删除培训机构
            'topTraining':{url:SYDB+"/training/top/:id",params: {id:'@id',topImage:'@topImage'},method:"PUT"},//是否置顶培训机构
            //活动
            'getActivities': {url:SYDB+"/activities",method: 'GET'},//获取活动列表
            'deleteActivity': {url:SYDB+"/activities/:id",method: 'DELETE'},//删除活动
            'deleteAllActivities': {url:SYDB+"/activities/delAll",method: 'DELETE'},//批量删除活动
            'getActivityDetail': {url:SYDB+"/activityDetail/:id",method: 'GET'}, // 获取活动详情
            'saveActivity': {url:SYDB+"/activities",method: 'POST'}, // 发布活动
            'updateActivity': {url:SYDB+"/activities/:id",method: 'PUT'}, // 编辑活动
            'activityTop':{url:SYDB+"/activities/activityTop",method: 'POST'}, //活动置顶
            'removeActivityTop':{url:SYDB+"/activities/removeActivityTop/:id",params: {id:'@id'},method: 'PUT'}, //取消活动置顶

            // 网址站
            'getUrls': {url:SYDB+"/websites",method: 'GET'},//获取网址站列表
            'saveUrl': {url:SYDB+"/website",method: 'POST'},//新增网址站
            'updUrl': {url:SYDB+"/website/:id",method: 'PUT'},//编辑网址站
            'delUrl': {url:SYDB+"/website",method: 'DELETE'},//删除网址站

        });
    }
})();
