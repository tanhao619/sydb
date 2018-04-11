/**
 * Created by sky on 2017/9/20.
 */
(function() {

    'use strict';

    angular
        .module('sydbApp')
        .factory('projectDeclarationService', projectDeclarationService);
    projectDeclarationService.$inject = ['$resource', 'SYDB'];

    function projectDeclarationService($resource, SYDB) {
        var resourceUrl = "/sy/api";
        return $resource(resourceUrl, {}, {
            'getProjectDeclaration':{url:SYDB + "/projects",method:'GET'},//获取项目申报列表
            'getProjectDeclarationDetail':{url:SYDB + "/projects/:id",method:'GET'},//获取项目申报详情
            'saveProjectDeclarationPeople':{url:SYDB +"/projects",method:'POST'},//保存申报人信息
        });
    }
})();
