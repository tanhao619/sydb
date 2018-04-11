/**
 * Created by PC-45 on 2017/4/20.
 */
(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('ProjectBouncesModalController', ProjectBouncesModalController);

    ProjectBouncesModalController.$inject = ['$scope','Upload','SYDB','toaster','projectDeclarationService', '$uibModalInstance','items'];

    function ProjectBouncesModalController($scope,Upload,SYDB,toaster,projectDeclarationService,$uibModalInstance,items) {
        var vm = this;
        vm.entity={};
        vm.uploadFile=uploadFile;
        vm.save=save;
        vm.cancel=cancel;
        function uploadFile() {
            if (!vm.project.file){
                return;
            }
            var url = SYDB+'/common/upload';
            var data = angular.copy(vm.project || {});
            data.file = vm.project.file;
            //限制不能上传可执行文件
            var fileLastName=data.file.name.substring(data.file.name.indexOf('.'),data.file.name.length);
            if(fileLastName === ".exe" || fileLastName === ".sys" || fileLastName === ".com" || fileLastName === ".dll" || fileLastName === ".bat" || fileLastName === ".scr" || fileLastName === ".cmd" || fileLastName === ".dat"){
                toaster.clear();
                toaster.pop("warning", "上传文件格式不对!");
                vm.project.imageUrl=null;
                return;
            }
            Upload.upload({
                url: url,
                data: data
            }).success(function (data) {
                vm.attachment={
                    url:data.response.url,
                    name:data.response.name,
                    id:data.response.id
                };
                vm.entity.attachmentUrl = data.response.url;
                vm.project.imageUrl = data.response.url;
                toaster.clear();
                toaster.pop("info", "上传成功!");
            }).error(function () {
                toaster.clear();
                toaster.pop("error", "上传失败!");
            });
        }

        function save() {
            vm.entity.projectId=items;
            projectDeclarationService.saveProjectDeclarationPeople(vm.entity,function (data) {
                console.log(vm.entity);
                console.log("保存成功");
                cancel();
            },function (err) {
                console.log("保存失败")
            })
        }
        function cancel() {
            $uibModalInstance.dismiss('cancel');
        }
    }

})();
