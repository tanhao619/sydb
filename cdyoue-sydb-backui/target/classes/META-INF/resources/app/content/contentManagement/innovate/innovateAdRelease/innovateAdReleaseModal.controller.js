(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('InnovateAdReleaseModalController', InnovateAdReleaseModalController);

    InnovateAdReleaseModalController.$inject = ['toaster', '$uibModalInstance', 'items','InnovateService', 'Upload', 'SYDB'];

    function InnovateAdReleaseModalController(toaster, $uibModalInstance, items, InnovateService, Upload, SYDB) {
        var vm = this;

        vm.cancel = cancel; // 关闭
        vm.editor = editor; // 编辑
        vm.save = save; // 保存
        vm.uploadImage = uploadImage; // 上传图片

        vm.items = items;
        vm.dataImage = {file: null};

        initSyBanner();
        function initSyBanner() {
            InnovateService.getAd({view:'zlcx',id:items.id},function (rel) {
                if (rel.status == 'SUCCESS'){
                    vm.syBannerDetail = rel.response;
                    vm.updateItems = angular.copy(vm.syBannerDetail);
                }else {
                    toaster.clear();
                    toaster.pop('error', rel.message+":"+rel.description);
                    $uibModalInstance.dismiss("cancel");
                }
            },function (err) {
                //console.log(err);
            });
        }

        // 是编辑还是查看
        if(items.showModel == "show"){
            vm.isEditor = false;
        }else{
            vm.isEditor = true;
        }

        /*$timeout(function(){
            zoomImage(); // 缩放图片
        },200);*/

        function save () {
            InnovateService.updAd({'id':vm.syBannerDetail.orderBy,'view':'zlcx'},vm.updateItems, function success(obj) {
                if (obj.status == 'SUCCESS') {
                    toaster.clear();
                    toaster.pop('success', obj.message);
                    cancel();
                } else {
                    toaster.clear();
                    toaster.pop('error', obj.message+":"+obj.description);
                }
            },function error(obj) {

            });
        }

        function editor () {
            vm.isEditor = true;
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
                vm.updateItems.coverImg = data.response.url;
                toaster.clear();
                toaster.pop('info', '上传图片成功');
                //zoomImage();
            }).error(function () {
                toaster.clear();
                toaster.pop('warning', '上传图片失败！');
            });
        }

        /*function zoomImage() {
            var imgs = $(".zoomImages");
            for(var i=0;i<imgs.length;i++){
                if(imgs[i].width>750){
                    imgs[i].width = 750;
                }
            }
        }*/
    }
})();