/**
 * Created by Administrator on 2017/9/25 0025.
 */
(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('modifyPasswordController', modifyPasswordController);

    modifyPasswordController.$inject = ['$rootScope', '$state', 'modifyPasswordService', 'loginService', 'toaster'];

    function modifyPasswordController($rootScope, $state, modifyPasswordService, loginService, toaster) {
        var vm = this;

        vm.pwdModel={};

        vm.save = save;
        vm.cancel = cancel;
        vm.checkOld = checkOld; // 检测原密码
        vm.checkReNew = checkReNew; // 检测新密码

        init();
        function init() {
            var token = $.cookie('syFrontUserTokenKey');
            loginService.checkLogin({token:token}, function (rel) {
                if (rel.code === 20) {
                    vm.pwdModel.checkOld = false;
                    vm.pwdModel.checkReNew = false;
                } else {
                    $state.go("login");
                }
            },function (err) {
                $state.go("login");
            })
        }

        function save() {
            modifyPasswordService.updatepassword({newPwd:vm.pwdModel.newPwd}, function (data) {
                switch (data.code){
                    case 20:
                        loginService.logout(function (rel) {
                            toaster.clear();
                            toaster.pop('info', "修改成功，重新登录");
                            //alert("修改成功，重新登录");
                            sessionStorage.clear();
                            $rootScope.SY_IsLogin = false;
                            $state.go("login");
                        }, function (err) {

                        });
                        break;
                    default:
                        toaster.clear();
                        toaster.pop('info', data.message + "：" + data.description);
                        //alert(data.message+":"+data.description);
                        break;
                }
            });
        }
        
        function cancel() {
            $state.go("personalCenter");
        }

        function checkOld() {
            if (vm.pwdModel.pwd === undefined) {
                vm.pwdModel.checkOld = false;
            }
            else {
                modifyPasswordService.checkpassword({password:vm.pwdModel.pwd}, function (data) {
                    switch (data.response){
                        case true:
                            vm.pwdModel.checkOld = false;
                            break;
                        default:
                            vm.pwdModel.checkOld = true;
                            break;
                    }
                });
            }
        }
        
        function checkReNew() {
            if (vm.pwdModel.reNewPwd === undefined) {
                vm.pwdModel.checkReNew = false;
            }
            else if (vm.pwdModel.newPwd !== vm.pwdModel.reNewPwd) {
                vm.pwdModel.checkReNew = true;
            } else {
                vm.pwdModel.checkReNew = false;
            }
        }
        
    }
})();
