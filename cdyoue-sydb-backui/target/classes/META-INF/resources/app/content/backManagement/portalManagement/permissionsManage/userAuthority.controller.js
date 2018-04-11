(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('UserAuthorityController', UserAuthorityController);

    UserAuthorityController.$inject = ['$uibModal', 'PortalManageService','toaster'];

    function UserAuthorityController($uibModal, PortalManageService,toaster) {
        var vm = this;
        vm.dataShow = true;
        vm.selectAll = false;
        vm.sortDown = true; // 显示排序箭头，默认降序
        vm.msg = "没有数据！";
        vm.initList = {
            pageSize: 10,
            pageNumber: 0
        };

        vm.search = search; // 搜索
        vm.inputSearch = inputSearch; // 改变即搜索
        vm.sortField = sortField; // 点击改变排序
        vm.changeState = changeState;// 选择框改变事件
        vm.deleteData = deleteData; // 删除数据
        vm.openModal = openModal; // 修改新增弹窗
        vm.selectPage = selectPage;//分页插件 选择第几页事件

        init();

        //初始化数据
        function init() {
            PortalManageService.getRoles(vm.initList, function success(obj) {
                if (obj.status != "SUCCESS") {
                    vm.dataShow = false;
                    if (obj.message) {
                        vm.msg = obj.message + "(" + obj.description + ")！";
                    }
                    return;
                }
                vm.dataShow = true;
                vm.pager = obj.response;
            }, function error(obj) {
                vm.dataShow = false;
                if (obj.message) {
                    vm.msg = obj.message + "(" + obj.description + ")！";
                }
            });
        }

        function sortField() {
            if (vm.initList.sort == "createTime") {
                vm.initList.sort = "-createTime";
                vm.sortDown = true;
            } else {
                vm.initList.sort = "createTime";
                vm.sortDown = false;
            }
            init();
        }

        function changeState() {
            vm.initList.pageNumber = 0;
            init();
        }
        function search() {
            vm.initList.pageNumber = 0;
            init();
        }

        function inputSearch() {
            vm.initList.pageNumber = 0;
            init();
        }

        function deleteData(id) {
            if (id == 1) {
               // alert("超级管理员不能被删除！");
                var out = $uibModal.open({
                    animation : true,
                    templateUrl : "app/layouts/messageTips/messageTips.html",
                    controller : "messageTipsController",
                    controllerAs:"vm",
                    size : "md",
                    resolve : {
                        items : function() {
                            return  "超级管理员不能被删除！";
                        }
                    }
                });
                return;
            }
            var out = $uibModal.open({
                animation : true,
                templateUrl : "app/layouts/messageTips/messageTips.html",
                controller : "messageTipsController",
                controllerAs:"vm",
                size : "md",
                resolve : {
                    items : function() {
                        return  "您确定要删除吗？！";
                    }
                }
            });
            out.result.then(function (result) {//result关闭是回传的值
                if(result == 1){
                    PortalManageService.deleteRole({id: id}, function success(obj) {
                        if (obj.status != "SUCCESS") {
                            vm.dataShow = false;
                            if (obj.message) {
                                toaster.info(obj.message + "(" + obj.description + ")！");
                            }
                            return;
                        }
                        toaster.info("删除成功!");
                        search();
                    });
                }
            });
            // if (window.confirm("您确定要删除吗？")) {
            //     PortalManageService.deleteRole({id: id}, function success(obj) {
            //         if (obj.status != "SUCCESS") {
            //             vm.dataShow = false;
            //             if (obj.message) {
            //                 alert(obj.message + "(" + obj.description + ")！");
            //             }
            //             return;
            //         }
            //         alert("删除成功!");
            //         search();
            //     });
            // }
        }

        function openModal(id, model) {
            var out = $uibModal.open({
                animation: true,
                templateUrl: "app/content/backManagement/portalManagement/permissionsManage/userAuthorityModal.html",
                controller: "UserAuthorityModalController",
                controllerAs: "vm",
                size: "lg",
                resolve: {
                    items: {id: id, model: model}
                }
            });
            out.result.then(function (result) {

            }, function () {
                init();
            });
        }

        function selectPage(select, current, max) {
            if (select != current) {
                if (select > max) {
                    return;
                }
                vm.initList.pageNumber = select;
                init();
                vm.pager.pageNum = select;
            }
        }
    }
})();