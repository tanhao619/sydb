(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('TechnologyFinancingController', TechnologyFinancingController);

    TechnologyFinancingController.$inject = ['$scope','$uibModal', 'InnovateService','toaster'];

    function TechnologyFinancingController($scope, $uibModal,InnovateService,toaster) {
        var vm = this;
        vm.query={//分页信息
            pageSize:10,
            pageNumber:0,
            sort:"-createTime|-top",
            q:""
        }
        vm.boolean = false;
        vm.page = {};
        vm.financingList = {};
        vm.statusSelect = "";

        vm.selectPage = selectPage;
        vm.getFinancingBackPageList = getFinancingBackPageList;
        vm.checkedOne = checkedOne;//单选
        vm.checkedAll = checkedAll;//全选
        vm.deleteAll = deleteAll
        vm.ids=[];//批量数组
        //获取分页列表
        getFinancingBackPageList();
        function getFinancingBackPageList() {
            InnovateService.getFinancingBackPageList(vm.query,function (rel) {
                if(rel.code == 20){
                    vm.financingList = rel.response.list;
                    vm.page = rel.response;
                    vm.boolean = true;
                }else if(rel.code == 40){
                    vm.boolean = false;
                }


            },function (err) {

            })
        }

        //分页插件
        function selectPage(select, current, max) {
            if (select != current) {
                if (select > max) {
                    return;
                }
                vm.query.pageNumber = select;
                vm.getFinancingBackPageList();
            }
        }

        //关键字查询
        vm.findTitle = function findTitle() {
            vm.query.q = vm.q;
            vm.query.sort = "id";
            vm.query.status = vm.statusSelect;
            vm.query.pageNumber = 0;
            InnovateService.getFinancingBackPageList(vm.query,function (rel) {
                if(rel.code == 20){
                    vm.financingList = rel.response.list;
                    vm.page = rel.response;
                    vm.boolean = true;
                }else if(rel.code == 40){
                    vm.boolean = false;
                }
            },function (err) {
            })
        }

        //单选
        function checkedOne(id, _index, checked) {
            if(checked){
                vm.ids.push(id);//将id添加至数组
            }else{
                for (var i = 0; i < vm.ids.length; i++) {
                    if(vm.ids[i] == id){//将ID从数组清除
                        vm.ids.splice(i,1);
                    }
                }
            }
            if(vm.ids.length == vm.query.pageSize){
                vm.selectCheckedAll = true;
            }else{
                vm.selectCheckedAll = false;
            }
        }

        //全选
        function checkedAll(selectCheckedAll) {
            if(selectCheckedAll){
                vm.ids.splice(0,vm.financingList.length);
                for(var i = 0; i < vm.financingList.length; i++){
                    vm.financingList[i].checked = true;
                    vm.ids.push(vm.financingList[i].id);
                }

            }else{
                for(var i = 0; i < vm.financingList.length; i++){
                    vm.financingList[i].checked = false;
                }
                vm.ids.splice(0,vm.financingList.length);
            }
        }

        //删除融资
        vm.delete = function deleteFinancing(id) {
            var out = $uibModal.open({
                animation : true,
                templateUrl : "app/layouts/messageTips/messageTips.html",
                controller : "messageTipsController",
                controllerAs:"vm",
                size : "md",
                resolve : {
                    items : function() {
                        return '确定删除此条数据吗'
                    }
                }
            });
            out.result.then(function (result) {//result关闭是回传的值
                if(result == 1){
                    InnovateService.deleteFinancing({id:id},function (rel) {
                        getFinancingBackPageList();
                        toaster.pop('info', rel .message);
                    },function (err) {
                        toaster.pop('info', err .message);
                    })
                }
            }, function (reason) {
            });
        }

        //批量删除
        function deleteAll() {
            var out = $uibModal.open({
                animation : true,
                templateUrl : "app/layouts/messageTips/messageTips.html",
                controller : "messageTipsController",
                controllerAs:"vm",
                size : "md",
                resolve : {
                    items : function() {
                        return '确定删除此条数据吗'
                    }
                }
            });
            out.result.then(function (result) {//result关闭是回传的值
                if(result == 1){
                    InnovateService.deleteFinancing({id:vm.ids},function (rel) {
                        getFinancingBackPageList();
                        toaster.pop('info', rel .message);
                        vm.ids=[];
                    },function (err) {

                    })
                }
            }, function (reason) {
            });
        }

        // 查看融资详情
        vm.openModal = function openModal(id) {
            var out = $uibModal.open({
                animation : true,
                templateUrl : "app/content/contentManagement/innovate/technologyFinancing/technologyFinancingModal.html",
                controller : "TechnologyFinancingModalController",
                controllerAs:"vm",
                size : "lg",
                resolve : {
                    items : function() {
                        return id;
                    }
                }
            });
            out.result.then(function (result) {
                getFinancingBackPageList();
            }, function () {
            });
        }

        //融资审核
        vm.openModal1 = function openModal(id) {
            var out = $uibModal.open({
                animation : true,
                templateUrl : "app/content/contentManagement/innovate/technologyFinancing/technologyFinancingShModal.html",
                controller : "technologyFinancingShModalController",
                controllerAs:"vm",
                size : "lg",
                resolve : {
                    items : function() {
                        return id;
                    }
                }
            });
            out.result.then(function (result) {
                getFinancingBackPageList();
            }, function () {
            });
        }

        //置顶or取消置顶
        vm.openTopModal = function openModal(id,type) {
            if(type == 0){
                var out = $uibModal.open({
                    animation : true,
                    templateUrl : "app/content/contentManagement/innovate/technologyFinancing/technologyFinancingTopModal.html",
                    controller : "technologyFinancingTopModalController",
                    controllerAs:"vm",
                    size : "lg",
                    resolve : {
                        items : function() {
                            return id;
                        }
                    }
                });
                out.result.then(function (result) {
                    if(result){
                        getFinancingBackPageList();
                    }
                }, function () {
                });
            }else if(type == 1){
                var out = $uibModal.open({
                    animation : true,
                    templateUrl : "app/layouts/messageTips/messageTips.html",
                    controller : "messageTipsController",
                    controllerAs:"vm",
                    size : "md",
                    resolve : {
                        items : function() {
                            return '确定取消顶置？'
                        }
                    }
                });
                out.result.then(function (result) {//result关闭是回传的值
                    if(result == 1){
                        InnovateService.cancelFinancingTop({id:id},function (rel) {
                            toaster.pop('info', rel .message);
                            getFinancingBackPageList();
                        },function (err) {
                            toaster.pop('info', err .message);
                        })
                    }
                }, function (reason) {
                });
            }

        }
    }
})();