(function() {

    'use strict';

    angular
        .module('sydbApp')
        .factory('modifyPasswordService', modifyPasswordService);
    modifyPasswordService.$inject = ['$resource', 'SYDB'];

    function modifyPasswordService($resource, SYDB) {
        var resourceUrl = "/sy/api";
        return $resource(resourceUrl, {}, {
            'checkpassword': {url:SYDB + "/accounts/checkpassword",params: {password:'@password'},method: 'POST'}, //检查密码是否和原密码相同
            'updatepassword': {url:SYDB + "/accounts/password",params: {newPwd:'@newPwd'},method: 'PUT'}, //修改账号密码
        });
    }
})();
