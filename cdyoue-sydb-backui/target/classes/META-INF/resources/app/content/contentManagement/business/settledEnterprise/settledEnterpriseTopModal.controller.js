/**
 * Created by sky on 2017/10/18.
 */
(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('settledEnterpriseTopModalController', settledEnterpriseTopModalController);

    settledEnterpriseTopModalController.$inject = ['$timeout','toaster', '$uibModalInstance','items','BusinessService','Upload','SYDB'];

    function settledEnterpriseTopModalController($timeout, toaster,$uibModalInstance,items,BusinessService,Upload,SYDB) {
        var vm = this;
        vm.enterprise=items;
        vm.topImage=vm.enterprise.topImage;
        vm.cancel = cancel;

        //上传图片
        vm.uploadImage = function uploadImage() {
            var url = SYDB+'/common/upload';
            var data = angular.copy(vm.uploadA || {});
            data.file = vm.uploadA.file;
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
                vm.topImage = data.response.url;
                toaster.clear();
                toaster.pop("info", "上传成功!");
            }).error(function () {
                toaster.clear();
                toaster.pop("error", "上传失败!");
            });
        }

        //保存置顶信息
        vm.save = function save() {
            BusinessService.topEnterprise({id:vm.enterprise.enterpriseId,topImage:vm.topImage},function (rel) {
                cancel();
                toaster.clear();
                toaster.pop('success', rel.message);
            },function (err) {
                toaster.clear();
                toaster.pop('error', err.message+":"+err.description);
            })
        }

        //关闭弹窗
        function cancel() {
            $uibModalInstance.close(true);
        }

    }
})();