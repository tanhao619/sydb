/**
 * Created by PC-45 on 2017/4/20.
 */
(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('expertAchievementDetailController', expertAchievementDetailController);

    expertAchievementDetailController.$inject = ['loginService', 'toaster', '$uibModal', '$stateParams', 'expertAchievementService'];

    function expertAchievementDetailController(loginService, toaster, $uibModal, $stateParams, expertAchievementService) {
        var vm = this;
        vm.id = $stateParams.id;

        vm.collect=collect;//收藏

        init(); //初始化
        function init(){
            expertAchievementService.getExpertAchievement({"id": vm.id}, function(data){
                vm.detail = data.response;
            });
        }
        
        function collect() {
            var token = $.cookie('syFrontUserTokenKey');
            loginService.checkLogin({token:token}, function (rel) {
                if (rel.code === 20) {
                    expertAchievementService.colExpertAchievement({"id": vm.id}, function(data){
                        if (data.code == 20) {
                            toaster.clear();
                            toaster.pop('info', data.message);
                            //alert(data.message);
                            init();
                        } else {
                            toaster.clear();
                            toaster.pop('info', data.message + "：" + data.description);
                            //alert(data.message + "：" + data.description);
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
    }
})();
