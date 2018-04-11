(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('InnovateProjectDeclareModalController', InnovateProjectDeclareModalController);

    InnovateProjectDeclareModalController.$inject = ['SYDB','$scope','$timeout', '$uibModalInstance','items','InnovateService','Upload','TimeSelect','toaster'];

    function InnovateProjectDeclareModalController(SYDB,$scope,$timeout, $uibModalInstance,items,InnovateService,Upload,TimeSelect,toaster) {
        var vm = this;
        vm.cancel=cancel; // 关闭
        vm.editor=editor; // 编辑
        vm.save=save; // 保存
        vm.uploadImage = uploadImage; // 上传图片

        vm.items = items;
        vm.updateItems={};
        vm.syBannerDetail={};
        vm.queryProjectDeclareModel = {
            q: null
        };
        vm.dataImage = {file: null};
        initSyBanner();
        $timeout(initTime, 100);
        function initTime() {
            // TimeSelect.getTime("start", "end");
            $("#start").datetimepicker({
                language:  'zh',
                format: "yyyy-MM-dd hh:ii:ss",
                autoclose: true,
                todayBtn: true,
                pickerPosition: "bottom",
                minuteStep: 1
            }).on('change',function(ev){
                var startDate = $('#start').val();
                $("#end").datetimepicker('setStartDate',startDate);
                $("#start").datetimepicker('hide');
            });
            $("#end").datetimepicker({
                language:  'zh',
                format: "yyyy-MM-dd hh:ii:ss",
                autoclose: true,
                todayBtn: true,
                pickerPosition: "bottom",
                minuteStep: 1
            }).on('change',function(ev){
                var returnDate = $("#end").val();
                $("#start").datetimepicker('setReturnDate',returnDate);
                $("#end").datetimepicker('hide');
            });
        }

        function initSyBanner() {
            // 是编辑还是查看
            if(items.showModel == "show"){
                vm.isEditor = false;
            }else{
                vm.isEditor = true;
            }
            if (items.id==null){
                vm.ProjectDeclareDetail={};
                vm.updateItems={};
            } else {
                InnovateService.getProjectDeclareDetail({id:items.id},function (rel) {
                    if (rel.status == 'SUCCESS'){
                        vm.ProjectDeclareDetail = rel.response;
                        vm.updateItems = angular.copy(vm.ProjectDeclareDetail);
                        $scope.ueditorSetContent("newsAddContainer",vm.updateItems.content);
                    }else {
                        toaster.clear();
                        toaster.pop('error', rel.message+":"+rel.description);
                        $uibModalInstance.dismiss("cancel");
                    }
                },function (err) {
                    console.log(err);
                });
            }
          //  TimeSelect.getTime("start", "end");
        }
        vm.editShow=false;
        function save() {
            vm.updateItems.content = $scope.ueditorGetContent("newsAddContainer");
            if(vm.updateItems.content =="" || vm.updateItems.content == null){
                vm.editShow=true;
                return;
            }
            if (items.showModel == "editor"){
                InnovateService.updateProjectDeclare({'id':items.id},vm.updateItems, function success(obj) {
                    if (obj.status == 'SUCCESS') {
                        toaster.clear();
                        toaster.pop('success', obj.message);
                        cancel();
                    }
                });
            } else if(items.showModel == "add"){
                InnovateService.createProjectDeclare(vm.updateItems, function success(obj) {
                    if (obj.status == 'SUCCESS') {
                        toaster.clear();
                        toaster.pop('success', obj.message);
                        cancel();
                    }
                });
            } else {
                return;
            }
        }

        function editor() {
            vm.isEditor = true;
            items.showModel = "editor";
        }
        function cancel() {
            $uibModalInstance.dismiss('cancel');
        }

        function uploadImage() {
            console.log(vm.items);
            var url = SYDB+'/common/upload';
            var data = angular.copy(vm.dataImage || {});
            data.file = vm.dataImage.file;
            //限制不能上传可执行文件
            var fileLastName=data.file.name.substring(data.file.name.indexOf('.'),data.file.name.length);
            if(fileLastName === ".exe" || fileLastName === ".sys" || fileLastName === ".com" || fileLastName === ".dll" || fileLastName === ".bat" || fileLastName === ".scr" || fileLastName === ".cmd" || fileLastName === ".dat"){
                toaster.clear();
                toaster.pop("warning", "上传文件格式不对!");
                vm.updateItems.attachmentUrl=null;
                return;
            }
            Upload.upload({
                url: url,
                data: data
            }).success(function (data) {
                vm.updateItems.attachmentUrl = data.response.url;
                toaster.clear();
                toaster.pop("info", "上传成功!");
            }).error(function () {
                toaster.clear();
                toaster.pop("error", "上传失败!");
            });
        }
    }
})();