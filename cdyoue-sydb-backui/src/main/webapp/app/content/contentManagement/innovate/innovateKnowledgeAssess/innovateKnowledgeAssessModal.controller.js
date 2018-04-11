(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('innovateKnowledgeAssessController', innovateKnowledgeAssessController);

    innovateKnowledgeAssessController.$inject = ['$timeout','items', '$uibModalInstance','InnovateService','Upload','SYDB','toaster'];

    function innovateKnowledgeAssessController($timeout,items, $uibModalInstance,InnovateService,Upload,SYDB,toaster) {
        var vm = this;
        vm.data = {};
        vm.init = init;
        vm.cancel = cancel; // 关闭
        vm.uploadImage = uploadImage;//上传图片
        vm.assess = {
            logoImg:"",
        };
        vm.save = save; // 保存
        vm.items = items;
        vm.updateItems = {};
        vm.questionsObj = {
            q:"",
            sort:"-publishTime"
        };

        vm.dataImage = {file: null};


        $timeout(function(){
            zoomImage(); // 缩放图片
        },200);

        function save () {
            InnovateService.insertPartner(vm.assess, function success(obj) {
                if (obj.status == 'SUCCESS') {
                    cancel();
                }
                window.history.go(0);
                toaster.pop('info', obj.message);
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
            InnovateService.getPagePartners(vm.questionsObj);
        }
        function queryDataSuccess(result) {
            var status = result.status;
            if(status.toLowerCase() == "fail".toLowerCase()) {
                //alert(result.message);
                return;
            }
            vm.data = result.response;
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
                    vm.assess.logoImg = data.response.url;
                    toaster.pop("info", "上传成功!");
                }).error(function () {
                    toaster.pop("error", "上传失败!");
                });
            }else {
                vm.assess.logoImg=null;
                toaster.clear();
                toaster.pop('info', "请上传图片文件！");
            }
        }


        function cancel() {
            $uibModalInstance.dismiss('cancel');
        }
    }
})();


