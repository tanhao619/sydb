(function() {

    'use strict';

    angular
        .module('sydbApp')
        .factory('knowledgeService', knowledgeService);
    knowledgeService.$inject = ['$resource', 'SYDB'];

    function knowledgeService($resource, SYDB) {
        var resourceUrl = "/sy/api";
        return $resource(resourceUrl, {}, {
            'getMySeeks': {url:SYDB + "/intellectuals/seeks/my",method: 'GET'}, //获取我的知产求购列表
            'getMySales': {url:SYDB + "/intellectuals/sales/my",method: 'GET'}, //获取我的知产出售雷彪
        });
    }
})();
