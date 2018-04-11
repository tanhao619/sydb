(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('loginController', loginController);

    loginController.$inject = ['$rootScope', '$state','loginService','UserToken','toaster'];

    function loginController($rootScope, $state,loginService,UserToken,toaster) {
        var vm = this;
        vm.loginMessage={
            userName:"",
            passWord:""
        };
        vm.userNameOrPasswordError = false;
        vm.userMessage={};

        vm.login = login;

        function login() {
            if(vm.loginMessage){
                vm.loginMessage.clientId = "098f6bcd4621d373cade4e832627b4f65";
                loginService.logIn(vm.loginMessage,function (rel) {
                    if(rel.code == 20){
                        vm.userNameOrPasswordError = false;
                        UserToken.setUserToken(rel.response);
                        var date = new Date();
                        date.setTime(date.getTime()+60*30*1000);
                        $.cookie('syFrontUserTokenKey', rel.response.access_token, { path:"/" ,domain:'.youedata.com',expires:date });
                        // 设置头部是否显示登录按钮
                        if($.cookie('syFrontUserTokenKey')){
                            $rootScope.SY_IsLogin = true;
                        }
                         $state.go("personalCenter");
                    }else{
                        vm.userNameOrPasswordError = true;
                    }
                },function (err) {

                })
            }else{
                toaster.pop('info', "用户名或密码为空");
            }
        }
    }
})();
