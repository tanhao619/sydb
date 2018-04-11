/**
 * Created by sky on 2017/9/20.
 */
(function() {

    'use strict';

    angular
        .module('sydbApp')
        .factory('projectInterpretationService', projectInterpretationService);
    projectInterpretationService.$inject = ['$resource', 'SYDB'];

    function projectInterpretationService($resource, SYDB) {
        var resourceUrl = "/sy/api";
        return $resource(resourceUrl, {}, {
            'getProjectInterpretation':{url:SYDB + "/projectInterpretation",method:'GET'},//获取项目解读列表
            'getProjectInterpretationDetail':{url:SYDB + "/projectInterpretation/:id",method:'GET'}//获取项目解读详情
        });
    }
})();
