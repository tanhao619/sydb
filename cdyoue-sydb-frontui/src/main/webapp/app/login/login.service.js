(function() {

    'use strict';

    angular
        .module('sydbApp')
        .factory('loginService',loginService);
    loginService.$inject = ['$resource', 'SYDB'];

    function loginService($resource, SYDB) {
        var resourceUrl = "/sy/api";
        return $resource(resourceUrl, {}, {
            'logIn': {url: SYDB + "/login", method: 'POST'},//登录,  成功后返回用户token
            'logout': {url: SYDB + "/logout", method: 'GET'},//登出
            'getMyEnterpriseInfo': {url: SYDB + "/accounts/enterprise/my", method: 'GET'},//获取当前用户登录信息，
            'checkLogin': {url: SYDB + "/checkLogin",params:{token:"@token"}, method: 'GET'},//验证登录
            'getUidByTokenString': {url: SYDB + "/checkLogin/getUid",params:{token:"@token"}, method: 'GET'},//获取当前用户ID
            'getUserMessageByToken': {url: SYDB + "/checkLogin/getUserMessage",params:{token:"@token"}, method: 'GET'},//获取当前用户信息
        });
    }
})();
