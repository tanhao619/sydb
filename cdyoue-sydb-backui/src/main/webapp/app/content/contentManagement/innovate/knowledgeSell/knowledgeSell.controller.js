(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('KnowledgeSellController', KnowledgeSellController);

    KnowledgeSellController.$inject = ['$scope','$uibModal', 'InnovateService','toaster'];

    function KnowledgeSellController($scope,$uibModal,InnovateService,toaster) {
        var vm = this;
        vm.data = {};
        vm.top = top; //置顶操作
        vm.openModal = openModal; //弹窗
        vm.getAcc = getAcc; //审核
        vm.save = save;//审核
        vm.deleteSale = deleteSale;//删除出售
        vm.selectPage = selectPage;//分页插件 选择第几页事件
        vm.selectPageSize = selectPageSize;//分页插件 现在显示多少条事件
        vm.search = search;//条件检索
        vm.access = {
            id:"",
            type:"",
            name:"",
            status:"",
            reviewReason:""
        };
        vm.pageSize = 10;
        vm.pageNumber = 0;
        vm.questionsObj = {
            q:"",
            sort:"-top|-publishTime"
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
         * 置顶操作
         * @param index
         */
        function top(id,type,status,topImg) {
            if (status == 0) {
                var out = $uibModal.open({
                    animation : true,
                    templateUrl : "app/content/contentManagement/innovate/knowledgeSell/topModal.html",
                    controller : "topSaleModalController",
                    controllerAs:"vm",
                    size : "lg",
                    resolve : {
                        items : {id: id,topImg:topImg,type:type}
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
                            InnovateService.removeSaleTop({type:type,id:id}, function success(obj) {
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
         * 弹窗
         * @param id
         * @param model
         */
        function openModal(id,type) {
            var out = $uibModal.open({
                animation: true,
                templateUrl: "app/content/contentManagement/innovate/knowledgeSell/knowledgeSellModal.html",
                controller: "KnowledgeSellModalController",
                controllerAs: "vm",
                size: "lg",
                resolve: {
                    items: {type:type,id: id}
                }
            });
            out.result.then(function (result) {

            }, function () {
                init();
            });
        }

        /**
         * 审核
         * @param id
         * @param type
         */
        function getAcc(id,type,name) {
            vm.access.name = name;
            vm.access.id=id;
            vm.access.type=type;
        }
        function save() {
            switch (vm.access.status){
                case '1':
                    InnovateService.approveSale({"id": vm.access.id,"type":vm.access.type},vm.access,success);
                    break;
                case '2':
                    InnovateService.rejectSale({"id": vm.access.id,"type":vm.access.type},vm.access,success);
                    break;
            }
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
                    switch (type){
                        case 1:
                            InnovateService.deleteBrand({"id": id},success);
                            break;
                        case 2:
                            InnovateService.deletePatent({"id": id},success);
                            break;
                        case 3:
                            InnovateService.deleteCopyright({"id": id},success);
                            break;
                    }
                }
            });
        }

        function success(obj) {
            if (obj.status == 'SUCCESS') {
                init();
                toaster.clear();
                toaster.pop('info', obj.message);
            } else {
                toaster.clear();
                toaster.pop('error', obj.message+":"+obj.description);
            }
        };

        /**
         * 异步请求数据
         */
        function queryData() {
            InnovateService.getIntellectualSales(vm.questionsObj, getIntellectualsSuccess, getIntellectualsError);
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
                InnovateService.getIntellectualSales(vm.questionsObj, function (obj) {
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
            InnovateService.getIntellectualSales(vm.questionsObj, function (obj) {
                vm.pager = obj.response;
                vm.pager.pageNumber = 0;
            })
        }
    }
})();