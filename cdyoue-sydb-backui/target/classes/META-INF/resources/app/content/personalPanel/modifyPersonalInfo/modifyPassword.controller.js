(function() {
    'use strict';

    angular
        .module('oddApp')
        .controller('ModifyPassword', ModifyPassword);

    ModifyPassword.$inject = ['$state','ModifyPersonalInfoService'];

    function ModifyPassword ($state,ModifyPersonalInfoService) {
        var vm = this;

        // 错误提示消息
        vm.oldPassword = "※";
        vm.newPassword = "※";
        vm.confirmPassword = "※";
        vm.entityConfirmPassword = "";
        vm.entity = {};

        vm.myInfo = JSON.parse(sessionStorage.getItem("oddBackUserKey"));

        vm.reset = reset; // 重置表单
        vm.submit = submit; // 提交
        vm.changeEvents = changeEvents; // 改变事件

        function reset () {
            vm.entity = {};
        }
        function changeEvents() {
            vm.msg = "";
            vm.confirmPassword = "※";
        }
        // 提交数据
        function submit () {
            if(vm.entity.newPwd != vm.entity.confirmPassword){
                vm.confirmPassword = "※ 确认密码与新密码不一致！";
                return;
            }

            ModifyPersonalInfoService.checkpassword({password:vm.entity.password} ,function (obj) {// 换成验证老密码是否相同
                if(!obj.response){
                    vm.oldPassword = "※ 原密码输入不正确！";
                    return;
                }
                ModifyPersonalInfoService.modifyPassword({newPwd:vm.entity.newPwd} ,function (obj) {
                    if(obj.status != "SUCCESS"){
                        if(obj.code==40){
                            vm.msg = "没有数据！";
                        }else {
                            vm.msg = obj.message +"("+ obj.description+")！";
                        }
                        return;
                    }
                    sessionStorage.removeItem('oddBackUserTokenKey');
                    $state.go('logIn');
                });
            });
        }
    }
})();
