(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('NavbarController', NavbarController);

    NavbarController.$inject = ['$state','toaster','$rootScope','loginService','UserToken','$uibModal'];

    function NavbarController($state,toaster,$rootScope,loginService,UserToken,$uibModal) {
        $("#titleSH").html("装备制造业大数据服务平台");
        var vm = this;
        var navItem = [];
        if($.cookie('syFrontUserTokenKey')){
            $rootScope.SY_IsLogin = true;
        }else {
            $rootScope.SY_IsLogin = false;
        }
        // vm.logState = false;
        vm.activetUrl = $state.current.active;
        vm.show = {value: null};
        vm.navItems = [
            {
                title: "助力创新",
                href: "assistsInnovation"
            },
            {
                title: "助力创业",
                href: "assistsProject"
            }
        ];
        vm.anotherNavItem = [
            {
                title: "首页",
                href: "http://sycms.youedata.com/youedata-ibdp-cms/"
            },
            {
                title: "产业洞察",
                href: "http://sycms.youedata.com/youedata-ibdp-cms/html/1/162/index.html"
            },
            {
                title: "数据服务",
                href: "http://syda.youedata.com/ds/sy-platform/views/goodsList/dataServices.html"
            }
        ];
        vm.selectedNavItem = "";
        vm.navClick = navClick;//激活导航
        vm.logOut = logOut;
        for (var i = 0, len = vm.navItems.length; i < len; i++) {
            navItem.push(vm.navItems[i]);
            defaultSelected();
        }

        function defaultSelected() {
            for (var j = 0; j < navItem.length; j++) {
                if (vm.activetUrl == navItem[j].href) {
                    vm.selectedNavItem = navItem[j].title;
                    break;
                }
            }
        }

        function navClick(navTitie, index) {
            var myIndex = index;
            vm.currentUrl = navTitie;
            vm.show.value = null;
            if (vm.selectedNavItem == navTitie) {
                //vm.selectedNavItem = "";
                vm.selectedNavItem = navTitie;
            }
            else {
                vm.selectedNavItem = navTitie;
                vm.show.value = myIndex;
            }
            for (var k = 0; k < navItem.length; k++) {
                if (navItem[k].title == navTitie && navItem[k].hasOwnProperty("submenu")) {
                    vm.selectedSubmenu = selectedSubmenu;
                    break;
                }
                else {
                    vm.selectedSubmenu = null;
                }
            }
        }

        //登出
        function logOut() {
            loginService.logout(function (rel) {
                $.cookie('syFrontUserTokenKey', null, { path:"/",domain:'.youedata.com',expires:-1});
                delCookie("syFrontUserTokenKey");
                sessionStorage.clear();
                $rootScope.SY_IsLogin = false;
                var out = $uibModal.open({
                    animation : true,
                    templateUrl : "layouts/messageTips/messageTipsModal.html",
                    controller : "messageTipsModalController",
                    controllerAs:"vm",
                    size : "sm",
                    resolve : {
                        items : function() {
                            return '退出成功';
                        }
                    }
                });
            }, function (err) {

            })
        }

        function delCookie(name){
            var exp = new Date();
            exp.setTime(exp.getTime() - 1);
            var cval = $.cookie(name);
            if(cval != null) {
                document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
            }
        }

        vm.goCenter = function () {
            var token = $.cookie('syFrontUserTokenKey');

            loginService.checkLogin({token:token},function (rel) {
                if (rel.code == 20){
                    $state.go("personalCenter");
                }else{
                    toaster.pop('info', "登录失效！");
                    sessionStorage.clear();
                    $rootScope.SY_IsLogin = false;
                    $state.go("login");
                }
            },function (err) {
                toaster.pop('info', "登录失效！");
                sessionStorage.clear();
                $state.go("login");
            })

        }


        // vm.getId = function getId() {
        //     var token = $.cookie('syFrontUserTokenKey');
        //     loginService.getUidByTokenString({token:token},function (rel) {
        //         console.log(rel);
        //     })
        // }
        //
        // vm.getUser = function getUser() {
        //     var token = $.cookie('syFrontUserTokenKey');
        //     loginService.getUserMessageByToken({token:token},function (rel) {
        //         console.log(rel);
        //     })
        // }
        //
        // vm.checkLogin = function checkLogin() {
        //     var token = UserToken.getUserToken();
        //     console.log(token.access_token);
        //     loginService.checkLogin({token:token.access_token},function (rel) {
        //         console.log(rel);
        //     },function (err) {
        //         alert("错误");
        //     })
        // }
    }
})();
