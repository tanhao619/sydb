(function() {

    'use strict';

    angular
        .module('sydbApp')
        .factory('forgetPasswordService', forgetPasswordService);
    forgetPasswordService.$inject = ['$resource', 'SYDB'];

    function forgetPasswordService($resource, SYDB) {
        var resourceUrl = "/sy/api";
        return $resource(resourceUrl, {}, {
            'checkAccount': {url: SYDB + "/checkAccount/checkInput", method: 'GET'},//验证账户是否存在
            'getTelCaptcha': {url: SYDB + "/telCaptcha", method: 'GET'},//获取手机验证码
            'checkTelCaptcha': {url: SYDB + "/checkTelCaptcha/:tel/:captcha",params:{tel:"@tel",captcha:"@captcha"}, method: 'GET'},//验证手机验证码
            'getMailCaptcha': {url: SYDB + "/accounts/mail/vcode/send/:address",params:{address:"@address"}, method: 'GET'},//获取邮箱验证码
            'checkMailCaptcha': {url: SYDB + "/accounts/mail/vcode/verify/:address/:yzm",params:{address:"@address",yzm:"@yzm"}, method: 'GET'},//验证邮箱验证码
            'forgetPassword': {url: SYDB + "/forgetPassword", method: 'GET'},//验证账户是否存在
        });
    }
})();
