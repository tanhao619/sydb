/**
 * Created by Administrator on 2017/10/12 0012.
 */
(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('equipmentTopCoverModalController', equipmentTopCoverModalController);

    equipmentTopCoverModalController.$inject = ['$timeout','toaster', '$uibModalInstance','items','InnovateService','Upload','SYDB'];

    function equipmentTopCoverModalController($timeout, toaster,$uibModalInstance,items,InnovateService,Upload,SYDB) {
        var vm = this;

        vm.dataImage = {file: null};

        vm.save = save; // 保存
        vm.uploadImage = uploadImage; // 上传图片
        vm.cancel = cancel;
        var regular = /.*(.jpg|.png|.gif|.jpeg|.bmp)$/;
        //查询详情
        initSyBanner();
        function initSyBanner() {
            InnovateService.getAd({view:'zlcxsbk',id:1},function (rel) {
                if (rel.status == 'SUCCESS'){
                    vm.syBannerDetail = rel.response;
                    vm.updateItems = angular.copy(vm.syBannerDetail);
                }else {
                    toaster.clear();
                    toaster.pop('info', rel .message);
                    $uibModalInstance.dismiss("cancel");
                }
            });
        }

        //上传图片
        function uploadImage() {
            var url = SYDB+'/common/upload';
            var data = angular.copy(vm.dataImage || {});
            data.file = vm.dataImage.file;
            debugger;
            if(data.file.type.indexOf("image") != -1){
                Upload.upload({
                    url: url,
                    data: data
                }).success(function (data) {
                    vm.updateItems.coverImg = data.response.url;
                    toaster.pop('info', '上传图片成功');
                    toaster.clear();
                }).error(function () {
                    toaster.pop('warning', '上传图片失败！');
                    toaster.clear();
                });
            }else {
                toaster.clear();
                toaster.pop('info', "请上传图片文件！");
            }
        }

        //保存置顶信息
        function save () {
            if(regular.test(vm.updateItems.coverImg)){
                InnovateService.updAd({'id':vm.syBannerDetail.orderBy,'view':'zlcxsbk'},vm.updateItems, function success(obj) {
                    if (obj.status == 'SUCCESS') {
                        toaster.pop('info', obj.message);
                        cancel();
                    } else {
                        toaster.pop('info',obj.message+"："+obj.description);
                    }
                })
            }else{
                toaster.pop('info', "请上传图片格式");
                return;
            }

        }

        //关闭弹窗
        function cancel() {
            $uibModalInstance.close(true);
        }

    }
})();