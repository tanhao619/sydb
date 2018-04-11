(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('EquipmentAddController', EquipmentAddController  );

    EquipmentAddController.$inject = ['$timeout','$scope', '$uibModalInstance','InnovateService','SYDB','Upload','toaster'];

    function EquipmentAddController($timeout,$scope, $uibModalInstance,InnovateService,SYDB,Upload,toaster) {
        var vm = this;
        vm.equipment = {};
        vm.equipmentSave = {};
        vm.imgUrl="";
        vm.cancel = cancel;
        vm.save = save;
        vm.industryList = "";
        vm.selected=""

        //获取行业分类列表
        getIndustrySelect();
        function getIndustrySelect() {
            InnovateService.getIndustrySelect({type:1},function (data) {
                vm.industryList = data.response;
                vm.selected = vm.equipment
            })
        }

        //关闭窗口
        function cancel() {
            $uibModalInstance.close ('true');
        }

        //上传图片
        vm.uploadImage = function uploadImage() {
            var url = SYDB+'/common/upload';
            var data = angular.copy(vm.uploadA || {});
            data.file = vm.uploadA.file;
            if(data.file.type.indexOf("image") != -1){
                Upload.upload({
                    url: url,
                    data: data
                }).success(function (data) {
                    vm.imgUrl = data.response.url;
                    toaster.pop("info", "上传成功!");
                }).error(function () {
                    toaster.pop("error", "上传失败!");
                });
            }else {
                toaster.clear();
                toaster.pop('info', "请上传图片文件！");
            }
        }

        //提交
        function save() {
            vm.equipmentSave = {
                id : 0,
                name: vm.equipment.name,
                owner:vm.equipment.owner,
                type:vm.equipment.type,
                model:vm.equipment.model,
                category:vm.equipment.category,
                value:vm.equipment.value,
                price:vm.equipment.price,
                manufacturer:vm.equipment.manufacturer,
                status:vm.equipment.status,
                detail:$scope.ueditorGetContent("container"),
                imgUrl:vm.imgUrl
            }
            InnovateService.createEquipment(vm.equipmentSave,function (data) {
                toaster.pop('info', "成功！");
                cancel();
            })
        }
    }
})();