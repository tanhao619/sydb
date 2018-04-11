(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('LogInController', LogInController);

    LogInController.$inject = ['$state', 'LogInService', 'UserToken','$rootScope'];

    function LogInController($state, LogInService, UserToken,$rootScope) {
        var vm = this;

        // 让页面全屏显示（去掉菜单栏和顶部）
        $("#navbar").remove();
        $(".content").addClass("content-login");
        $(".content").removeClass("content");

        vm.userKey = {}; // 存入浏览器的用户信息
        vm.errorMessage = "";
        vm.user = {
            userName: "",
            password: ""
            // verification: "9527"
        };
        vm.logIn = logIn;

        function logIn() {
            if (vm.user) {
                vm.user.clientId = '098f6bcd4621d373cade4e832627b4f6';
                LogInService.logIn(vm.user, function (obj) {
                    if(obj.status != "SUCCESS"){
                        if(obj.message){
                            vm.errorMessage = "用户名或密码错误！";
                        }
                        return;
                    }
                    $rootScope.loginstatus = true;
                    UserToken.setUserToken(obj);
                    getMyInfo();
                    $state.go('personalPanel');
                }, function (obj) {
                    if(obj.status == 500){
                        vm.errorMessage = "系统500错误，请联系管理员！";
                    }else{
                        vm.errorMessage = "错误消息："+obj.data.message +"；错误码："+ obj.status+"！";
                    }
                });
            } else {
                vm.errorMessage = "请输入帐号信息！";
            }
        }
        function getMyInfo() {
            LogInService.getMyInfo(function success(obj) {
                if(obj.status != "SUCCESS"){
                    return;
                }

                vm.userKey.name = obj.response.name;
                vm.userKey.nickName = obj.response.nickName;
                vm.userKey.role = obj.response.role;
                vm.userKey.userId = obj.response.userId;
                sessionStorage.setItem("oddBackUserKey", JSON.stringify(vm.userKey));
            });
        }
    }
})();

