(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('projectDeclarationController', projectDeclarationController);

    projectDeclarationController.$inject = ['$scope','$rootScope', '$state','projectDeclarationService','Upload','SYDB','toaster','$uibModal','loginService'];

    function projectDeclarationController($scope,$rootScope, $state,projectDeclarationService,Upload,SYDB,toaster,$uibModal,loginService) {
        var vm = this;
        vm.entity={};
        vm.q=null;
        vm.openModal2 = openModal2;
        vm.projectTime = [{key: '发文时间', value:null},{key: '最近一周', value: 'latestWeek'}, {key: '最近一个月', value: 'latestMonth'}];
        vm.queryModel = {
            pageNumber: 0,
            pageSize: 10,
            sort: '-updateTime',
            q:null,
            time:null
        };
        // vm.uploadFile=uploadFile;//上传附件
        // vm.save=save;//保存上传附件，申报人信息
        vm.selectPage=selectPage; //设置分页
        vm.selectPageSize = selectPageSize; //设置分页大小
        vm.query = query; // 关键字搜索
        vm.goes=goes;//改变时间重新初始化列表
        vm.checkLogin=checkLogin;//申报时检查是否登录

        // if(JSON.parse(sessionStorage.getItem("syFrontUserTokenKey"))){
        //     $rootScope.SY_IsLogin = true;
        // }else {
        //     $rootScope.SY_IsLogin = false;
        // }
        vm.booleanLogin=false;
        var token = $.cookie('syFrontUserTokenKey');
        loginService.checkLogin({token:token},function (rel) {
            if (rel.code == 20){
                vm.booleanLogin=true;
            }
        },function (err) {
            console.log(err);
        });
        function openModal2 (id) {
            var out = $uibModal.open({
                animation : true,
                templateUrl : "app/assistsInnovation/projectDeclaration/projectBouncesModal.html",
                controller : "ProjectBouncesModalController",
                controllerAs:"vm",
                size : "lg",
                resolve : {
                    items : function() {
                        return id;
                    }
                }
            });
            out.result.then(function (result) {
                console.log(result); //result关闭是回传的值
            }, function (reason) {
                //init();
            });
        }
        function checkLogin(id) {
            if(vm.booleanLogin){
                openModal2 (id);
            }else {
                $uibModal.open({
                    animation : true,
                    templateUrl : "layouts/notLoginModal/notLoginModal.html",
                    controller : "notLoginModalController",
                    controllerAs:"vm",
                     size : "sm",
                });
               // alert("请先登录！");
            }
        }

        init();//初始化

        function init() {
            projectDeclarationService.getProjectDeclaration(vm.queryModel, function (data) {
                vm.pager = data.response;
            })
        }
        function query(queryModel) {
            queryModel.pageNumber = 0;
            queryModel.pageSize=10;
            queryModel.sort='-updateTime';
            queryModel.q=vm.q;
            projectDeclarationService.getProjectDeclaration(queryModel, function (data) {
                vm.pager = data.response;
            })
        }

        function selectPage(select, current, max) {
            if(select !=current){
                if(select >max){
                    return;
                }
                vm.queryModel.pageNumber = select;
                vm.q=vm.queryModel.q;
                init();
                vm.pager.pageNum = select;
            }
        };

        function selectPageSize(pageSize) {
            vm.queryModel.pageNumber = 0;
            vm.queryModel.pageSize = pageSize;
        }

        // function uploadFile() {
        //     if (!vm.project.file){
        //         return;
        //     }
        //         var url = SYDB+'/common/upload';
        //         var data = angular.copy(vm.project || {});
        //         data.file = vm.project.file;
        //         Upload.upload({
        //             url: url,
        //             data: data
        //         }).success(function (data) {
        //             console.log(data)
        //             vm.attachment={
        //                 url:data.response.url,
        //                 name:data.response.name,
        //                 id:data.response.id
        //             };
        //             vm.entity.attachmentUrl =vm.attachment.url;
        //             vm.project.imageUrl = data.response.url;
        //             vm.project.imageName=data.response.name;
        //             toaster.pop("info", "上传成功!");
        //         }).error(function () {
        //             toaster.pop("error", "上传失败!");
        //         });
        // }
        //
        // function save() {
        //     projectDeclarationService.saveProjectDeclarationPeople(vm.entity,function (data) {
        //         console.log(vm.entity)
        //         console.log("保存成功");
        //     },function (err) {
        //         console.log("保存失败")
        //     })
        // }

        function goes() {
            vm.queryModel.pageNumber = 0;
            init();

        }
    }
})();
