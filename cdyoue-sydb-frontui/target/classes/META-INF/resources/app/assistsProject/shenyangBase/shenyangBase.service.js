/**
 * Created by sky on 2017/9/26.
 */
(function() {

    'use strict';

    angular
        .module('sydbApp')
        .factory('shenyangBaseService', shenyangBaseService);
    shenyangBaseService.$inject = ['$resource', 'SYDB'];

    function shenyangBaseService($resource, SYDB) {
        var resourceUrl = "/sy/api";
        return $resource(resourceUrl, {}, {
            'saveEnterpriseCheckIn':{url:SYDB + "/enterprise/checkIn",method:'POST'},//保存申请入驻企业信息
        });
    }
})();
