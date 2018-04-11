(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('UserAuthenticationController', UserAuthenticationController);

    UserAuthenticationController.$inject = ['$uibModal','$timeout', '$stateParams','PortalManageService'];

    function UserAuthenticationController($uibModal,$timeout, $stateParams,PortalManageService) {
        var vm = this;
        vm.id = $stateParams.id;

        vm.imageModal = imageModal; // 查看大图弹窗
        vm.userDataModal = userDataModal; // 个人详细资料弹窗
        vm.authenticationModal = authenticationModal; // 认证弹窗

        init();

        function init() {
            PortalManageService.getIdentification({id:vm.id},function (obj) {
                if(obj.status != "SUCCESS"){
                    vm.dataShow = false;
                    if(obj.code == 40){
                        vm.msg = "此用户没有认证信息！";
                    }else {
                        vm.msg = obj.message +"("+ obj.description+")！";
                    }
                    return;
                }
                vm.dataShow = true;
                vm.pager = obj.response;
            },function error (obj) {
                vm.dataShow = false;
                if(obj.message){
                    vm.msg = obj.message +"("+ obj.description+")！";
                }
            });
        }

        function imageModal (url,msg) {

            var out = $uibModal.open({
                animation : true,
                templateUrl : "app/content/backManagement/portalManagement/userInfoManage/userAuthenticationImageModal.html",
                controller : "UserAuthenticationImageModalController",
                controllerAs:"vm",
                size : "lg",
                resolve : {
                    items : {url:url,msg:msg}
                }
            });
            out.result.then(function (result) {

            }, function () {

            });
        }
        function userDataModal () {
            var r = $stateParams.role;
            if($stateParams.role==2){
                r=0;
            }
            var out = $uibModal.open({
                animation : true,
                templateUrl : "app/content/backManagement/portalManagement/userInfoManage/userDataModal.html",
                controller : "UserDataModalController",
                controllerAs:"vm",
                size : "lg",
                resolve : {
                    items : {id: $stateParams.id,role:r}
                }
            });
            out.result.then(function (result) {

            }, function () {

            });
        }
        function authenticationModal (type,id,reviewStatus) {
            if(reviewStatus!=0){
                return;
            }
            var out = $uibModal.open({
                animation : true,
                templateUrl : "app/content/backManagement/portalManagement/userInfoManage/userAuthenticationModal.html",
                controller : "UserAuthenticationModalController",
                controllerAs:"vm",
                size : "lg",
                resolve : {
                    items :{type:type,id:id}
                }
            });
            out.result.then(function (result) {
            }, function () {
                init();
            });
        }
    }
})();