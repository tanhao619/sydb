(function() {

    'use strict';

    angular
        .module('sydbApp')
        .factory('deviceLibraryService', deviceLibraryService);
    deviceLibraryService.$inject = ['$resource', 'SYDB'];

    function deviceLibraryService($resource, SYDB) {
        var resourceUrl = "/sy/api";
        return $resource(resourceUrl, {}, {
            'getEquipmentPageList': {url:SYDB + "/equipment",method: 'GET'}, //获取设备分页列表
            'getIndustrys': {url:SYDB + "/industrys",method: 'GET'}, //获取设备分页列表
        });
    }
})();
