(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('RoadshowController', RoadshowController);

    RoadshowController.$inject = ['$scope','$uibModal', 'BusinessService','toaster'];

    function RoadshowController($scope, $uibModal,BusinessService,toaster) {
        var vm = this;

        vm.data = {};
        vm.ids=[];
        vm.top = top; //置顶操作
        vm.openModal = openModal; //弹窗
        vm.openSaveModal = openSaveModal;
        vm.openUpdateModal = openUpdateModal;
        vm.delActivity = delActivity;//删除
        vm.dels = dels;//删除
        vm.selectPage = selectPage;//分页插件 选择第几页事件
        vm.selectPageSize = selectPageSize;//分页插件 现在显示多少条事件
        vm.search = search;//条件检索
        vm.selectAll = false;
        vm.checkedOne = checkedOne;
        vm.checkedAll = checkedAll;
        vm.pageSize = 10;
        vm.pageNumber = 0;
        vm.questionsObj = {
            q:"",
            sort:"-top|-updateTime"
        };
        vm.emptyFlag = false;//列表数据是否为空
        vm.init = init;

        init();//页面初始化

        function init() {
            queryData();
        }

        /**
         * 置顶操作
         * @param index
         */
        function top(id,status,topImg) {
            if (status == 0) {
                var out = $uibModal.open({
                    animation : true,
                    templateUrl : "app/content/contentManagement/business/roadshow/topModal.html",
                    controller : "topRoadshowModalController",
                    controllerAs:"vm",
                    size : "lg",
                    resolve : {
                        items : {id: id,topImg:topImg}
                    }
                });
                out.result.then(function (result) {
                    // console.log(result); //result关闭是回传的值
                }, function () {
                    init();
                });
            } else {
                var out = $uibModal.open({
                    animation : true,
                    templateUrl : "app/layouts/messageTips/messageTips.html",
                    controller : "messageTipsController",
                    controllerAs:"vm",
                    size : "md",
                    resolve : {
                        items : function() {
                            return '确认取消置顶吗？'
                        }
                    }
                });
                out.result.then(function (result) {//result关闭是回传的值
                    if(result == 1){
                        BusinessService.removeActivityTop({id:id}, function success(obj) {
                            if (obj.status == 'SUCCESS') {
                                init();
                                toaster.clear();
                                toaster.pop('info', obj.message);
                            } else {
                                toaster.clear();
                                toaster.pop('error', obj.message+":"+obj.description);
                            }
                        });
                    }
                });
            }
        }

        function checkedOne(id, _index, checkOne) {
            if(checkOne){
                vm.ids.push(id)
            }else{
                for (var i = 0; i < vm.ids.length; i++) {
                    if(vm.ids[i] == id){
                        vm.ids.splice(i,1);
                    }
                }
            }
            var length = vm.fLength;
            if (checkOne == false) {
                if (length > 0) {
                    vm.fLength = length - 1
                }
                vm.selectCheckedAll = false;
            }
            else {
                vm.fLength = length + 1;
            }
            if (vm.fLength == vm.pager.list.length) {
                vm.selectCheckedAll = true;
            }
            else {
                vm.selectCheckedAll = false;
            }
        }
        function checkedAll(m) {
            vm.fLength = 0;
            for (var i = 0, len = vm.pager.list.length; i < len; i++) {
                //根据全选状态进行赋值
                if (m) {
                    vm.ids.push(vm.pager.list[i].id);
                    var BL = vm.pager.list[i].checked;
                    if (BL == false) {
                        vm.fLength += 1
                    }
                    vm.pager.list[i].checked = true;
                }
                else {
                    for (var y = 0; y < vm.ids.length; y++) {
                        if(vm.ids[y] == vm.pager.list[i].id){
                            vm.ids.splice(y,1);
                        }
                    }
                    vm.pager.list[i].checked = false;
                }
            }
        }


        function search() {
            vm.questionsObj.pageNumber = 0;
            queryData();
        }

        /**
         * 弹窗
         * @param id
         * @param model
         */
        function openModal(index) {
            var out = $uibModal.open({
                animation: true,
                templateUrl: "app/content/contentManagement/business/roadshow/roadshowModal.html",
                controller: "RoadshowModalController",
                controllerAs: "vm",
                size: "lg",
                resolve: {
                    items : function() {
                        vm.passData = vm.pager.list[index];
                        return vm.passData;
                    }
                }
            });
            out.result.then(function (result) {

            }, function () {
                init();
            });
        }
        function openSaveModal() {
            var out = $uibModal.open({
                animation: true,
                templateUrl: "app/content/contentManagement/business/roadshow/roadShowSave.html",
                controller: "RoadshowSaveController",
                controllerAs: "vm",
                size: "lg",
                resolve: {
                    items: {}
                }
            });
            out.result.then(function (result) {
            }, function () {
                // init();
            });
        }
        function openUpdateModal(id) {
            var out = $uibModal.open({
                animation: true,
                templateUrl: "app/content/contentManagement/business/roadshow/roadShowSave.html",
                controller: "RoadshowSaveController",
                controllerAs: "vm",
                size: "lg",
                resolve: {
                    items: {id:id}
                }
            });
            out.result.then(function (result) {
            }, function () {
                init();
            });
        }

        /**
         * 删除
         */
        function delActivity(id) {
            var out = $uibModal.open({
                animation : true,
                templateUrl : "app/layouts/messageTips/messageTips.html",
                controller : "messageTipsController",
                controllerAs:"vm",
                size : "md",
                resolve : {
                    items : function() {
                        return '确认删除吗？'
                    }
                }
            });
            out.result.then(function (result) {//result关闭是回传的值
                if(result == 1){
                    BusinessService.deleteActivity({"id": id}, function success(obj) {
                        if (obj.status == 'SUCCESS') {
                            init();
                            toaster.clear();
                            toaster.pop('info', obj.message);
                        }
                    },function error (obj) {
                        if(obj.message){
                            toaster.clear();
                            toaster.pop('error', obj.message+":"+obj.description);
                        }
                    });
                }
            });
        }

        function dels() {
            if (vm.ids.length<1){
                toaster.clear();
                toaster.pop('info', "至少选中一条记录！！！");
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
                        return '确认删除吗？'
                    }
                }
            });
            out.result.then(function (result) {//result关闭是回传的值
                if(result == 1){
                    BusinessService.deleteAllActivities({ids:vm.ids}, function success(obj) {
                        if(obj.status == "SUCCESS"){
                            toaster.clear();
                            toaster.pop('info', obj.message);
                            init();
                        }
                    },function error (obj) {
                        if(obj.message){
                            vm.msg = obj.message +"("+ obj.description+")！";
                        }
                    });
                    vm.ids=[];
                }
            });
        }
        /**
         * 异步请求数据
         */
        function queryData() {
            BusinessService.getActivities(vm.questionsObj, getIntellectualsSuccess, getIntellectualsError);
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
                BusinessService.getActivities(vm.questionsObj, function (obj) {
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
            BusinessService.getActivities(vm.questionsObj, function (obj) {
                vm.pager = obj.response;
                vm.pager.pageNumber = 0;
            })
        }
    }
})();