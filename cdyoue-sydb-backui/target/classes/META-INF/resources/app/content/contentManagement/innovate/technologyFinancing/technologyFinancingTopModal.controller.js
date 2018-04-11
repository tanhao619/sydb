/**
 * Created by Administrator on 2017/10/12 0012.
 */
(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('technologyFinancingTopModalController', technologyFinancingTopModalController);

    technologyFinancingTopModalController.$inject = ['$timeout','toaster', '$uibModalInstance','items','InnovateService','Upload','SYDB'];

    function technologyFinancingTopModalController($timeout, toaster,$uibModalInstance,items,InnovateService,Upload,SYDB) {
        var vm = this;
        vm.financingMessage = {
            id:items
        };
        vm.cancel = cancel;

        getFinancingById();
        function getFinancingById() {
            InnovateService.getFinancingById({id:items},function (rel) {
                vm.financingMessage = rel.response;
            })
        }

        vm.uploadImage = function uploadImage() {
            var url = SYDB+'/common/upload';
            var data = angular.copy(vm.uploadA || {});
            data.file = vm.uploadA.file;
            if(data.file.type.indexOf("image") != -1){
                Upload.upload({
                    url: url,
                    data: data
                }).success(function (data) {
                    vm.financingMessage.topImg = data.response.url;
                    toaster.clear();
                    toaster.pop("info", "上传成功!");
                }).error(function () {
                    toaster.clear();
                    toaster.pop("error", "上传失败!");
                });
            }else {
                toaster.clear();
                toaster.pop('info', "请上传图片文件！");
            }
        }

        vm.save = function save() {
            InnovateService.madeTop(vm.financingMessage,function (rel) {
                toaster.clear();
                toaster.pop('info', "操作成功！");
                cancel();
            },function (err) {
                toaster.clear();
                toaster.pop('info', "操作失败！");
            })
        }

        function cancel() {
            $uibModalInstance.close(true);
        }

    }
})();