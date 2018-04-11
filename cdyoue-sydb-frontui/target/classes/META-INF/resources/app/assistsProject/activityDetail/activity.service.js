(function() {
    'use strict';

    angular
        .module('sydbApp')
        .factory('ActivityService', ActivityService);

    ActivityService.$inject = ['$resource','SYDB'];


    function ActivityService($resource,SYDB) {
        var resourceUrl = "/sy/api";
        return $resource(resourceUrl, {}, {
            'getActivities':{url:SYDB+"/activities",method: 'GET',isArray:false}, //获取活动列表
            'getActivityDetail':{url:SYDB+"/activityDetail/:id",method: 'GET',isArray:false}, //获取活动详情
        });
    }
})();
