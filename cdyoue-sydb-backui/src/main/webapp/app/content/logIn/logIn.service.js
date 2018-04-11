/**
 * Created by PC-45 on 2017/4/21.
 */
(function () {
    'use strict';
    angular
        .module('oddApp')
        .factory('LogInService', LogInService);

    LogInService.$inject = ['$resource', 'SYDB'];

    function LogInService($resource, SYDB) {
        var resourceUrl = "";
        return $resource(resourceUrl, {}, {
            'logIn': {url: SYDB + "/loginBack", method: 'POST'},//登录,  成功后返回用户token
            'logout': {url: SYDB + "/logout", method: 'GET'},
            'getMyInfo': {url: SYDB + "/accounts/person/my", method: 'GET'} // 获取个人账号信息
        });
    }
})();
