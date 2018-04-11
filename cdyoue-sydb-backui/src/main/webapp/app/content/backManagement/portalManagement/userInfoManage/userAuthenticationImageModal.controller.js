(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('UserAuthenticationImageModalController', UserAuthenticationImageModalController);
    UserAuthenticationImageModalController.$inject = ['$uibModalInstance','$timeout', 'items'];
    function UserAuthenticationImageModalController( $uibModalInstance,$timeout, items) {
        var vm = this;
        vm.items = items;
        $timeout(function(){
            zoomImage(); // 缩放图片
        },400);

        vm.cancel = cancel; // 关闭
        function cancel() {
            $uibModalInstance.dismiss('cancel');
        }
        function zoomImage() {
            var imgs = $(".zoomImages");
            for(var i=0;i<imgs.length;i++){
                if(imgs[i].width>750){
                    imgs[i].width = 750;
                }
            }
        }
    }
})();