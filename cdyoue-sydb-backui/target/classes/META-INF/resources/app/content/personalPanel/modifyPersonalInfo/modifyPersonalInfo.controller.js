(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('ModifyPersonalInfo', ModifyPersonalInfo);

    ModifyPersonalInfo.$inject = ['$scope', 'ModifyPersonalInfoService'];

    function ModifyPersonalInfo($scope, ModifyPersonalInfoService) {
        var vm = this;
        vm.myInfo = JSON.parse(sessionStorage.getItem("oddBackUserKey"));

        vm.expectFunctionCategory = "";
        getCategorie(); // 获取分类
        myPerson(); // 获取个人信息
        vm.submit = submit;  // 提交数据

        function submit() {
            if(!$scope.ueditorGetContent("ueContent")){
                alert("详细介绍不能为空!");
                return;
            }
            vm.myInfo.expectFunctionCategory = vm.expectFunctionCategory;
            vm.myInfo.introduction = $scope.ueditorGetContent("ueContent");
            ModifyPersonalInfoService.modifyPersonalInfo(vm.myInfo ,function (obj) {
                if(obj.status != "SUCCESS"){
                    if(obj.message){
                        vm.msg = obj.message +"("+ obj.description+")！";
                    }
                    return;
                }
                alert("修改成功！");
            });
        }
        function myPerson() {
            ModifyPersonalInfoService.getMyPerson({},function (obj) {
                if(obj.status != "SUCCESS"){
                    if(obj.message){
                        vm.msg = obj.message +"("+ obj.description+")！";
                    }
                    return;
                }
                vm.myInfo = obj.response;
                if(vm.myInfo.expectFunctionCategory){
                    vm.expectFunctionCategory = vm.myInfo.expectFunctionCategory+"";
                }
                if (vm.myInfo.introduction){
                    $scope.ueditorSetContent("ueContent",vm.myInfo.introduction);
                }
            });
        }
        function getCategorie() {
            ModifyPersonalInfoService.getCategories({type:1}, function success(obj) {
                if (obj.status != "SUCCESS") {
                    return;
                }
                vm.domainsShow = obj.response;
            });
        }
    }
})();
