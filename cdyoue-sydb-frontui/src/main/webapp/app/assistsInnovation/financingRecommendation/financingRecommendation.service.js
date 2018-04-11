(function() {

    'use strict';

    angular
        .module('sydbApp')
        .factory('financingRecommendationService', financingRecommendationService);
    financingRecommendationService.$inject = ['$resource', 'SYDB'];

    function financingRecommendationService($resource, SYDB) {
        var resourceUrl = "/sy/api";
        return $resource(resourceUrl, {}, {
            'getFinancingPageList': {url:SYDB + "/financing",method: 'GET'}, //获取融资分页列表
        });
    }
})();