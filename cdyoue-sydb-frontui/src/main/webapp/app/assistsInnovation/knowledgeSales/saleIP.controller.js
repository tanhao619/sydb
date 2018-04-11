/**
 * Created by Administrator on 2017/5/15 0015.
 */
/**
 * Created by Administrator on 2017/5/15 0015.
 */
(function () {
    'use strict';

    angular.module('sydbApp')
        .controller('knowledgeSalesController', knowledgeSalesController);

    knowledgeSalesController.$inject = ['$rootScope', 'SaleIPService', '$state','$uibModal','loginService'];

    function knowledgeSalesController($rootScope, SaleIPService, $state,$uibModal,loginService) {
        var vm = this;
        vm.data = {};
        vm.checkLogin=checkLogin;//检查是否登录
        vm.selectPage = selectPage;//分页插件 选择第几页事件
        vm.selectPageSize = selectPageSize;//分页插件 现在显示多少条事件
        vm.search = search;//条件检索
        vm.getDetails = getDetails;//点击查看详情
        vm.publishSaleIP = publishSaleIP;//发布出售需求
        vm.pageSize = 10;
        vm.pageNumber = 0;
        vm.questionsObj = {
            type: 2,//默认展示专利类型知识产权
            transactionType: '',
            sort:"-publishTime"
        };
        vm.emptyFlag = false;//列表数据是否为空
        vm.init = init;

        init();//页面初始化

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
        });

        function init() {
            //document.title = '知识产权-出售列表页'//设置页面title
            queryData();
        }


        function publishSaleIP() {
            if ($rootScope.SY_IsLogin){
                $state.go("publishSaleIP");
            }else {
                if (confirm("用户未登录，是否前往登录")) {
                    $state.go("login");
                } else {
                    return;
                }
            }
        };

        function getDetails(id,type) {
            $state.go("saleIPDetails", {type:type,id: id});
        };


        function search(type) {
            if (type < 4) {
                vm.questionsObj.type = type;
            } else {
                if (type == 5) {
                    vm.questionsObj.transactionType = 1;
                } else if (type == 6) {
                    vm.questionsObj.transactionType = 2;
                } else {
                    vm.questionsObj.transactionType = '';
                }
            }
            vm.questionsObj.pageNumber = 0;
            queryData();
        }

        /**
         * 异步请求数据
         */
        function queryData() {
            SaleIPService.getIntellectuals(vm.questionsObj, getIntellectualsSuccess, getIntellectualsError);
        }

        /**
         * 获取列表成功
         * @param result
         */
        function getIntellectualsSuccess(result) {
            vm.loaded = true;
            var status = result.status;
            if(status.toLowerCase() == "fail".toLowerCase()) {
                vm.emptyFlag = true;
                vm.tipPop=true;
                return;
            }
            vm.pager = result.response;
            vm.tipPop=false;
        }

        /**
         * 获取列表失败
         * @param result
         */
        function getIntellectualsError(result) {
            vm.loaded = true;
            var status = result.status;
            if (status == 404) {
                vm.emptyFlag = true;
                vm.tipPop=true;
                vm.pager = {};
                //console.log("获取知产求购列表失败");
            } else {
                vm.emptyFlag = false;
                vm.tipPop=false;
            }

        }


        function selectPage(select, current, max) {
            if (select != current) {
                if (select > max) {
                    return;
                }
                vm.questionsObj.pageNumber = select;
                SaleIPService.getIntellectuals(vm.questionsObj, function (obj) {
                    vm.pager = obj.response;
                    vm.pager.pageNum = select;
                });
            }
        }

        function selectPageSize(pageSize) {
            if (pageSize == null || pageSize == '') {
                return;
            }
            vm.questionsObj.pageSize = pageSize;
            vm.questionsObj.pageNumber = 0;
            SaleIPService.getIntellectuals(vm.questionsObj, function (obj) {
                vm.pager = obj.response;
                vm.pager.pageNumber = 0;
            })
        }

        function checkLogin() {
            if(vm.booleanLogin){
                $state.go("publishSaleIP");
            }else {
                $uibModal.open({
                    animation : true,
                    templateUrl : "layouts/notLoginModal/notLoginModal.html",
                    controller : "notLoginModalController",
                    controllerAs:"vm",
                    size : "sm",
                });
            }
        }

    }
})();