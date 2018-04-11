(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('StairRecommendController', StairRecommendController);

    StairRecommendController.$inject = ['$scope','$uibModal', 'BusinessService'];

    function StairRecommendController($scope, $uibModal,BusinessService) {
        var vm = this;

        // vm.dataShow = true;
        // vm.selectAll = false;
        // vm.msg = "没有数据！";
        // vm.query = {
        //     view:'xqdt'
        // };
        //
        // vm.openModal = openModal; // 打开弹窗
        //
        // init();
        //
        // //初始化数据
        // function init() {
        //     AdRelease.getBannerList(vm.query, function success(obj) {
        //         console.log(obj);
        //         if(obj.status != "SUCCESS"){
        //             vm.dataShow = false;
        //             if(obj.code==40){
        //                 vm.msg = "没有数据！";
        //             }else {
        //                 vm.msg = obj.message +"("+ obj.description+")！";
        //             }
        //             return;
        //         }
        //         vm.dataShow = true;
        //         vm.syBanner = obj.response;
        //     },function error (obj) {
        //         vm.dataShow = false;
        //         if(obj.message){
        //             vm.msg = obj.message +"("+ obj.description+")！";
        //         }
        //     });
        // }
        //
        // function openModal (index,model) {
        //     vm.passData={};
        //     var out = $uibModal.open({
        //         animation : true,
        //         templateUrl : "app/content/businessManagement/adRelease/demandHall/demandHallDetailModal.html",
        //         controller : "DemandHallDetailModalController",
        //         controllerAs:"vm",
        //         size : "lg",
        //         resolve : {
        //             items : function() {
        //                 vm.passData.id=vm.syBanner[index].orderBy;
        //                 vm.passData.showModel = model;
        //                 return vm.passData;
        //             }
        //         }
        //     });
        //     out.result.then(function (result) {
        //         console.log(result); //result关闭是回传的值
        //     }, function (reason) {
        //         init();
        //     });
        // }
    }
})();