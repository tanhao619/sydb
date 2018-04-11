(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('InnovateAdReleaseController', InnovateAdReleaseController);

    InnovateAdReleaseController.$inject = ['$uibModal', 'InnovateService'];

    function InnovateAdReleaseController($uibModal, InnovateService) {
        var vm = this;

        vm.dataShow = true;
        vm.msg = "抱歉！暂无数据！";

        vm.openModal = openModal; // 打开弹窗

        //初始化数据
        init();
        function init() {
            InnovateService.getAds({view:'zlcx'}, function success(obj) {
                if(obj.status !== "SUCCESS"){
                    vm.dataShow = false;
                    if(obj.code === 10){
                        vm.msg = "抱歉！暂无数据！";
                    }else {
                        vm.msg = obj.message +"("+ obj.description+")！";
                    }
                    return;
                }
                vm.dataShow = true;
                vm.syBanner = obj.response;
            },function error (obj) {
                vm.dataShow = false;
                if(obj.message){
                    vm.msg = obj.message +"("+ obj.description+")！";
                }
            });
        }

        function openModal (index,model) {
            vm.passData={};
            var out = $uibModal.open({
                animation : true,
                templateUrl : "app/content/contentManagement/innovate/innovateAdRelease/innovateAdReleaseModal.html",
                controller : "InnovateAdReleaseModalController",
                controllerAs:"vm",
                size : "lg",
                resolve : {
                    items : function() {
                        vm.passData.id=vm.syBanner[index].orderBy;
                        vm.passData.showModel = model;
                        return vm.passData;
                    }
                }
            });
            out.result.then(function (result) {
                //console.log(result); //result关闭是回传的值
            }, function (reason) {
                init();
            });
        }

    }
})();