(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('topUnscrambleModalController', topUnscrambleModalController);

    topUnscrambleModalController.$inject = ['SYDB', '$uibModalInstance', 'Upload', 'InnovateService', 'items', 'toaster'];

    function topUnscrambleModalController(SYDB, $uibModalInstance, Upload, InnovateService, items, toaster) {
        var vm = this;

        vm.cancel = cancel; // 关闭
        vm.save = save; // 保存
        vm.uploadImage = uploadImage; // 上传图片

        vm.items = items;
        vm.dataImage = {file: null};
        vm.updateItems={};

        initSyBanner();
        function initSyBanner() {
            vm.updateItems.topImgUrl = vm.items.topImgUrl;
        }

        function save () {
            InnovateService.topUnscramble({id:vm.items.id, topImgUrl:vm.updateItems.topImgUrl}, function success(obj) {
                if (obj.status == 'SUCCESS') {
                    toaster.clear();
                    toaster.pop('success', obj.message);
                    cancel();
                } else {
                    toaster.clear();
                    toaster.pop('error', obj.message+":"+obj.description);
                }
            });
        }

        function cancel () {
            $uibModalInstance.dismiss('cancel');
        }

        function uploadImage() {
            var url = SYDB+'/common/upload';
            var data = angular.copy(vm.dataImage || {});
            data.file = vm.dataImage.file;
            if (data.file.type.substring(0, data.file.type.indexOf('/')) !== 'image') {
                toaster.clear();
                toaster.pop('warning', '上传文件格式不对！');
                return;
            }
            Upload.upload({
                url: url,
                data: data
            }).success(function (data) {
                vm.updateItems.topImgUrl = data.response.url;
                toaster.clear();
                toaster.pop('info', '上传图片成功');
            }).error(function () {
                toaster.clear();
                toaster.pop('warning', '上传图片失败！');
            });
        }
    }
})();