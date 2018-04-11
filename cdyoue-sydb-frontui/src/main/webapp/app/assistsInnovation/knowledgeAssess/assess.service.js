(function() {
    'use strict';

    angular
        .module('sydbApp')
        .factory('AssessService',AssessService);

    AssessService.$inject = ['$resource','SYDB'];

    function AssessService($resource,SYDB) {
        var resourceUrl = "/sy/api";
        return $resource(resourceUrl, {}, {
            'publishAssess':{url:SYDB+"/intellectuals/Assess",method: 'POST'}, //发布知产评估
            'getPartners':{url:SYDB+"/intellectuals/getPartner",method: 'GET'}, //获取合作伙伴
        });
    }
})();
