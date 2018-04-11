(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('knowledgeAssessController', knowledgeAssessController);

    knowledgeAssessController.$inject = ['$state','$rootScope', '$uibModal','AssessService','loginService'];

    function knowledgeAssessController($state,$rootScope ,$uibModal,AssessService,loginService) {
        var vm = this;
        vm.changeTable = changeTable;
        vm.openApply = openApply;
        vm.checkLogin=checkLogin;//检查是否登录
        // vm.cancel = cancel; // 关闭
        vm.showTable =1;
        //锚点定位
        vm.activeClass = 1;
        vm.showActive = showActive;
        vm.assess = {
            type: 1,
        }//表单提交数据
        vm.partners = [];
        getPartnersList();


        // function cancel() {
        //      $uibModalInstance.dismiss('cancel');
        // }

        // if(JSON.parse(sessionStorage.getItem("syFrontUserTokenKey"))){
        //     $rootScope.SY_IsLogin = true;
        // }else {
        //     $rootScope.SY_IsLogin = false;
        // }

        vm.booleanLogin=false;
        var token = $.cookie('syFrontUserTokenKey');
        loginService.checkLogin({token:token},function (rel) {
            if (rel.code == 20){
                vm.booleanLogin=true;
            }
        });

        function showActive(id){
            if(id == 1){
                vm.activeClass = 1;
            }
            else if(id == 2){
                vm.activeClass = 2;
            }
            else if(id == 3){
                vm.activeClass = 3;
            }
        }
        function changeTable(id){
            vm.assess = {};
            vm.assess.type = id;
            console.log(vm.assess.type);
            if(id == 1){
                vm.showTable = 1;
            }
            if(id == 2)
            {
                vm.showTable = 2;
            }
            if(id == 3)
            {
                vm.showTable = 3;
            }
        }


        function openApply() {
            if (vm.booleanLogin){
                var out = $uibModal.open({
                    animation: true,
                    templateUrl: "app/assistsInnovation/knowledgeAssess/knowledgeAssessApply.html",
                    controller: "knowledgeAssessApplyController",
                    controllerAs: "vm",
                    size: "lg",
                });
                out.result.then(function (result) {
                });
            }else {
                var out = $uibModal.open({
                    animation : true,
                    templateUrl : "app/layouts/messageTips/messageTips.html",
                    controller : "messageTipsController",
                    controllerAs:"vm",
                    size : "md",
                    resolve : {
                        items : function() {
                            return "用户未登录，是否前往登录"
                        }
                    }
                });
                out.result.then(function (result) {//result关闭是回传的值
                    if(result == 1){
                        $state.go("login");
                    }else {
                        return;
                    }
                });
            }
        }

        function getPartnersList() {
            AssessService.getPartners(function (data) {
                vm.partners=data.response;
                // console.log(vm.partners)
            })
        }

        function checkLogin() {
            if(vm.booleanLogin){
                var out = $uibModal.open({
                    animation: true,
                    templateUrl: "app/assistsInnovation/knowledgeAssess/knowledgeAssessApply.html",
                    controller: "knowledgeAssessApplyController",
                    controllerAs: "vm",
                    size: "lg",
                });
                out.result.then(function (result) {
                });
            }else {
                $uibModal.open({
                    animation : true,
                    templateUrl : "layouts/notLoginModal/notLoginModal.html",
                    controller : "notLoginModalController",
                    controllerAs:"vm",
                    size : "sm",
                });
            }
        }
    }
})();
