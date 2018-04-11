/**
 * Created by sky on 2017/10/13.
 */
(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('shenyangBaseBouncesController', shenyangBaseBouncesController);

    shenyangBaseBouncesController.$inject = ['$scope', '$state', 'shenyangBaseService','Upload','SYDB','toaster','$uibModalInstance','loginService','$uibModal'];

    function shenyangBaseBouncesController($scope, $state, shenyangBaseService,Upload,SYDB,toaster,$uibModalInstance,loginService,$uibModal) {
        var vm = this;
        vm.userName="";
        vm.enterpriseEntity={};//入驻基地填写的信息实体
        vm.uploadFile2=uploadFile2;//上传图片
        vm.save=save;//保存申请入驻企业信息
        vm.cancel=cancel;
        vm.getUserMessage=getUserMessage;//获取用户信息
        getUserMessage();
        function getUserMessage() {
            var token = $.cookie('syFrontUserTokenKey');
            loginService.getUserMessageByToken({token:token},function (data) {
                vm.userName = data.response.userName;
            },function (err) {
                console.log(err);
            })
        }
        function uploadFile2() {
            if (!vm.myEnterprise.file){
                return;
            }
            var url = SYDB+'/common/upload';
            var data = angular.copy(vm.myEnterprise || {});
            data.file = vm.myEnterprise.file;
            //限制只能上传图片
            if((data.file.type.substring(0,data.file.type.indexOf('/'))) !== "image"){
                toaster.clear();
                toaster.pop("warning", "上传文件格式不对!");
                return;
            }
            Upload.upload({
                url: url,
                data: data
            }).success(function (data) {
                vm.enterpriseEntity.enterpriseLogo = data.response.url;
                toaster.clear();
                toaster.pop("info", "上传成功!");
            }).error(function () {
                toaster.clear();
                toaster.pop("error", "上传失败!");
            });
        }

        function save() {
            shenyangBaseService.saveEnterpriseCheckIn(vm.enterpriseEntity,function () {
                console.log("保存成功");
                cancel();
                var out = $uibModal.open({
                    animation : true,
                    templateUrl : "app/assistsProject/shenyangBase/shenyangBaseModal.html",
                    controller : "shenyangBaseModalController",
                    controllerAs:"vm",
                    size : "md",
                    resolve : {
                        items : function() {

                        }
                    }
                });
            },function (err) {
                console.log(err);
            })
        }

        function cancel() {
            $uibModalInstance.dismiss('cancel');
        }
    }
})();