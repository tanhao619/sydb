(function() {

    'use strict';

    angular
        .module('sydbApp')
        .factory('financingDetailService', financingDetailService);
    financingDetailService.$inject = ['$resource', 'SYDB'];

    function financingDetailService($resource, SYDB) {
        var resourceUrl = "/sy/api";
        return $resource(resourceUrl, {}, {
            'getFinancingById': {url:SYDB + "/financing/:id",method: 'GET',params:{id:"@id"}}, //获取融资项目详情
        });
    }
})();