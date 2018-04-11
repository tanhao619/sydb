/**
 * Created by sky on 2017/9/20.
 */
(function() {

    'use strict';

    angular
        .module('sydbApp')
        .factory('settledEnterpriseService', settledEnterpriseService);
    settledEnterpriseService.$inject = ['$resource', 'SYDB'];

    function settledEnterpriseService($resource, SYDB) {
        var resourceUrl = "/sy/api";
        return $resource(resourceUrl, {}, {
            'getEnterprise':{url:SYDB + "/enterprise",method:'GET'},//获取企业列表
            'getEnterpriseDetails':{url:SYDB + "/enterprise/:id",method:'GET'},//获取企业详情
            'saveEnterpriseCheckIn':{url:SYDB + "/enterprise/checkIn",method:'POST'},//保存申请入驻企业信息
            'saveEnterpriseDirection':{url:SYDB + "/enterprise/direction",method:'POST'},//保存申请创业指导企业信息
        });
    }
})();