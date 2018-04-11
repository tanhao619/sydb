(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('SiteUpdateController', SiteUpdateController);

    SiteUpdateController.$inject = ['$timeout', '$uibModalInstance','items','BusinessService','Upload','toaster'];

    function SiteUpdateController($timeout, $uibModalInstance,items,BusinessService,Upload,toaster) {
        var vm = this;
        vm.init = init;
        vm.cancel = cancel; // 关闭
        vm.uploadImage = uploadImage;//上传图片
        vm.place = {};
        vm.assess = {
            logoImg:"",
        };
        init();//页面初始化

        vm.save = save; // 保存
        vm.items = items;
        vm.updateItems = {};

        vm.dataImage = {file: null};


        $timeout(function(){
            zoomImage(); // 缩放图片
        },200);

        function save () {
            BusinessService.insertPartner(vm.assess, function success(obj) {
                if (obj.status == 'SUCCESS') {
                    cancel();
                }
            },function error(obj) {
            });
        }
        function zoomImage() {
            var imgs = $(".zoomImages");
            for(var i=0;i<imgs.length;i++){
                if(imgs[i].width>750){
                    imgs[i].width = 750;
                }
            }
        }

        function init() {
            var id = items.id;
            BusinessService.getSpaceDetail({"id": id}, queryDataSuccess, queryDataErroe);
        }

        function queryDataSuccess(result) {
            var status = result.status;
            if(status.toLowerCase() == "fail".toLowerCase()) {
                //alert(result.message);
                return;
            }
            vm.place = result.response;
        }

        function queryDataErroe(result) {
            //alert('请求数据失败');
            // ModalTips.setMessage(false,'请求数据失败');
        }

        function uploadImage() {
            var url = SYDB+'/common/upload';
            var data = angular.copy(vm.dataImage || {});
            data.file = vm.dataImage.file;
            Upload.upload({
                url: url,
                data: data
            }).success(function (data) {
                vm.assess.logoImg = data.response.url;
                toaster.pop("info", "上传成功!");
            }).error(function () {
                toaster.pop("error", "上传失败!");
            });
        }


        function cancel() {
            $uibModalInstance.dismiss('cancel');
        }
    }
})();