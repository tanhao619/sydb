/**
 * Created by sky on 2017/9/20.
 */
(function() {

    'use strict';

    angular
        .module('sydbApp')
        .factory('registerService', registerService);
    registerService.$inject = ['$resource', 'SYDB'];

    function registerService($resource, SYDB) {
        var resourceUrl = "/sy/api";
        return $resource(resourceUrl, {}, {
            'telRegister':{url:SYDB + "/telRegister",method:'POST'},//手机号注册
            'telCaptcha':{url:SYDB + "/telCaptcha/",method:'GET',params:{tel:"@tel"}},//获取验证码
        });
    }
})();