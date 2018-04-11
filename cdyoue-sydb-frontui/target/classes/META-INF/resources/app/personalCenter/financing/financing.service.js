(function() {

    'use strict';

    angular
        .module('sydbApp')
        .factory('financingService', financingService);
    financingService.$inject = ['$resource', 'SYDB'];

    function financingService($resource, SYDB) {
        var resourceUrl = "/sy/api";
        return $resource(resourceUrl, {}, {
            'getMy': {url:SYDB + "/financing/my",method: 'GET'}, //获取我的融资列表
        });
    }
})();
