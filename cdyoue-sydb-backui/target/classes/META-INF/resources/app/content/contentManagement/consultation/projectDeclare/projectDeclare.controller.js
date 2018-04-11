(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('ProjectDeclareController', ProjectDeclareController);

    ProjectDeclareController.$inject = ['$scope','$uibModal', 'ConsultationService','toaster'];

    function ProjectDeclareController($scope, $uibModal,ConsultationService,toaster) {
        var vm = this;

        vm.data = {};
        vm.ids=[];
        vm.openModal = openModal; //弹窗
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
            sort:"-createTime"
        };
        vm.emptyFlag = false;//列表数据是否为空
        vm.init = init;

        init();//页面初始化

        function init() {
            queryData();
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
        function openModal(id) {
            var out = $uibModal.open({
                animation: true,
                templateUrl: "app/content/contentManagement/consultation/projectDeclare/projectDeclareModal.html",
                controller: "ProjectDeclareModalController",
                controllerAs: "vm",
                size: "lg",
                resolve: {
                    items : {id:id}
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
                    ConsultationService.deleteProject({"id": id}, function success(obj) {
                        if(obj.status == "SUCCESS"){
                            toaster.pop('info', obj.message);
                            init();
                        }
                    },function error (obj) {
                        if(obj.message){
                            toaster.pop('error', obj.message+":"+obj.description);
                        }
                    });
                }
            });
        }

        function dels() {
            if (vm.ids.length<1){
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
                    ConsultationService.deleteAllProjects({ids:vm.ids}, function success(obj) {
                        if(obj.status == "SUCCESS"){
                            toaster.pop('info', obj.message);
                            init();
                        }
                    },function error (obj) {
                        if(obj.message){
                            toaster.pop('error', obj.message+":"+obj.description);
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
            ConsultationService.getProject(vm.questionsObj, getIntellectualsSuccess, getIntellectualsError);
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
                ConsultationService.getProject(vm.questionsObj, function (obj) {
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
            ConsultationService.getProject(vm.questionsObj, function (obj) {
                vm.pager = obj.response;
                vm.pager.pageNumber = 0;
            })
        }
    }
})();