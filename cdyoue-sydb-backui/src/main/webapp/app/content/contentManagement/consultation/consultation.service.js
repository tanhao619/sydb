(function() {

    'use strict';

    angular
        .module('oddApp')
        .factory('ConsultationService', ConsultationService);
    ConsultationService.$inject = ['$resource','SYDB'];

    function ConsultationService($resource,SYDB) {
        var resourceUrl = "/sy/api";
        return $resource(resourceUrl, {}, {
            // 'getAlgorithms': {url:"/oddjobs/api/algorithms",method: 'GET'}, // 获取分页算法信息
            // 'getAlgorithmsDetails': {url:"/oddjobs/api/algorithms/:id",method: 'GET'}, // 获取算法详情
            // 'addAlgorithms': {url:"/oddjobs/api/algorithms",method: 'POST'}, // 添加算法
            // 'deleteAlgorithms': {url:"/oddjobs/api/algorithms/:id",method: 'DELETE'}, // 删除算法
            // 'updateAlgorithms': {url:"/oddjobs/api/algorithms/:id",method: 'PUT',params:{id:'@id'}} // 修改算法

            //申请设备
            'getEquipmentApplyById': {url:SYDB+"/equipment/apply/:id",params:{id:'@id'},method: 'GET'}, // 获取申请详情
            'getEquipmentApplyPageList': {url:SYDB+"/equipment/apply",method: 'GET'}, // 获取申请分页列表
            'deleteEquipmentApplyById': {url:SYDB+"/equipment/apply/:id",params:{id:'@id'},method: 'DELETE'},//删除申请

            //知产评估
            'getAssess': {url:SYDB+"/intellectuals/assess",method: 'GET'}, // 获取知产评估信息
            'getAssessDetail': {url:SYDB+"/intellectuals/assessDetail/:id",method: 'GET'}, // 获取知产评估详情
            'deleteAssess': {url:SYDB+"/intellectuals/delAssess/:id",method: 'DELETE'}, // 删除知产评估
            'deleteAllAssess': {url:SYDB+"/intellectuals/delAllAssess",method: 'DELETE'},//批量删除知产评估

            //申报项目
            'getProject': {url:SYDB+"/projects/projects",method: 'GET'}, // 获取申报项目信息
            'getProjectDetail': {url:SYDB+"/projects/seeks/:id",method: 'GET'}, // 获取申报项目详情
            'deleteProject': {url:SYDB+"/projects/delProject/:id",method: 'DELETE'}, // 删除申报项目
            'deleteAllProjects': {url:SYDB+"/projects/delAllProject",method: 'DELETE'},//批量删除申报项目

            // 联系专家
            'getExpertContacts': {url:SYDB+"/expert/contacts",method: 'GET'},//获取联系专家列表
            'delExpertContact': {url:SYDB+"/expert/contact",method: 'DELETE'},//删除专家

            // 申请入驻基地
            'getApplyBases': {url:SYDB+"/enterprise/checkIns",method: 'GET'},//获取申请入驻基地信息列表
            'getApplyBase': {url:SYDB+"/enterprise/checkIn/:id",method: 'GET'},//获取申请入驻基地信息详情
            'delApplyBase': {url:SYDB+"/enterprise/checkIn",method: 'DELETE'},//删除申请入驻基地信息

            // 申请创业导师
            'getApplyTutors': {url:SYDB+"/enterprise/directions",method: 'GET'},//获取申请创业导师信息列表
            'getApplyTutor': {url:SYDB+"/enterprise/direction/:id",method: 'GET'},//获取申请创业导师信息详情
            'delApplyTutor': {url:SYDB+"/enterprise/direction",method: 'DELETE'},//删除申请创业导师

        });
    }
})();
