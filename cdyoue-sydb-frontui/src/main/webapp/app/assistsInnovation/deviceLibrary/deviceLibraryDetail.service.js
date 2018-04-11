(function() {

    'use strict';

    angular
        .module('sydbApp')
        .factory('deviceLibraryDetailService', deviceLibraryDetailService);
    deviceLibraryDetailService.$inject = ['$resource', 'SYDB'];

    function deviceLibraryDetailService($resource, SYDB) {
        var resourceUrl = "/sy/api";
        return $resource(resourceUrl, {}, {
            'getEquipmentById': {url:SYDB + "/equipment/:id",method: 'GET',params:{id:"@id"}}, //获取设备详情
            'applyEquipment': {url:SYDB + "/equipment/apply",method: 'POST'}, //申请设备
        });
    }
})();
