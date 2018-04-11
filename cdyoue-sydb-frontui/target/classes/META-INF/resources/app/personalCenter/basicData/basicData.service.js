(function() {

    'use strict';

    angular
        .module('sydbApp')
        .factory('basicDataService', basicDataService);
    basicDataService.$inject = ['$resource', 'SYDB'];

    function basicDataService($resource, SYDB) {
        var resourceUrl = "/sy/api";
        return $resource(resourceUrl, {}, {
            'getBase': {url:SYDB + "/accounts/enterprise/my",method: 'GET'}, //获取当前企业的基本资料
            'editBase': {url:SYDB + "/accounts/enterprise/my",method: 'PUT'}, //编辑当前企业的基本资料
            'getCertification': {url:SYDB + "/auth/applyInfo/:type",method: 'GET'}, //獲取當前企業的認證信息
            'lookCertification': {url:SYDB + "/auth/:id/look",params: {id:'@id'},method: 'POST'}, //更新當前企業的認證信息为已读
            'saveCertification': {url:SYDB + "/auth",method: 'POST'}, //提交認證信息
            'editCertification': {url:SYDB + "/auth/:id",method: 'PUT'}, //編輯認證信息
            'checkBase': {url:SYDB + "/checkAccount",method: 'GET'}, //編輯时检查邮箱或号码是否被占用
        });
    }
})();
