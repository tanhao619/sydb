(function() {

    'use strict';

    angular
        .module('sydbApp')
        .factory('publishNeedService', publishNeedService);
    publishNeedService.$inject = ['$resource', 'SYDB'];

    function publishNeedService($resource, SYDB) {
        var resourceUrl = "/sy/api";
        return $resource(resourceUrl, {}, {
            'createFinancing': {url:SYDB + "/financing",method: 'POST'}, //创建新的融资需求
            'upload': {url:SYDB + "/common/upload",method: 'POST'}, //创建新的融资需求
        });
    }
})();