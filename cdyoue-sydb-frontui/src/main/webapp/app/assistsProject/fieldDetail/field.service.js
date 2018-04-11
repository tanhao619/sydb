(function() {
    'use strict';

    angular
        .module('sydbApp')
        .factory('FieldService', FieldService);

    FieldService.$inject = ['$resource','SYDB'];


    function FieldService($resource,SYDB) {
        var resourceUrl = "/sy/api";
        return $resource(resourceUrl, {}, {
            'getFields':{url:SYDB+"/spaces",method: 'GET',isArray:false}, //获取场地列表
            'getFieldDetail':{url:SYDB+"/spaceDetail/:id",method: 'GET',isArray:false}, //获取场地详情
        });
    }
})();
