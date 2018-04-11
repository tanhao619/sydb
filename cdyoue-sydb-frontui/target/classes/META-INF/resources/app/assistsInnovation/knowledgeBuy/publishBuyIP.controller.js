/**
 * Created by PC-45 on 2017/4/20.
 */
(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('PublishBuyIPController', PublishBuyIPController);


    PublishBuyIPController.$inject = ['$scope', 'BuyIPService', '$state', "$stateParams",'$uibModal'];

    function PublishBuyIPController($scope, BuyIPService, $state, $stateParams,$uibModal) {
        var vm = this;
        vm.choose = choose;//点击选择事件
        vm.containerFlag = true;//富文本为空时提示消息
        vm.intellectual = {
            category:"2",
            transactionType: 1,
            patentType: 1
        };//表单提交数据
        vm.save = save;//提交事件
        vm.editAction = false;

        //UE.delEditor('container');
        //var ue = UE.getEditor('container');

        vm.init = init;

        init();//页面初始化

        function init() {
            vm.id = $stateParams.id;
            if (vm.id) {
                editInit(vm.id);
            } else {
                //document.title = '知识产权求购-发布'//设置页面title
            }
            // console.log(vm.containerFlag);
        }

        function editInit(id) {
            vm.editAction = true;
            //document.title = '知识产权求购-编辑'//设置页面title
            BuyIPService.getDetails({id: id}, editInitSuccess, editInitError)
        }

        function editInitSuccess(result) {
            var success = ifSuccess(result);
            if (!success) {
                return;
            }
            // console.log(result);
            vm.intellectual = result.response.intellectual;
            console.log(vm.intellectual);
            if (vm.intellectual.content != null && vm.intellectual.content != undefined) {
                $scope.ueditorSetContent("container", vm.intellectual.content);
            }
            vm.containerFlag = true;
        }

        function editInitError(result) {
           // alert(result.message);
        }

        function save() {
            vm.intellectual.content = $scope.ueditorGetContent('container');
            // vm.intellectual.content = ue.getContent();
            if (vm.intellectual.content == undefined || vm.intellectual.content.length <= 0) {
                vm.containerFlag = false;
                return;
            }
            vm.containerFlag = true;

            if (vm.editAction) {
                BuyIPService.update({id: vm.id}, vm.intellectual, updateSuccess, updateError);
            } else {
                BuyIPService.publish(vm.intellectual, publishSuccess, publishError);
            }
        }

        function updateSuccess(result) {
            var status = result.status;
            if (status.toLowerCase() == "fail".toLowerCase()) {
                publishError();
                return;
            }
            //$state.go('buyPublishInfo', {status: '0'});
            $state.go('personalBuy');
        }

        function updateError(result) {
            //$state.go('buyPublishInfo', {status: '1'});
            //alert('编辑失败')

        }

        function publishSuccess(result) {
            var status = result.status;
            if (status.toLowerCase() == "fail".toLowerCase()) {
                publishError();
                return;
            }
           $state.go('buyIP');
            // alert("发布成功");
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

        function publishError(result) {
            //$state.go('buyPublishInfo', {status: '1'});
            // alert("发布失败");
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


        function ifSuccess(result) {
            var status = result.status;
            if (status.toLowerCase() == "fail".toLowerCase()) {
                //alert(result.message);
                return false;
            } else {
                return true;
            }
        }


    }
})();
