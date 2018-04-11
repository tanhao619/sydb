(function() {

    'use strict';

    angular
        .module('oddApp')
        .factory('ModifyPersonalInfoService', ModifyPersonalInfoService);
    ModifyPersonalInfoService.$inject = ['$resource','SYDB'];

    function ModifyPersonalInfoService($resource,SYDB) {
        var resourceUrl = "";
        return $resource(resourceUrl, {}, {
            'getCategories': {url:SYDB+"/categories",method: 'GET'}, // 获取类型列表

            'modifyPassword': {url:SYDB+"/accounts/password",method: 'PUT',params:{newPwd:'@newPwd'}}, // 修改账号密码
            'checkpassword': {url:SYDB+"/accounts/checkpassword",method: 'POST',params:{password:'@password'}}, // 检查密码是否和原密码相同
            'modifyPersonalInfo': {url:SYDB+"/accounts/person/my",method: 'PUT'}, // 编辑个人账号信息
            'getMyPerson': {url:SYDB+"/accounts/person/my",method: 'GET'} // 获取个人账号信息
        });
    }
})();
