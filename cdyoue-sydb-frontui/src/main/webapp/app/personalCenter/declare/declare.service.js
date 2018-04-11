(function() {

    'use strict';

    angular
        .module('sydbApp')
        .factory('declareService', declareService);
    declareService.$inject = ['$resource', 'SYDB'];

    function declareService($resource, SYDB) {
        var resourceUrl = "/sy/api";
        return $resource(resourceUrl, {}, {
            'getMy': {url:SYDB + "/my/projects",method: 'GET'}, //获取我的申报
        });
    }
})();
