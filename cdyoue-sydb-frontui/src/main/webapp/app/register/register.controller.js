(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('registerController', registerController);

    registerController.$inject = ['$scope', '$state', 'registerService','$interval','toaster'];

    function registerController($scope, $state, registerService,$interval,toaster) {
        var vm = this;
        vm.telRequest = {
            tel:"",
            captcha:"",
            password:"",
            rePassword:""
        }
        vm.paracont="发送验证码";
        vm.time = 60;
        vm.b = false;
        vm.rePassword = "";
        vm.agree = "";
        vm.telCaptcha = telCaptcha;//获取验证码
        vm.save = save;
        vm.showCheckbox = false;
        vm.show = show;
        function show(){
            vm.showCheckbox =  !vm.showCheckbox;
        }
        /**
         * 获取验证码
         */
        function telCaptcha(tel) {

            if(tel == "" || tel == undefined || tel ==null){
                toaster.pop('info', "手机号为空");
            }else{
                registerService.telCaptcha({tel:tel}, function (rel) {
                   if(rel.code == 20){
                       var timeInterval = $interval(function () {
                           if(vm.time > 0){
                               vm.paracont = "("+vm.time+"s)后重新获取"
                               vm.b = true;
                               vm.time -- ;
                           }else if(vm.time == 0){
                               vm.paracont = "发送验证码";
                               vm.b = false;
                               vm.time = 60;
                               $interval.cancel(timeInterval)
                           }
                       },1000)
                   }else{
                       toaster.pop('info', rel.description);
                   }
                }, function (err) {
                    toaster.pop('info', "获取失败");
                })
            }
        }


        function save() {
            console.log(vm.telRequest.password);
            console.log(vm.telRequest.rePassword);
            if(vm.telRequest.password == vm.telRequest.rePassword){
                registerService.telRegister(vm.telRequest, function (rel) {
                    if(rel.code == 20){
                        toaster.pop('info', "提交成功！即将跳转至登录页面");
                        $state.go("login");
                    }else{
                        toaster.pop('info', rel.message);
                    }
                }, function (err) {
                    toaster.pop('info', "提交失败！");
                })
            }else{
                toaster.pop('info', "两次密码不一致！");
            }

        }

    }
})();
