/**
 * Created by PC-45 on 2017/4/20.
 */
(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('publishSaleIPController', publishSaleIPController);

    publishSaleIPController.$inject = ['$scope', 'SaleIPService', '$state', 'Upload', 'toaster', '$stateParams','SYDB','$uibModal'];

    function publishSaleIPController($scope, SaleIPService, $state, Upload, toaster, $stateParams,SYDB,$uibModal) {
        var vm = this;
        vm.data = {
            copyright: {},
            patent: {},
            trademark: {}
        };
        vm.type;
        vm.choose = choose;//点击选择事件
        vm.containerFlag = true;//富文本为空时提示消息
        vm.intellectual = {
            category: '2',
            transactionType: 1,
            patentType: 1
        }//表单提交数据
        vm.save = save;//提交事件
        vm.uploadImage = uploadImage;//上传图片
        vm.editAction = false;
        vm.sta = true;
        //UE.delEditor('container');
        //var ue = UE.getEditor('container');

        vm.init = init;

        init();//页面初始化

        function init() {
            vm.id = $stateParams.id;
            var type = $stateParams.type;
            if (vm.id && type) {
                editInit(vm.id, type);
            } else {
                //document.title = '知识产权出售-发布'//设置页面title
            }
        }

        //编辑页面初始化
        function editInit(id, type) {
            vm.editAction = true;
            //document.title = '知识产权出售-编辑'//设置页面title
            vm.type = type;
            switch (type) {
                case '1':
                    SaleIPService.getTrademarkDetails({"id": id}, editInitSuccess, editInitError);
                    break;
                case '2':
                    SaleIPService.getPatentDetails({"id": id}, editInitSuccess, editInitError);
                    break;
                case '3':
                    SaleIPService.getCopyrightDetails({"id": id}, editInitSuccess, editInitError);
                    break;
            }
        }

        function editInitSuccess(result) {
            var success = ifSuccess(result);
            if (!success) {
                return;
            }
            // console.log(result);
            switch (vm.type) {
                case '1':
                    vm.intellectual = result.response.trademark;
                    vm.intellectual.category = 1;
                    break;
                case '2':
                    vm.intellectual = result.response.patent;
                    vm.intellectual.category = 2;
                    break;
                case '3':
                    vm.intellectual = result.response.copyright;
                    vm.intellectual.category = 3;
                    break;
            }

            //ue.ready(function () {
            //    if (vm.intellectual.content != null && vm.intellectual.content != undefined) {
            //        ue.setContent(vm.intellectual.content);
            //        vm.containerFlag = true;
            //    }
            //});//ueditor加载完成后赋值

            if (vm.intellectual.content != null && vm.intellectual.content != undefined) {
                $scope.ueditorSetContent("container", vm.intellectual.content);
                vm.containerFlag = true;
                // console.log(vm.containerFlag);
            }
        }

        function editInitError(result) {
            //alert(result.message);
        }

        function save() {
            vm.intellectual.content = $scope.ueditorGetContent('container');
            if (vm.intellectual.content == undefined || vm.intellectual.content.length <= 0) {
                vm.containerFlag = false;
                return;
            }
            vm.containerFlag = true;
            if (vm.editAction) {
                switch (vm.intellectual.category) {
                    case 1:
                        SaleIPService.updateTrademark({id: vm.id}, vm.intellectual, updateSuccess, updateError);
                        break;
                    case 2:
                        SaleIPService.updatePatent({id: vm.id}, vm.intellectual, updateSuccess, updateError);
                        break;
                    case 3:
                        SaleIPService.updateCopyright({id: vm.id}, vm.intellectual, updateSuccess, updateError);
                        break;
                }
            } else {
                switch (vm.intellectual.category) {
                    case '1':
                        SaleIPService.publishTrademark(vm.intellectual, publishSuccess, publishError);
                        break;
                    case '2':
                        SaleIPService.publishPatent(vm.intellectual, publishSuccess, publishError);
                        break;
                    case '3':
                        SaleIPService.publishCopyright(vm.intellectual, publishSuccess, publishError);
                        break;
                }
            }
        }


        function updateSuccess(result) {
            var status = result.status;
            if (status.toLowerCase() == "fail".toLowerCase()) {
                publishError();
                return;
            }
            $state.go('knowledgeSales');
            var out = $uibModal.open({
                animation : true,
                templateUrl : "layouts/messageTips/messageTipsModal.html",
                controller : "messageTipsModalController",
                controllerAs:"vm",
                size : "sm",
                resolve : {
                    items : function() {
                        return "发布成功！！！";
                    }
                }
            });
        }

        function updateError(result) {
            // $state.go('salePublishInfo', {status: '1'});
            $state.go('personalSale');
        }

        function publishSuccess(result) {
            var status = result.status;
            if (status.toLowerCase() == "fail".toLowerCase()) {
                publishError();
                return;
            }
            $state.go('knowledgeSales');
            var out = $uibModal.open({
                animation : true,
                templateUrl : "layouts/messageTips/messageTipsModal.html",
                controller : "messageTipsModalController",
                controllerAs:"vm",
                size : "sm",
                resolve : {
                    items : function() {
                        return "发布成功！！！";
                    }
                }
            });
            // _paq.push(['trackEvent', 'publishIP', 'sale',vm.intellectual.title]);
        }

        function publishError(result) {
            var out = $uibModal.open({
                animation : true,
                templateUrl : "layouts/messageTips/messageTipsModal.html",
                controller : "messageTipsModalController",
                controllerAs:"vm",
                size : "sm",
                resolve : {
                    items : function() {
                        return "发布失败！！！";
                    }
                }
            });
        }


        function choose(type) {
            if (type <= 3) {
                vm.intellectual.patentType = type;
            } else if (type <= 5) {
                vm.intellectual.transactionType = type - 3;
            }
        }


        function uploadImage() {
            var url = SYDB+'/common/upload';
            var data = angular.copy(vm.dataImage || {});
            data.file = vm.dataImage.file;
            if(data.file.type.indexOf("image") != -1){
                vm.sta = false;
                Upload.upload({
                    url: url,
                    data: data
                }).success(function (data) {
                    vm.intellectual.imageUrl = data.response.url;
                    toaster.pop("info", "上传成功!");
                }).error(function () {
                    toaster.pop("error", "上传失败!");
                });
            }else {
                toaster.pop('info', "上传文件格式不对！");
            }
        }

        function ifSuccess(result) {
            var status = result.status;
            if (status.toLowerCase() == "fail".toLowerCase()) {
               // alert(result.message);
                return false;
            } else {
                return true;
            }
        }

    }
})
();
