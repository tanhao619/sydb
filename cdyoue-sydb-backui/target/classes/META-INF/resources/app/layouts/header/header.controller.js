(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('HeaderController', HeaderController);

    HeaderController.$inject = ['$rootScope', '$scope', '$state', 'LogInService','$uibModal'];

    function HeaderController($rootScope, $scope, $state, LogInService,$uibModal) {
        var vm = this;
        // vm.logoutModal = logoutModal;//退出弹框
        // 每隔1秒更新时间(顶部时间)
        vm.date = new Date();
        setInterval(function () {
            $scope.$apply(function () {
                vm.date = new Date();
            });
        }, 1000);

        if (sessionStorage.getItem("oddBackUserKey")) {
            vm.myInfo = JSON.parse(sessionStorage.getItem("oddBackUserKey"));
        } else {
            init();
        }

        vm.init = init; // 初始化登录用户信息
        vm.logout = logout; // 退出系统

        var headerNavItem = [];
        vm.selectedHeaderNavItem = "";
        vm.name = $state.$current.locals.resolve.$$values.name;
        vm.logo = "/content/images/syLogo.png";

        vm.headerNavItems = {};
        if (!$rootScope.headerNavItems) {
            if (!JSON.parse(sessionStorage.getItem("SY_MenuData"))) {
                vm.headerNavItems = JSON.parse(sessionStorage.getItem("oddBackUserTokenKey")).response.userMine.topMenus;
            } else {
                vm.headerNavItems = angular.copy(JSON.parse(sessionStorage.getItem("SY_MenuData")));
            }
        } else {
            vm.headerNavItems = $rootScope.headerNavItems;
        }

        $rootScope.headerNavItems = vm.headerNavItems; // 放入全局

        for (var i = 0, len = vm.headerNavItems.length; i < len; i++) {
            headerNavItem.push(vm.headerNavItems[i]);
        }

        //初始化数据
        function init() {
            LogInService.getMyInfo(function success(obj) {
                if (obj.status != "SUCCESS") {
                    return;
                }
                vm.myInfo = obj.response;
            });
        }

        function logout() {
            // if (window.confirm('你确定要退出系统吗？')) {
            var out = $uibModal.open({
                animation: true,
                templateUrl: "app/layouts/messageTips/messageTips.html",
                controller: "messageTipsController",
                controllerAs: "vm",
                size: "md",
                resolve: {
                    items: function () {
                        return '你确定要退出系统吗'
                    }
                }
            });
            out.result.then(function (result) {//result关闭是回传的值
                if (result == 1) {
                    LogInService.logout(function () {
                        $rootScope.loginstatus = false;
                        $rootScope.SY_CrumbsData = null;
                        $rootScope.headerNavItems = null;
                        sessionStorage.removeItem('oddBackUserKey');
                        sessionStorage.removeItem('oddBackUserTokenKey');
                        sessionStorage.removeItem('SY_MenuData');
                        sessionStorage.removeItem('SY_CrumbsData');
                        $state.go('logIn');
                    });
                }
            });

            // }


        }
    }
})();
