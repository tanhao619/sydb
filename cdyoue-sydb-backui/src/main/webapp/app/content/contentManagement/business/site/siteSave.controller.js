/**
 * Created by Administrator on 2017/5/13 0013.
 */
(function(){
    'use strict';
    angular.module('oddApp')
        .controller('SiteSaveController',SiteSaveController);

    SiteSaveController.$inject = ['$scope', 'items','$timeout', 'BusinessService', 'Upload','SYDB','$uibModalInstance','toaster'];

    function SiteSaveController($scope, items,$timeout, BusinessService, Upload, SYDB,$uibModalInstance,toaster) {
        var vm=this;
        vm.cancel = cancel; // 关闭
        //document.title = "发布场地服务";
        vm.space={
            address:'' ,// 详细地址 ,
            area:'' ,// 所属区域省级区域 ,
            areaNext:'',// 所属区域市级区域 ,
            contact:'' ,// 联系方式 ,
            contactor:'' ,// 联系人 ,
            coverImgUrl:'' ,// 封面 ,
            introduction:'' ,// 简介 ,
            number:'' ,// 数量 ,
            rent:'' ,// 租金 ,
            title:'' ,// 名称 ,
            type:''// 出租方式,1 场地出租 2 工位出租
        };
        vm.address= '';
        vm.space.type =1;
        vm.dataImage = {file: null};
        vm.selectLocation =  {
            province:'北京市',
            city:'东城区',
            address:''
        };
        vm.save = save;//保存
        vm.uploadImage = uploadImage;//上传图片
        vm.id = items.id;

        if (vm.id != null && vm.id != '') {
            initPlace();
        }

        // 初始化城市创建
        $timeout(function() {
            $('#locations').citys({
                required: true,
                //nodata:'disabled',
                onChange: function (data) {
                    console.log(data);
                    vm.selectLocation =  {
                        province:data.province,
                        city:data.city,
                    };
                    vm.space.area=data.provinceCode;
                    vm.space.areaNext=data.cityCode;
                }
            });
        },13);


        function initPlace() {
            BusinessService.getSpaceDetail({id: vm.id}, function (rel) {
                $scope.ueditorSetContent("spaceEdit", rel.response.introduction);
                vm.space = rel.response;
                if(rel.response.address != null && rel.response.address != ''){
                    vm.selectLocation = angular.fromJson(rel.response.address);
                    vm.address = angular.fromJson(rel.response.address).address;
                }
                // 城市插件回显
                $timeout(function(){
                        var address = JSON.parse(rel.response.address);
                        $("#locations").citys({
                            province:address.province,
                            city:address.city
                        });
                        // vm.selectAddress.province = address.province;
                        // vm.selectAddress.city = address.city;
                },14)
            });
        }
        vm.spaceEditShow= false;

        function save() {
            if(vm.space.coverImgUrl == '' || vm.space.coverImgUrl == null){
                // ModalTips.setMessage(false, '图片上传失败');
                toaster.pop('info', "图片上传失败");
                return;
            }
            vm.space.introduction = $scope.ueditorGetContent("spaceEdit");
            if($scope.ueditorGetContent("spaceEdit").length<=0 ){
                vm.spaceEditShow = true;
                return;
            }
            vm.selectLocation.address = vm.address;
            vm.space.address=angular.toJson(vm.selectLocation);
            // console.log(vm.space);
            if (vm.id !=null && vm.id !=''){
                BusinessService.updateSpace({id:vm.id},vm.space, function success(obj) {
                    if(obj.status == "SUCCESS"){
                        toaster.pop('info', obj.message);
                        cancel();
                        window.history.go(0);
                    }
                },function error (obj) {
                    if(obj.message){
                        toaster.pop('error', obj.message+":"+obj.description);
                    }
                });
            }else {
                BusinessService.saveSpace(vm.space, function success(obj) {
                    if(obj.status == "SUCCESS"){
                        toaster.pop('info', obj.message);
                        cancel();
                        window.history.go(0);
                    }
                },function error (obj) {
                    if(obj.message){
                        toaster.pop('error', obj.message+":"+obj.description);
                    }
                });
            }

        }


        function uploadImage() {
            var url = SYDB+'/common/upload';
            var data = angular.copy(vm.dataImage || {});
            data.file = vm.dataImage.file;
            if (data.file == null) return
            if (!data.file.name.match("\\w+\\.(jpg|jpeg|gif|png).*")){
                toaster.pop('info', '您选择的不是图片');
                return;
            }

            Upload.upload({
                url: url,
                data: data
            }).success(function (rel) {
                vm.space.coverImgUrl = rel.response.url;
            }).error(function () {
            });
        }

        function ifSuccess(result) {
            var status = result.code;
            if (status === 20 ) {
                return true;
            } else {
                return false;
            }
        }

        function cancel() {
            $uibModalInstance.dismiss('cancel');
        }
    }
})();