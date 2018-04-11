(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('UserInfoController', UserInfoController);

    UserInfoController.$inject = ['$state', '$uibModal', 'PortalManageService'];

    function UserInfoController($state, $uibModal, PortalManageService) {
     //   alert(22);
        var vm = this;
        vm.dataShow = true;
        vm.selectAll = false;
        vm.sortDown = true; // 显示排序箭头，默认降序
        vm.userType = [{key: '全部用户', value: ''}, { key: '企业用户', value: 1}, {key: '管理员', value: 2}];
        vm.ids = [];
        vm.msg = "没有数据！";
        vm.initList = {
            pageSize: 10,
            pageNumber: 0,
            role: ''
        };

        vm.search = search; // 搜索
        vm.inputSearch = inputSearch; // 改变即搜索
        vm.sortField = sortField; // 点击改变排序
        vm.changeState = changeState;// 选择框改变事件
        // vm.statusAudit = statusAudit;// 设置状态
        vm.resetPassword = resetPassword;// 重置密码
        vm.editor = editor; // 跳转到编辑页面
        vm.userInfoUpdate = userInfoUpdate; // 编辑用户信息
        vm.roleDistributionModal = roleDistributionModal; // 分配角色
        vm.selectPage = selectPage;//分页插件 选择第几页事件

        init();

        //初始化数据
        function init() {
            PortalManageService.getAccounts(vm.initList, function success(obj) {
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

        // function statusAudit(status, id) {
        //     var string = "";
        //     var statusType = "";
        //     if (status == 1) {
        //         string = "禁用";
        //         statusType = 0;
        //     } else if (status == 2) {
        //         string = "启用";
        //         statusType = 1;
        //     }
        //     else {
        //         alert("状态码错误！");
        //         return;
        //     }
        //     if (window.confirm('你确定要' + string + '吗？')) {
        //         PortalManageService.setAccounts({'id': id, type: statusType}, {}, function success(obj) {
        //             if (obj.status != "SUCCESS") {
        //                 vm.dataShow = false;
        //                 if (obj.message) {
        //                     vm.msg = obj.message + "(" + obj.description + ")！";
        //                 }
        //                 return;
        //             }
        //             vm.dataShow = true;
        //             init();
        //         }, function error(obj) {
        //             vm.dataShow = false;
        //             if (obj.message) {
        //                 vm.msg = obj.message + "(" + obj.description + ")！";
        //             }
        //         });
        //     }
        // }

        function resetPassword(id) {
            var out = $uibModal.open({
                animation : true,
                templateUrl : "app/layouts/messageTips/messageTips.html",
                controller : "messageTipsController",
                controllerAs:"vm",
                size : "md",
                resolve : {
                    items : function() {
                        return '你确定要重置密码吗？'
                    }
                }
            });
            out.result.then(function (result) {//result关闭是回传的值
                if(result == 1){
                    PortalManageService.setAccounts({'id': id, type: 2}, {}, function success(obj) {
                        if (obj.status != "SUCCESS") {
                            vm.dataShow = false;
                            if (obj.message) {
                                vm.msg = obj.message + "(" + obj.description + ")！";
                            }
                            return;
                        }
                        vm.dataShow = true;
                      //  alert("密码重置成功，初始密码为“youedata@2015”!");
                        var out = $uibModal.open({
                            animation : true,
                            templateUrl : "app/layouts/messageTips/messageTips.html",
                            controller : "messageTipsController",
                            controllerAs:"vm",
                            size : "md",
                            resolve : {
                                items : function() {
                                    return  "密码重置成功，初始密码为“youedata@2015”!";
                                }
                            }
                        });
                        init();
                    }, function error(obj) {
                        vm.dataShow = false;
                        if (obj.message) {
                            vm.msg = obj.message + "(" + obj.description + ")！";
                        }
                    });
                }
            });
            // if (window.confirm('你确定要重置密码吗？')) {
            //     PortalManageService.setAccounts({'id': id, type: 2}, {}, function success(obj) {
            //         if (obj.status != "SUCCESS") {
            //             vm.dataShow = false;
            //             if (obj.message) {
            //                 vm.msg = obj.message + "(" + obj.description + ")！";
            //             }
            //             return;
            //         }
            //         vm.dataShow = true;
            //         alert("密码重置成功，初始密码为“youedata@2015”!");
            //         init();
            //     }, function error(obj) {
            //         vm.dataShow = false;
            //         if (obj.message) {
            //             vm.msg = obj.message + "(" + obj.description + ")！";
            //         }
            //     });
            // }
        }

        function search() {
            vm.initList.pageNumber = 0;
            init();
        }

        function inputSearch() {
            vm.initList.pageNumber = 0;
            init();
        }

        function editor(id, role) {
            $state.go('authenticationInfo', {id: id, role: role});
        }

        function userInfoUpdate(id, role) {
            $state.go('userInfoUpdate', {id: id, role: role});
        }

        function roleDistributionModal(index) {
            var out = $uibModal.open({
                animation: true,
                templateUrl: "app/content/backManagement/portalManagement/userInfoManage/roleDistributionModal.html",
                controller: "RoleDistributionModalController",
                controllerAs: "vm",
                size: "lg",
                resolve: {
                    items: function () {
                        vm.passData = vm.pager.list[index];
                        return vm.passData;
                    }
                }
            });
            out.result.then(function (result) {

            }, function () {

            });
        }

        function selectPage(select, current, max) {
            vm.selectCheckedAll = false;
            vm.ids = [];
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
