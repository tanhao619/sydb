(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('LectureModalController', LectureModalController);

    LectureModalController.$inject = ['SYDB','$scope','$timeout', '$uibModalInstance','items','InnovateService','Upload','toaster'];

    function LectureModalController(SYDB,$scope,$timeout, $uibModalInstance,items,InnovateService,Upload,toaster) {
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
                // var startDate = $('#start').val();
                // $("#end").datetimepicker('setStartDate',startDate);
                $("#start").datetimepicker('hide');
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
                vm.ProjectLectureDetail={};
                vm.updateItems={};
            } else {
                InnovateService.getProjectLectureDetail({id:items.id},function (rel) {
                    if (rel.status == 'SUCCESS'){
                        vm.ProjectLectureDetail = rel.response;
                        vm.updateItems = angular.copy(vm.ProjectLectureDetail);
                        $scope.ueditorSetContent("newsAddContainer",vm.updateItems.content);
                    }else {
                        toaster.clear();
                        toaster.pop('success', rel.message);
                        $uibModalInstance.dismiss("cancel");
                    }
                },function (err) {
                    console.log(err);
                });
            }

        }
        vm.editShow=false;
        function save() {
            vm.updateItems.content = $scope.ueditorGetContent("newsAddContainer");
            if(vm.updateItems.content =="" || vm.updateItems.content == null){
                vm.editShow=true;
                return;
            }
            if (items.showModel == "editor"){
                InnovateService.updateProjectLecture({'id':items.id},vm.updateItems, function success(obj) {
                    if (obj.status == 'SUCCESS') {
                        toaster.clear();
                        toaster.pop('success', obj.message);
                        cancel();
                    }
                });
            } else if(items.showModel == "add"){
                InnovateService.createProjectLecture(vm.updateItems, function success(obj) {
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
            //限制只能上传图片
            if((data.file.type.substring(0,data.file.type.indexOf('/'))) !== "image"){
                toaster.clear();
                toaster.pop("warning", "上传文件格式不对!");
                return;
            }
            Upload.upload({
                url: url,
                data: data
            }).success(function (data) {
                vm.updateItems.image = data.response.url;
                // zoomImage();
            }).error(function () {
            });
        }
    }
})();