(function () {
    'use strict';
    angular
        .module('oddApp',
            ['ui.router', 'ngResource',
                'ui.bootstrap', 'angular-loading-bar',
                'ngFileUpload',
                'angularFileUpload', 'toaster',
                'ngScrollbar', 'oc.lazyLoad',
                'cdyoue.components',
                'ueditor.directive'
            ])
        .config(['$httpProvider', function ($httpProvider) {
            $httpProvider.defaults.headers.patch = {
                'Content-Type': 'application/json;charset=utf-8'
            }
        }])
        .run(run);

    run.$inject = ['$rootScope', '$http', 'cfpLoadingBar', 'CommonService'];
    function run($rootScope, $http, cfpLoadingBar, CommonService) {
        $rootScope.loginstatus = false;
        // 设置文件上传的基础路径（全局）
        if (!$rootScope.SY_UploadBasePath) {
            CommonService.getFileBasePath({}, function (obj) {
                $rootScope.SY_UploadBasePath = obj.basePath;
            });
        }

        $rootScope.$on('$stateChangeStart', function (event, toState, toParams, fromState, fromParams) {
            cfpLoadingBar.start();
        });

        $rootScope.$on('$stateChangeSuccess', function (event, toState, toParams, fromState, fromParams) {
            var activeMenu = toState.active; // 需要高亮的菜单
            var sessionMenus = $rootScope.headerNavItems; // 全局菜单
            var headerNavItems = angular.copy(JSON.parse(sessionStorage.getItem("oddBackUserTokenKey")).response.userMine.topMenus);

            $rootScope.SY_CrumbsData = {}; // 动态面包屑
            if (sessionMenus && activeMenu) {
                oneLevel();
                if ($rootScope.loginstatus) {
                    sessionStorage.setItem("SY_MenuData", JSON.stringify(sessionMenus)); //防止刷新页面菜单离开当前位置
                    sessionStorage.setItem("SY_CrumbsData", JSON.stringify($rootScope.SY_CrumbsData)); //防止刷新
                    $rootScope.loginstatus = false;
                }
            } else {
                if(!JSON.parse(sessionStorage.getItem("SY_MenuData"))){
                    for (var y = 0; y < headerNavItems.length; y++) {
                        if (headerNavItems[y].uiSref == "personalPanel") {
                            headerNavItems[y].isActive = true;
                            $rootScope.SY_CrumbsData.oneLevel = {name:headerNavItems[y].name};
                        } else {
                            headerNavItems[y].isActive = false;
                        }
                        if (headerNavItems[y].leftMenus) {
                            for (var z = 0; z < headerNavItems[y].leftMenus.length; z++) {
                                headerNavItems[y].leftMenus[z].isActive = false;
                            }
                        }
                    }
                    sessionStorage.setItem("SY_MenuData", JSON.stringify(headerNavItems)); //防止刷新页面菜单离开当前位置
                    sessionStorage.setItem("SY_CrumbsData", JSON.stringify($rootScope.SY_CrumbsData)); //防止刷新
                }else {
                    $rootScope.SY_CrumbsData = JSON.parse(sessionStorage.getItem("SY_CrumbsData")); // 刷新获取浏览器数据赋值
                }
            }

            cfpLoadingBar.complete();

            // 遍历一级
            function oneLevel() {
                for (var a = 0; a < sessionMenus.length; a++) {
                    if (sessionMenus[a].uiSref == activeMenu) {
                        sessionMenus[a].isActive = true;
                        $rootScope.SY_CrumbsData.oneLevel = {name:headerNavItems[a].name};
                        if (sessionMenus[a].leftMenus) {
                            twoLevel(a);
                        }
                    } else {
                        sessionMenus[a].isActive = false;
                        if (sessionMenus[a].leftMenus) {
                            twoLevel(a);
                        }
                    }
                }
            }

            // 遍历二级
            function twoLevel(a) {
                for (var b = 0; b < sessionMenus[a].leftMenus.length; b++) {
                    if (sessionMenus[a].leftMenus[b].uiSref == activeMenu) {
                        sessionMenus[a].isActive = true;
                        sessionMenus[a].leftMenus[b].isActive = true;
                        $rootScope.SY_CrumbsData.oneLevel = {uiSref:headerNavItems[a].uiSref,name:headerNavItems[a].name};
                        $rootScope.SY_CrumbsData.twoLevel = {name:headerNavItems[a].leftMenus[b].name};
                        if (sessionMenus[a].leftMenus[b].menus) {
                            threeLevel(a, b);
                        }
                    } else {
                        sessionMenus[a].leftMenus[b].isActive = false;
                        if (sessionMenus[a].leftMenus[b].menus) {
                            threeLevel(a, b);
                        }
                    }
                }
            }

            // 遍历三级
            function threeLevel(a, b) {
                for (var c = 0; c < sessionMenus[a].leftMenus[b].menus.length; c++) {
                    if (sessionMenus[a].leftMenus[b].menus[c].uiSref == activeMenu) {
                        sessionMenus[a].isActive = true;
                        sessionMenus[a].leftMenus[b].isActive = true;
                        sessionMenus[a].leftMenus[b].menus[c].isActive = true;
                        $rootScope.SY_CrumbsData.oneLevel = {uiSref:headerNavItems[a].uiSref,name:headerNavItems[a].name};
                        $rootScope.SY_CrumbsData.twoLevel = {uiSref:headerNavItems[a].leftMenus[b].uiSref,name:headerNavItems[a].leftMenus[b].name};
                        $rootScope.SY_CrumbsData.threeLevel = {name:headerNavItems[a].leftMenus[b].menus[c].name};
                    } else {
                        sessionMenus[a].leftMenus[b].menus[c].isActive = false;
                    }
                }
            }

        });
    }
})();