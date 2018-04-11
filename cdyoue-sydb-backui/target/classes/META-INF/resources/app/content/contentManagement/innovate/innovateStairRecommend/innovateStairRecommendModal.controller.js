(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('InnovateStairRecommendModalController', InnovateStairRecommendModalController);

    InnovateStairRecommendModalController.$inject = ['$timeout', '$uibModalInstance','items','InnovateService','Upload'];

    function InnovateStairRecommendModalController($timeout, $uibModalInstance,items,InnovateService,Upload) {
        var vm = this;
        // vm.cancel = cancel; // 关闭
        // vm.editor = editor; // 编辑
        // vm.save = save; // 保存
        // vm.uploadImage = uploadImage; // 上传图片
        // vm.items = items;
        // initSyBanner();
        // function initSyBanner() {
        //     AdRelease.getBannerDetail({view:'xqdt',id:items.id},function (rel) {
        //         if (rel.status == 'SUCCESS'){
        //             vm.syBannerDetail = rel.response;
        //             vm.updateItems = angular.copy(vm.syBannerDetail);
        //         }else {
        //             alert(rel.message);
        //             $uibModalInstance.dismiss("cancel");
        //         }
        //     },function (err) {
        //         console.log(err);
        //     });
        // }
        //
        // vm.dataImage = {file: null};
        //
        // // 是编辑还是查看
        // if(items.showModel == "show"){
        //     vm.isEditor = false;
        // }else{
        //     vm.isEditor = true;
        // }
        // $timeout(function(){
        //     zoomImage(); // 缩放图片
        // },200);
        //
        // function save () {
        //     AdRelease.updateBanner({'id':vm.syBannerDetail.orderBy,'view':'xqdt'},vm.updateItems, function success(obj) {
        //         console.log(obj);
        //         if (obj.status == 'SUCCESS') {
        //             alert(obj.message);
        //             cancel();
        //         }
        //     },function error(obj) {
        //         console.log(obj);
        //     });
        // }
        // function editor () {
        //     vm.isEditor = true;
        // }
        // function cancel () {
        //     $uibModalInstance.dismiss('cancel');
        // }
        //
        // function uploadImage() {
        //     console.log(vm.items);
        //     var url = '/zuul/oddjobs/api/common/upload';
        //     var data = angular.copy(vm.dataImage || {});
        //     data.file = vm.dataImage.file;
        //     Upload.upload({
        //         url: url,
        //         data: data
        //     }).success(function (data) {
        //         vm.updateItems.coverImg = data.response.url;
        //         zoomImage();
        //     }).error(function () {
        //     });
        // }
        // function zoomImage() {
        //     var imgs = $(".zoomImages");
        //     for(var i=0;i<imgs.length;i++){
        //         if(imgs[i].width>750){
        //             imgs[i].width = 750;
        //         }
        //     }
        // }
    }
})();