(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('KnowledgeBuyController', KnowledgeBuyController);

    KnowledgeBuyController.$inject = ['$scope','$uibModal', 'InnovateService','toaster'];

    function KnowledgeBuyController($scope, $uibModal,InnovateService,toaster) {
        var vm = this;

        vm.data = {};
        vm.openModal = openModal; //弹窗
        vm.top = top; //置顶操作
        vm.getAcc = getAcc; //审核
        vm.save = save;//审核
        vm.deleteSale = deleteSale;//删除出售
        vm.selectPage = selectPage;//分页插件 选择第几页事件
        vm.selectPageSize = selectPageSize;//分页插件 现在显示多少条事件
        vm.search = search;//条件检索
        vm.access = {
            id:"",
            name:"",
            status:"",
            reason:""
        };
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
                templateUrl: "app/content/contentManagement/innovate/knowledgeBuy/knowledgeBuyModal.html",
                controller: "KnowledgeBuyModalController",
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

        /**
         * 置顶操作
         * @param index
         */
        function top(id,status,topImg) {
            if (status == 0) {
                var out = $uibModal.open({
                    animation : true,
                    templateUrl : "app/content/contentManagement/innovate/knowledgeBuy/topModal.html",
                    controller : "topBuyModalController",
                    controllerAs:"vm",
                    size : "lg",
                    resolve : {
                        items : {id: id,topImg:topImg}
                    }
                });
                out.result.then(function (result) {
                    console.log(result); //result关闭是回传的值
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
                        InnovateService.makeRemoveBuyTop({id:id}, function success(obj) {
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
        /**
         * 审核
         * @param id
         * @param type
         */
        function getAcc(id,name) {
            vm.access.name = name;
            vm.access.id=id;
        }
        function save() {
            switch (vm.access.status){
                case '1':
                    InnovateService.approveBuy({"id": vm.access.id},vm.access,function success(obj) {
                        if (obj.status == 'SUCCESS') {
                            init();
                            toaster.clear();
                            toaster.pop('info', obj.message);
                        } else {
                            toaster.clear();
                            toaster.pop('error', obj.message+":"+obj.description);
                        }
                    });
                    break;
                case '2':
                    InnovateService.rejectBuy({"id": vm.access.id},vm.access,function success(obj) {
                        if (obj.status == 'SUCCESS') {
                            init();
                            toaster.clear();
                            toaster.pop('info', obj.message);
                        } else {
                            toaster.clear();
                            toaster.pop('error', obj.message+":"+obj.description);
                        }
                    });
                    break;
            }
            init();
        }

        /**
         * 删除出售
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
                    InnovateService.deleteBuy({"id": id}, function success(obj) {
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

        /**
         * 异步请求数据
         */
        function queryData() {
            InnovateService.getIntellectualBuys(vm.questionsObj, getIntellectualsSuccess, getIntellectualsError);
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
            }else{
                vm.emptyFlag = false;
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
                InnovateService.getIntellectualBuys(vm.questionsObj, function (obj) {
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
            InnovateService.getIntellectualBuys(vm.questionsObj, function (obj) {
                vm.pager = obj.response;
                vm.pager.pageNumber = 0;
            })
        }
    }
})();