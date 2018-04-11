(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('topRoadshowModalController', topRoadshowModalController);

    topRoadshowModalController.$inject = ['SYDB', '$uibModalInstance', 'Upload', 'BusinessService', 'items','toaster'];

    function topRoadshowModalController(SYDB, $uibModalInstance, Upload, BusinessService, items,toaster) {
        var vm = this;

        vm.cancel = cancel; // 关闭
        vm.save = save; // 保存
        vm.uploadImage = uploadImage; // 上传图片

        vm.updateItems={};
        vm.dataImage = {file: null};
        vm.updateItems.id = items.id;

        initSyBanner();
        function initSyBanner() {
            vm.updateItems.topImg = items.topImg;
        }

        function save () {
            BusinessService.activityTop(vm.updateItems, function success(obj) {
                if (obj.status == 'SUCCESS') {
                    toaster.pop('info', obj.message);
                    cancel();
                } else {
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
            if(data.file.type.indexOf("image") != -1){
                Upload.upload({
                    url: url,
                    data: data
                }).success(function (data) {
                    vm.updateItems.topImg = data.response.url;
                }).error(function () {
                });
            }else {
                toaster.clear();
                toaster.pop('info', "请上传图片文件！");
            }
        }
    }
})();