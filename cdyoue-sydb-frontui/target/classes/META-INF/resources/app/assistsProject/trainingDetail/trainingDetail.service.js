/**
 * Created by sky on 2017/9/20.
 */
(function() {

    'use strict';

    angular
        .module('sydbApp')
        .factory('trainingDetailService', trainingDetailService);
    trainingDetailService.$inject = ['$resource', 'SYDB'];

    function trainingDetailService($resource, SYDB) {
        var resourceUrl = "/sy/api";
        return $resource(resourceUrl, {}, {
            'getTraining':{url:SYDB + "/training",method:'GET'},//获取培新机构列表
            'getTrainingDetails':{url:SYDB + "/training/:id",method:'GET'},//获取培训机构详情
        });
    }
})();