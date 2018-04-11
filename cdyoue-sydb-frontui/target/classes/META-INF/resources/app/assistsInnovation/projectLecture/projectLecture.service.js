/**
 * Created by sky on 2017/9/20.
 */
(function() {

    'use strict';

    angular
        .module('sydbApp')
        .factory('projectLectureService', projectLectureService);
    projectLectureService.$inject = ['$resource', 'SYDB'];

    function projectLectureService($resource, SYDB) {
        var resourceUrl = "/sy/api";
        return $resource(resourceUrl, {}, {
            'getProjectLecture':{url:SYDB + "/projectLecture",method:'GET'},//获取项目解读列表
            'getProjectLectureDetail':{url:SYDB + "/projectLecture/:id",method:'GET'},//获取项目解读详情
        });
    }
})();