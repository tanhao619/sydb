(function() {

    'use strict';

    angular
        .module('sydbApp')
        .factory('collectionsService', collectionsService);
    collectionsService.$inject = ['$resource', 'SYDB'];

    function collectionsService($resource, SYDB) {
        var resourceUrl = "/sy/api";
        return $resource(resourceUrl, {}, {
            'getMyEA': {url:SYDB + "/expertAchievement/my/collect",method: 'GET'}, //获取我收藏的专家成果
            'getMyPI': {url:SYDB + "/professionalInterpretation/my/collect",method: 'GET'}, //获取我收藏的专业解读
        });
    }
})();
