(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('RoadshowModalController', RoadshowModalController);

    RoadshowModalController.$inject = ['$timeout', '$uibModalInstance','items'];

    function RoadshowModalController($timeout, $uibModalInstance,items) {
        var vm = this;
        vm.cancel = cancel; // 关闭
        vm.items = items;
        if(vm.items.location){
            var jsonObj = angular.fromJson(vm.items.location);
            vm.location = jsonObj.province+jsonObj.city+jsonObj.address;
        }
        $timeout(function(){
            zoomImage(); // 缩放图片
        },200);

        function cancel () {
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