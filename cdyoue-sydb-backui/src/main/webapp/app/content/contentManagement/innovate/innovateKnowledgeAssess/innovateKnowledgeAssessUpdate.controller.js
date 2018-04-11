(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('innovateKnowledgeAssessUpdateController', innovateKnowledgeAssessUpdateController);

    innovateKnowledgeAssessUpdateController.$inject = ['$timeout','items', '$uibModalInstance','InnovateService','Upload','SYDB','toaster'];

    function innovateKnowledgeAssessUpdateController($timeout,items, $uibModalInstance,InnovateService,Upload,SYDB,toaster) {
        var vm = this;
        vm.init = init;
        vm.cancel = cancel; // 关闭
        vm.save = save;
        vm.uploadImage = uploadImage;//上传图片
        vm.assess = {
            logoImg:"",
        };
        init();//页面初始化

        vm.items = items;
        vm.updateItems = {};

        vm.dataImage = {file: null};


        $timeout(function(){
            zoomImage(); // 缩放图片
        },200);


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
            queryData(id);
        }

        function queryData(id) {
            InnovateService.getPartnerDetail({"id": id}, queryDataSuccess, queryDataErroe);
        }

        function save () {
            InnovateService.updatePartner({"id":items.id},vm.assess, function success(obj) {
                if (obj.status == 'SUCCESS') {
                    cancel();
                    toaster.pop('info', obj.message);
                }
            },function error(obj) {
                toaster.pop('error', obj.message+":"+obj.description);
            });
        }

        function queryDataSuccess(result) {
            var status = result.status;
            if(status.toLowerCase() == "fail".toLowerCase()) {
                toaster.pop('error', result.message+":"+result.description);
                return;
            }
            vm.assess = result.response;
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
                toaster.pop('info', "请上传图片文件！");
            }

        }

        function queryDataErroe(result) {

        }

        function cancel() {
            $uibModalInstance.dismiss('cancel');
        }
    }
})();


