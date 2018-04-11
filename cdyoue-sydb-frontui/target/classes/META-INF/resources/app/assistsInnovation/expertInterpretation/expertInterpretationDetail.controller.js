(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('expertInterpretationDetailController', expertInterpretationDetailController);

    expertInterpretationDetailController.$inject = ['$uibModal', 'expertInterpretationService', '$stateParams', 'loginService'];

    function expertInterpretationDetailController($uibModal, expertInterpretationService, $stateParams, loginService) {
        var vm = this;
        var id = $stateParams.id;
        //展开全部
        vm.openAll = openAll;
        vm.getAll = false;
        //锚点定位
        vm.activeClass = 1;
        vm.contactExpertModel={};
        vm.enterName = '';
        vm.showActive = showActive;
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
            else if(id == 4){
                vm.activeClass = 4;
            }
        }
        init(); //初始化
        vm.open = open; // 联系专家的弹窗
        vm.closeApplyUse = closeApplyUse; // 取消提交联系
        vm.applyTOUse = applyTOUse; // 提交联系

        function init(){
            expertInterpretationService.getExpertInterpretationDetail({"id": id}, function(data){
                vm.baseDetail = data.response;
            });
            expertInterpretationService.getExpertCareer({"expertId": id}, function (data) {
                vm.careers = data.response;
            });
            expertInterpretationService.getExpertAchievement({"expertId": id}, function (data) {
                vm.achievements = data.response;
            });
            expertInterpretationService.getProfessionalInterpretation({"expertId": id}, function (data) {
                vm.interpretations = data.response;
            });
        };
        vm.showMore1 = false;
        vm.showMore2 = false;
        vm.showMore3 = false;
         function openAll(id){
             // console.log("展开");
             if(id == 1){
                 vm.showMore1 = !vm.showMore1;
             }
             else if(id == 2){
                 vm.showMore2 = !vm.showMore2;
             }
             else if(id == 3){
                 vm.showMore3 = !vm.showMore3;
             }
         }
        function open(id, size) {
            var token = $.cookie('syFrontUserTokenKey');
            loginService.checkLogin({token:token}, function (rel) {
                if (rel.code === 20) {
                    var model = $uibModal.open({
                        templateUrl: 'app/assistsInnovation/expertInterpretation/expertContactModal.html',
                        controller: 'expertContactModalController',
                        controllerAs: 'vm',
                        size: size,
                        resolve: {
                            entity: function () {
                                return {
                                    contactExpertId: id,
                                };
                            }
                        }
                    });
                } else {
                    var out = $uibModal.open({
                        animation : true,
                        templateUrl : "layouts/notLoginModal/notLoginModal.html",
                        controller : "notLoginModalController",
                        controllerAs:"vm",
                        size : "sm",
                    });
                }
            },function (err) {
                var out = $uibModal.open({
                    animation : true,
                    templateUrl : "layouts/notLoginModal/notLoginModal.html",
                    controller : "notLoginModalController",
                    controllerAs:"vm",
                    size : "sm",
                });
            })

        }

        function applyTOUse() {
            expertInterpretationService.contactExpert({id: vm.contactExpertId},vm.contactExpertModel,function(data){
                if (data.status == 'SUCCESS') {
                    //alert(data.message);
                    cancel();
                } else {
                    //alert(data.message+":"+data.description);
                }
            })
            vm.contactExpertModel = null;
        };

        function closeApplyUse() {
            vm.contactExpertModel = null;
        };
    }
})();
