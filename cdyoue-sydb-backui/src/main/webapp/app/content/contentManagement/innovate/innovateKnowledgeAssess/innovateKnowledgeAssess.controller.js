(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('InnovateKnowledgeAssessController', InnovateKnowledgeAssessController);

    InnovateKnowledgeAssessController.$inject = ['$scope','$uibModal', 'InnovateService','toaster'];

    function InnovateKnowledgeAssessController($scope, $uibModal,InnovateService,toaster) {
        var vm = this;

        vm.data = {};
        vm.ids=[];
        vm.openModal = openModal; //弹窗
        vm.openNewModal = openNewModal;
        vm.openUpdateModal = openUpdateModal;
        vm.deleteSale = deleteSale;//删除
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
            sort:"-publishTime"
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
                templateUrl: "app/content/contentManagement/innovate/innovateKnowledgeAssess/innovateKnowledgeAssessDetail.html",
                controller: "innovateKnowledgeAssessUpdateController",
                controllerAs: "vm",
                size: "lg",
                resolve: {
                    items: {id: id}
                }
            });
            out.result.then(function (result) {

            }, function () {
                init();
            });
        }
        function openNewModal() {
            var out = $uibModal.open({
                animation: true,
                templateUrl: "app/content/contentManagement/innovate/innovateKnowledgeAssess/innovateKnowledgeAssessSave.html",
                controller: "innovateKnowledgeAssessController",
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
                templateUrl: "app/content/contentManagement/innovate/innovateKnowledgeAssess/innovateKnowledgeAssessUpdate.html",
                controller: "innovateKnowledgeAssessUpdateController",
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
        function deleteSale(id,type) {
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
                    InnovateService.deletePartner({"id": id}, function success(obj) {
                        if(obj.status == "SUCCESS"){
                            alert(obj.message);
                            init();
                        }
                    },function error (obj) {
                        if(obj.message){
                            vm.msg = obj.message +"("+ obj.description+")！";
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
            if(confirm("确认删除吗?")){
                InnovateService.deleteAllPartners({ids:vm.ids}, function success(obj) {
                    if(obj.status == "SUCCESS"){
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
        }
        /**
         * 异步请求数据
         */
        function queryData() {
            InnovateService.getPagePartners(vm.questionsObj, getIntellectualsSuccess, getIntellectualsError);
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
                //console.log("获取知产求购列表失败");
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
                InnovateService.getPagePartners(vm.questionsObj, function (obj) {
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
            InnovateService.getPagePartners(vm.questionsObj, function (obj) {
                vm.pager = obj.response;
                vm.pager.pageNumber = 0;
            })
        }
    }
})();