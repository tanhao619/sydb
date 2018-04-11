(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('EquipmentEditorController', EquipmentEditorController  );

    EquipmentEditorController.$inject = ['$timeout','$scope', '$uibModalInstance','items','InnovateService','SYDB','Upload','toaster'];

    function EquipmentEditorController($timeout,$scope, $uibModalInstance,items,InnovateService,SYDB,Upload,toaster) {
        var vm = this;
        vm.id = items;
        vm.cancel = cancel;
        vm.save = save;
        vm.industryList = "";
        vm.selected=""
        vm.imgUrl = "";
        vm.status = -1;
        vm.industryId="";
        vm.equipmentType = "";
        //获取设备详情
        getMessage();
        function getMessage() {
            InnovateService.getEquipmentById({id:vm.id},function (rel) {
                vm.equipment = rel.response;
                vm.industryId = selectedIndustryId(vm.equipment.type);
                $scope.ueditorSetContent("container",vm.equipment.detail);
                if(vm.equipment.status == "未租赁"){
                    vm.status = 0
                    vm.equipment.status=0;
                }else if(vm.equipment.status == "已租赁"){
                    vm.status = 1
                    vm.equipment.status=1;
                }
            })
        }

        //获取行业分类列表
        getIndustrySelect();
        function getIndustrySelect() {
            InnovateService.getIndustrySelect({type:1},function (data) {
                vm.industryList = data.response;
                vm.selected = vm.equipment

            })
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
                    vm.equipment.imgUrl = data.response.url;
                    toaster.pop("info", "上传成功!");
                }).error(function () {
                    toaster.pop("error", "上传失败!");
                });
            }else {
                toaster.clear();
                toaster.pop('info', "请上传图片文件！");
            }
        }

        //关闭窗口
        function cancel() {
            $uibModalInstance.close ('true');
        }


        function selectedIndustryId(name) {
            for (var i = 0;i<vm.industryList.length;i++){
                if(name == vm.industryList[i].name){
                   return vm.industryList[i].id;
                }
            }
        }

        //更改行业类型
        vm.industryChange = function industryChange() {
            vm.industryId = vm.equipmentType;
        }

        //提交修改
        function save() {
            vm.equipment.detail = $scope.ueditorGetContent("container");
            vm.equipment.type = vm.industryId;
            console.log(vm.equipment);
            InnovateService.updateEquipment(vm.equipment,function (data) {
                toaster.pop('info', data .message);
                cancel();
            })
        }

    }
})();