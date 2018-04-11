(function() {
    'use strict';
    angular
        .module('oddApp')
        .factory('PortalManageService', PortalManageService);
    PortalManageService.$inject = ['$resource','SYDB'];

    function PortalManageService($resource,SYDB) {
        var resourceUrl = "/sy/api";
        return $resource(resourceUrl, {}, {
            'getCategories': {url:SYDB+"/categories",method: 'GET'}, // 获取类型列表
            'getEnterprise': {url:SYDB+"/accounts/enterprise/:id",method: 'GET'}, // 获取任意企业用户账号信息
            'getPerson': {url:SYDB+"/accounts/person/:id",method: 'GET'}, // 获取任意个人用户账号信息

            'getAccountRoles': {url:SYDB+"/accounts/:id/roles",method: 'GET'}, // 获取账户角色
            'setAccountRoles': {url:SYDB+"/role/:ids",method: 'POST',params:{ids:'@ids',id:'@id'}}, // 分配账户角色

            'getAccounts': {url:SYDB+"/accounts",method: 'GET'}, // 获取所有注册用户
            'getIdentification': {url:SYDB+"/auth/identification/:id",method: 'GET'}, // 获取用户认证信息
            'setAccounts': {url:SYDB+"/accounts/:id",method: 'POST',params:{id:'@id'}}, // 操作用户 0:禁止用户 1:解除禁止 2:重置密码"
            'updateEnterprise': {url:SYDB+"/accounts/enterprise/:id",method: 'PUT',params:{id:'@id'}}, // 编辑企业信息
            'updatePerson': {url:SYDB+"/accounts/person/:id",method: 'PUT',params:{id:'@id'}}, // 编辑个人账号信息
            'authApprove': {url:SYDB+"/auth/:id/approve",method: 'POST',params:{id:'@id'}}, // 通过认证
            'authReject': {url:SYDB+"/auth/:id/reject",method: 'POST',params:{id:'@id'}}, // 拒绝认证

            'getMenus': {url:SYDB+"/menus",method: 'GET'}, // 获取所有菜单
            'getRoles': {url:SYDB+"/roles",method: 'GET'}, // 获取所有角色
            'getRoleDetails': {url:SYDB+"/role/:id",method: 'GET'}, // 获取角色详细
            'addRole': {url:SYDB+"/role",method: 'POST'}, // 新增角色
            'updateRole': {url:SYDB+"/role/:id",method: 'PUT',params:{id:'@id'}}, // 修改角色
            'deleteRole': {url:SYDB+"/role/:id",method: 'DELETE'} // 删除角色
        });
    }
})();
