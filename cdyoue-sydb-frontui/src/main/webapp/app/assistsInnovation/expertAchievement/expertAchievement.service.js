(function() {

    'use strict';

    angular
        .module('sydbApp')
        .factory('expertAchievementService', expertAchievementService);
    expertAchievementService.$inject = ['$resource', 'SYDB'];

    function expertAchievementService($resource, SYDB) {
        var resourceUrl = "/sy/api";
        return $resource(resourceUrl, {}, {
            'getExpertAchievements': {url:SYDB + "/expertAchievements",method: 'GET'}, //获取专家成果列表
            'getExpertAchievement': {url:SYDB + "/expertAchievement/:id",method: 'GET'}, //获取专家成果详情
            'colExpertAchievement': {url:SYDB + "/expertAchievement/:id/collect",params: {id:'@id'},method: 'POST'}, //收藏专家成果详情
        });
    }
})();
