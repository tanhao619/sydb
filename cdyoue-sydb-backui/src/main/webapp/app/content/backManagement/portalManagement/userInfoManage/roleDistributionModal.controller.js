(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('RoleDistributionModalController', RoleDistributionModalController);
    RoleDistributionModalController.$inject = ['$uibModalInstance','items','PortalManageService','toaster','$uibModal'];
    function RoleDistributionModalController($uibModalInstance, items,PortalManageService,toaster,$uibModal) {
        var vm = this;
        vm.items = items;
        vm.roles ={};

        getRoleList();
        init();

        vm.save = save; // 保存
        vm.cancel = cancel; // 关闭
        vm.viewRole = viewRole; // 查看角色资源
        vm.addRole = addRole; // 添加角色
        vm.removeRole = removeRole; // 删除角色

        function init() {
            PortalManageService.getAccountRoles({id:vm.items.id},function (obj) {
                console.log(obj);
                if(obj.status != "SUCCESS"){
                    if(obj.code==40){
                        console.log(obj.message +"("+ obj.description+")！");
                        vm.selectRole = [];
                    }
                    return;
                }
                vm.selectRole = obj.response;
            },function error (obj) {
                if(obj.message){
                    console.log(obj.message +"("+ obj.description+")！");
                }
            });
        }
        // 获取角色列表
        function getRoleList() {
            PortalManageService.getRoles({pageSize: 100,pageNumber: 0}, function success(obj) {
                console.log(obj);
                if(obj.status != "SUCCESS"){
                    if(obj.message){
                        console.log(obj.message +"("+ obj.description+")！");
                    }
                    return;
                }
                vm.roles = obj.response.list;
                setSelectRole();
            },function error (obj) {
                if(obj.message){
                    console.log(obj.message +"("+ obj.description+")！");
                }
            });
        }
        function viewRole(index,isSelect) {
            if(isSelect){
                vm.userSelectRole = vm.selectRole[index];
                for(var i = 0;i<vm.roles.length;i++){
                    if(vm.roles[i].id == vm.userSelectRole.id){
                        vm.userSelectRole = vm.roles[i];
                        return;
                    }
                }
            }else{
                vm.userRole = vm.roles[index];
            }
        }
        function addRole(index) {
            vm.selectRole.push(vm.roles[index]);
            vm.roles[index].isSelect = true;
        }
        function removeRole(index) {
            for(var i = 0;i<vm.roles.length;i++){
                if(vm.roles[i].id == vm.selectRole[index].id){
                    vm.roles[i].isSelect = false;
                }
            }
            vm.selectRole.splice(index,1);
        }
        // 设置选中的角色
        function setSelectRole() {
            var selectRoleIds = [];
            for(var i=0;i<vm.selectRole.length;i++){
                selectRoleIds.push(vm.selectRole[i].id);
            }
            for(var y=0;y<vm.roles.length;y++){
                if(selectRoleIds.indexOf(vm.roles[y].id)==-1){
                    vm.roles[y].isSelect = false;
                }else {
                    vm.roles[y].isSelect = true;
                }
            }
        }
        function save() {
            var selectRoleIds = [];
            for(var i=0;i<vm.selectRole.length;i++){
                selectRoleIds.push(vm.selectRole[i].id);
            }
            if(!selectRoleIds.length){
               // alert("角色不能为空！");
                toaster.info("角色不能为空！");
                return;
            }
            console.log(vm.items.id);
            PortalManageService.setAccountRoles({ids: selectRoleIds,id:vm.items.id} ,function success(obj) {
                //console.log(obj);
                if(obj.status != "SUCCESS"){
                    if(obj.message){
                       // console.log(obj.message +"("+ obj.description+")！");
                    }
                    return;
                }
               // alert("角色分配成功！")
                var out = $uibModal.open({
                    animation : true,
                    templateUrl : "app/layouts/messageTips/messageTips.html",
                    controller : "messageTipsController",
                    controllerAs:"vm",
                    size : "md",
                    resolve : {
                        items : function() {
                            return  "角色分配成功！";
                        }
                    }
                });
                cancel();
            },function error (obj) {
                if(obj.message){
                 //   console.log(obj.message +"("+ obj.description+")！");
                }
            });
        }
        function cancel() {
            $uibModalInstance.dismiss('cancel');
        }
    }
})();