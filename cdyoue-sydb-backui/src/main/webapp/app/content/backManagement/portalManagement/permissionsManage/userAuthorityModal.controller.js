(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('UserAuthorityModalController', UserAuthorityModalController);
    UserAuthorityModalController.$inject = ['$rootScope', '$uibModalInstance', 'items', 'PortalManageService','toaster','$uibModal'];
    function UserAuthorityModalController($rootScope, $uibModalInstance, items, PortalManageService,toaster,$uibModal) {
        var vm = this;
        vm.addMenu = addMenu; // 添加菜单资源
        vm.removeMenu = removeMenu; // 删除菜单资源
        vm.editor = editor; // 编辑
        vm.getMenu = getMenu; // 获取所有菜单
        vm.save = save; // 保存
        vm.cancel = cancel; // 关闭
        vm.items = {};
        vm.updateItems = {};
        vm.Menus = {};

        // 是编辑还是查看
        if (items.model == "show") {
            vm.isEditor = false;
            init()
        } else {
            getMenu();
            vm.isEditor = true;
            if (items.id) {
                init();
            }
        }

        function init() {
            PortalManageService.getRoleDetails({'id': items.id}, function success(obj) {
                if (obj.status != "SUCCESS") {
                    vm.dataShow = false;
                    if (obj.message) {
                       // alert(obj.message + "(" + obj.description + ")！");
                        var out = $uibModal.open({
                            animation : true,
                            templateUrl : "app/layouts/messageTips/messageTips.html",
                            controller : "messageTipsController",
                            controllerAs:"vm",
                            size : "md",
                            resolve : {
                                items : function() {
                                    return  obj.message + "(" + obj.description + ")！";
                                }
                            }
                        });
                    }
                    return;
                }
                vm.items = obj.response;
                vm.updateItems.name = vm.items.name;
                vm.updateItems.intro = vm.items.intro;

                // 获取已选中的
                var menuList = getSelectedMenu(angular.copy(obj.response.menu));
                // 已选中设置
                for (var y = 0; y < menuList.length; y++) {
                    forMenus(menuList[y].uiSref, menuList[y].id, false);
                }
            }, function error(obj) {

            });
        }

        function getMenu() {
            PortalManageService.getMenus({pageSize: 150, pageNumber: 0}, function success(obj) {
                if (obj.status != "SUCCESS") {
                    vm.dataShow = false;
                    if (obj.message) {
                       // alert(obj.message + "(" + obj.description + ")！");
                        var out = $uibModal.open({
                            animation : true,
                            templateUrl : "app/layouts/messageTips/messageTips.html",
                            controller : "messageTipsController",
                            controllerAs:"vm",
                            size : "md",
                            resolve : {
                                items : function() {
                                    return  obj.message + "(" + obj.description + ")！";
                                }
                            }
                        });
                    }
                    return;
                }
                vm.Menus = obj.response.list;
                setBaseMenu();
            });
        }

        function save() {
            vm.updateItems.menus = getSelectedMenuId();
            if (items.id) {
                PortalManageService.updateRole({'id': items.id}, vm.updateItems, function success(obj) {
                    if (obj.status != "SUCCESS") {
                        vm.dataShow = false;
                        if (obj.message) {
                           // alert(obj.message + "(" + obj.description + ")！");
                            var out = $uibModal.open({
                                animation : true,
                                templateUrl : "app/layouts/messageTips/messageTips.html",
                                controller : "messageTipsController",
                                controllerAs:"vm",
                                size : "md",
                                resolve : {
                                    items : function() {
                                        return  obj.message + "(" + obj.description + ")！";
                                    }
                                }
                            });
                        }
                        return;
                    }
                    toaster.info("修改成功!");
                    cancel();
                });
            } else {
                PortalManageService.addRole(vm.updateItems, function success(obj) {
                    if (obj.status != "SUCCESS") {
                        vm.dataShow = false;
                        if (obj.message) {
                          //  alert(obj.message + "(" + obj.description + ")！");
                            var out = $uibModal.open({
                                animation : true,
                                templateUrl : "app/layouts/messageTips/messageTips.html",
                                controller : "messageTipsController",
                                controllerAs:"vm",
                                size : "md",
                                resolve : {
                                    items : function() {
                                        return  obj.message + "(" + obj.description + ")！";
                                    }
                                }
                            });
                        }
                        return;
                    }
                    toaster.info("添加成功!");
                    cancel();
                });
            }

        }

        function editor() {
            getMenu();
            vm.isEditor = true;
            if (items.id) {
                init();
            }
        }

        // 初始设置菜单添加显示与否字段
        function setBaseMenu() {
            for (var i = 0; i < vm.Menus.length; i++) {
                if (vm.Menus[i].leftMenus) {
                    for (var y = 0; y < vm.Menus[i].leftMenus.length; y++) {
                        if (vm.Menus[i].leftMenus[y].menus) {
                            for (var x = 0; x < vm.Menus[i].leftMenus[y].menus.length; x++) {
                                vm.Menus[i].leftMenus[y].menus[x].isShow = true;
                            }
                            vm.Menus[i].leftMenus[y].isShow = true;
                        } else {
                            vm.Menus[i].leftMenus[y].isShow = true;
                        }
                    }
                }
            }
        }

        // 初始获取选中的菜单
        function getSelectedMenu(menus) {
            var tempMenus = [];
            for (var i = 0; i < menus.length; i++) {
                if (menus[i].leftMenus) {
                    for (var y = 0; y < menus[i].leftMenus.length; y++) {
                        if (menus[i].leftMenus[y].menus) {
                            for (var z = 0; z < menus[i].leftMenus[y].menus.length; z++) {
                                var tempMen = {};
                                tempMen.uiSref = menus[i].leftMenus[y].menus[z].uiSref;
                                tempMen.id = menus[i].leftMenus[y].menus[z].id;
                                tempMenus.push(tempMen);
                            }
                        } else {
                            var tempMenu = {};
                            tempMenu.uiSref = menus[i].leftMenus[y].uiSref;
                            tempMenu.id = menus[i].leftMenus[y].id;
                            tempMenus.push(tempMenu);
                        }
                    }
                }
            }
            return tempMenus;
        }

        // 获取选中的菜单id
        function getSelectedMenuId() {
            var menusId = [];
            for (var i = 0; i < vm.Menus.length; i++) {
                if (vm.Menus[i].leftMenus) {
                    for (var y = 0; y < vm.Menus[i].leftMenus.length; y++) {
                        if (vm.Menus[i].leftMenus[y].menus) {
                            var isTag = false;
                            for (var z = 0; z < vm.Menus[i].leftMenus[y].menus.length; z++) {
                                if (!vm.Menus[i].leftMenus[y].menus[z].isShow) {
                                    menusId.push(vm.Menus[i].leftMenus[y].menus[z].id);
                                    isTag = true;
                                }
                            }
                            if (isTag) {
                                menusId.push(vm.Menus[i].leftMenus[y].id);
                            }
                        } else {
                            if (!vm.Menus[i].leftMenus[y].isShow) {
                                menusId.push(vm.Menus[i].leftMenus[y].id);
                            }
                        }
                    }
                }
            }
            return menusId;
        }

        function addMenu(uiSref, id) {
            forMenus(uiSref, id, false);
        }

        function removeMenu(uiSref, id) {
            forMenus(uiSref, id, true);
        }

        function forMenus(uiSref, id, trueOrFalse) {
            for (var i = 0; i < vm.Menus.length; i++) {
                if (vm.Menus[i].leftMenus) {
                    for (var y = 0; y < vm.Menus[i].leftMenus.length; y++) {
                        if (vm.Menus[i].leftMenus[y].menus) {
                            var isTag = true;
                            for (var x = 0; x < vm.Menus[i].leftMenus[y].menus.length; x++) {
                                if (vm.Menus[i].leftMenus[y].menus[x].uiSref == uiSref && vm.Menus[i].leftMenus[y].menus[x].id == id) {
                                    vm.Menus[i].leftMenus[y].menus[x].isShow = trueOrFalse;
                                }
                                if (!vm.Menus[i].leftMenus[y].menus[x].isShow) {
                                    isTag = false;
                                }
                            }
                            vm.Menus[i].leftMenus[y].isShow = isTag;
                        } else {
                            if (vm.Menus[i].leftMenus[y].uiSref == uiSref && vm.Menus[i].leftMenus[y].id == id) {
                                vm.Menus[i].leftMenus[y].isShow = trueOrFalse;
                            }
                        }
                    }
                }
            }
        }

        function cancel() {
            $uibModalInstance.dismiss('cancel');
        }
    }
})();