/**
 * Created by sky on 2017/9/26.
 */
(function() {

    'use strict';

    angular
        .module('sydbApp')
        .factory('projectGuidanceService', projectGuidanceService);
    projectGuidanceService.$inject = ['$resource', 'SYDB'];

    function projectGuidanceService($resource, SYDB) {
        var resourceUrl = "/sy/api";
        return $resource(resourceUrl, {}, {
            'saveEnterpriseDirection':{url:SYDB + "/enterprise/direction",method:'POST'},//保存申请创业指导企业信息
        });
    }
})();
