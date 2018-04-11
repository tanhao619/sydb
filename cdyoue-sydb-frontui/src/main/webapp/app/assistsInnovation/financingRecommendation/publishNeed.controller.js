/**
 * Created by PC-45 on 2017/4/20.
 */
(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('publishNeedController', publishNeedController);

    publishNeedController.$inject = ['$scope','loginService','$uibModal','$state', 'Upload', 'toaster', '$stateParams','publishNeedService','SYDB'];

    function publishNeedController($scope,loginService,$uibModal, $state, Upload, toaster, $stateParams,publishNeedService,SYDB) {
        var vm = this;
        vm.financing = {
            id:0,//id
            name:"",//项目名称
            type:"",//项目类型
            patentNum:"",//专利号
            fund:"",//融资需求资金
            imgUrl:"",//图片url
            summary:"",//简介
            detail:"",//详情
            file:"",//附件
            contacts:"",//联系人
            phone:"",//联系电话
            email:"",//邮箱
            viewCount:0,//浏览量
            top:0,//是否顶置
            status:0,//审核状态
            createBy:"",
            createTime:""
       }

        vm.uploadImage = function uploadImage() {

            var url = SYDB+'/common/upload';
            var data = angular.copy(vm.uploadA || {});
            data.file = vm.uploadA.file;
            if(data.file.type.indexOf("image") == -1){
                toaster.pop("error", "请上传正确的图片!");
                vm.uploadA.file = null;
            }else{
                Upload.upload({
                    url: url,
                    data: data
                }).success(function (data) {
                    vm.financing.imgUrl = data.response.url;
                    toaster.pop("info", "上传成功!");
                }).error(function () {
                    toaster.pop("error", "上传失败!");
                });
            }

        }


        vm.uploadFile = function uploadFile() {
            var url = SYDB+'/common/upload';
            var dataB = angular.copy(vm.uploadB|| {});
            dataB.file = vm.uploadB.file;
            Upload.upload({
                url: url,
                data: dataB
            }).success(function (dataB) {
                vm.financing.file = dataB.response.url;
                toaster.pop("info", "上传成功!");
            }).error(function () {
                toaster.pop("error", "上传失败!");
            });
        }

       //保存数据
        vm.save = function save() {
            var token = $.cookie('syFrontUserTokenKey');
            loginService.checkLogin({token:token},function (rel) {
                if (rel.code == 20){
                    vm.financing.detail = $scope.ueditorGetContent("container"),
                        publishNeedService.createFinancing(vm.financing,function (rel) {
                            toaster.pop('info', "操作成功！");
                            $state.go('financingRecommendation');
                        },function (err) {
                            toaster.pop('info', "操作失败！");
                        })
                }else{
                    $uibModal.open({
                        animation : true,
                        templateUrl : "layouts/notLoginModal/notLoginModal.html",
                        controller : "notLoginModalController",
                        controllerAs:"vm",
                        size : "sm",
                    });
                }
            },function (err) {
                $uibModal.open({
                    animation : true,
                    templateUrl : "layouts/notLoginModal/notLoginModal.html",
                    controller : "notLoginModalController",
                    controllerAs:"vm",
                    size : "sm",
                });
            })


        }

    }

})
();
