/**
 * Created by Administrator on 2017/10/12 0012.
 */
(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('equipmentTopModalController', equipmentTopModalController);

    equipmentTopModalController.$inject = ['$timeout','toaster', '$uibModalInstance','items','InnovateService','Upload','SYDB'];

    function equipmentTopModalController($timeout, toaster,$uibModalInstance,items,InnovateService,Upload,SYDB) {
        var vm = this;

        vm.equipmentMessage = {
            id:items,
            topImg : ""
        };

        vm.cancel = cancel;

        //查询详情
        getMessage();
        function getMessage() {
            InnovateService.getEquipmentById({id:items},function (rel) {
                vm.equipment = rel.response;
            })
        }

        //上传图片
        vm.uploadImage = function uploadImage() {
            var url = SYDB+'/common/upload';
            var data = angular.copy(vm.uploadA || {});
            data.file = vm.uploadA.file;
            if(data.file.type.indexOf("image") != -1){
                Upload.upload({
                    url: url,
                    data: data
                }).success(function (data) {
                    vm.equipment.topImg = data.response.url;
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

        //保存置顶信息
        vm.save = function save() {
            vm.equipmentMessage.topImg = vm.equipment.topImg;
            InnovateService.top(vm.equipmentMessage,function (rel) {
                toaster.clear();
                toaster.pop('info', "操作成功！");
                cancel();
            },function (err) {
                toaster.clear();
                toaster.pop('info', "操作失败！");
            })
        }

        //关闭弹窗
        function cancel() {
            $uibModalInstance.close(true);
        }

    }
})();