/**
 * Created by PC-45 on 2017/4/20.
 */
(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('BuyIPController', BuyIPController);

    BuyIPController.$inject = ['$rootScope', 'BuyIPService', '$state','$uibModal','loginService'];

    function BuyIPController($rootScope, BuyIPService, $state,$uibModal,loginService) {
        var vm = this;
        vm.loading=true;
        vm.data = [];
        vm.checkLogin=checkLogin;//检查是否登录
        vm.selectPage = selectPage;//分页插件 选择第几页事件
        vm.selectPageSize = selectPageSize;//分页插件 现在显示多少条事件
        vm.search = search;//条件检索
        vm.getDetails = getDetails;//点击查看详情
        vm.publish = publish;//发布求购需求
        vm.pageSize = 10;
        vm.pageNumber = 0;

        vm.questionsObj = {
            type: '',
            transactionType : '',
            sort:"-publishTime"
        };
        vm.emptyFlag = false;//列表数据是否为空
        vm.init = init;

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

        init();//页面初始化


        function init() {
           // document.title = '知识产权-求购'//设置页面title
            queryData();
        }


        function getDetails(id) {
            $state.go("buyIPDetails", {id: id});
        };


        function publish() {
                if ($rootScope.SY_IsLogin){
                    $state.go("publishBuyIP");
                }else {
                    if (confirm("用户未登录，是否前往登录")) {
                        $state.go("login");
                    } else {
                        return;
                    }
                }
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
            BuyIPService.getIntellectuals(vm.questionsObj, getIntellectualsSuccess, getIntellectualsError);
        }

        /**
         * 获取列表成功
         * @param result
         */
        function getIntellectualsSuccess(result) {
            vm.loading=false;
            var status = result.status;
            if(status.toLowerCase() == "fail".toLowerCase()) {
                //alert(result.message);
                vm.tipPop=true;
                vm.emptyFlag = true;
                return;
            }
            vm.emptyFlag = false;
            vm.pager = result.response;
            vm.tipPop=false;

        }

        /**
         * 获取列表失败
         * @param result
         */
        function getIntellectualsError(result) {
            console.log(result);
            var status = result.status;
            if (status == 404) {
                vm.emptyFlag = true;
                vm.pager = {};
                //console.log("获取知产求购列表失败");
            } else {
                vm.emptyFlag = false;
            }

        }


        function selectPage(select, current, max) {
            if (select != current) {
                if (select > max) {
                    return;
                }
                vm.questionsObj.pageNumber = select;
                BuyIPService.getIntellectuals(vm.questionsObj, function (obj) {
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
            BuyIPService.getIntellectuals(vm.questionsObj, function (obj) {
                vm.pager = obj.response;
                vm.pager.pageNumber = 0;
            })
        }

        function checkLogin() {
            if(vm.booleanLogin){
                $state.go("publishBuyIP");
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
