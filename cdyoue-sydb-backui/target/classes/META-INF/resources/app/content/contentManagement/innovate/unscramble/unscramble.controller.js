(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('UnscrambleController', UnscrambleController);

    UnscrambleController.$inject = ['toaster', '$uibModal', 'InnovateService'];

    function UnscrambleController(toaster, $uibModal, InnovateService) {
        var vm = this;

        vm.dataShow = true;
        vm.selectAll = false;
        vm.msg = "抱歉！暂无数据！";
        vm.queryModel = {
            pageNumber: 1,
            pageSize: 10,
            q: null
        };
        vm.q = '';
        vm.ids = [];

        vm.selectPage = selectPage; //设置分页
        vm.selectPageSize = selectPageSize; //设置分页
        vm.query = query; // 关键字搜索
        vm.openModal = openModal; // 打开弹窗
        vm.del = del; // 删除
        vm.dels = dels; // 批量删除
        vm.top = top; // 置顶
        vm.checkedOne = checkedOne;// 单选
        vm.checkedAll = checkedAll; // 全选

        init();
        //初始化数据
        function init() {
            InnovateService.getUnscrambles(vm.queryModel, function success(obj) {
                if(obj.status !== "SUCCESS"){
                    vm.dataShow = false;
                    if(obj.code === 40){
                        vm.msg = "抱歉！暂无数据！";
                    }else {
                        vm.msg = obj.message +"("+ obj.description+")！";
                    }
                    return;
                }
                vm.dataShow = true;
                vm.syBanner = obj.response;
            },function error (obj) {
                vm.dataShow = false;
                if(obj.message){
                    vm.msg = obj.message +"("+ obj.description+")！";
                }
            });
        }

        function query() {
            vm.queryModel.q = vm.q;
            vm.queryModel.pageNumber = 1;
            init();
        }

        function selectPage(select, current, max) {
            if (select !== current) {
                if (select > max) {
                    return;
                }
                vm.q = vm.queryModel.q;
                vm.queryModel.pageNumber = select + 1;
                InnovateService.getUnscrambles(vm.queryModel, function (rlt) {
                    vm.syBanner = rlt.response;
                    vm.syBanner.pageNum = select;
                })
            }
        }

        function selectPageSize(pageSize) {
            vm.q = vm.queryModel.q;
            vm.queryModel.pageNumber = 1;
            vm.queryModel.pageSize = pageSize;
            InnovateService.getUnscrambles(vm.queryModel,function(data){
                vm.syBanner = data.response;
            })
        }

        function openModal (index,model) {
            vm.passData={};
            var out = $uibModal.open({
                animation : true,
                templateUrl : "app/content/contentManagement/innovate/unscramble/unscrambleModal.html",
                controller : "UnscrambleModalController",
                controllerAs:"vm",
                size : "lg",
                resolve : {
                    items : function() {
                        if (index != null){
                            vm.passData.id=vm.syBanner.list[index].id;
                        } else {
                            vm.passData.id=null;
                        }
                        vm.passData.showModel = model;
                        return vm.passData;
                    }
                }
            });
            out.result.then(function (result) {
                //console.log(result); //result关闭是回传的值
            }, function (reason) {
                init();
            });
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
            if (vm.fLength == vm.syBanner.list.length) {
                vm.selectCheckedAll = true;
            }
            else {
                vm.selectCheckedAll = false;
            }
        }

        function checkedAll(m) {
            vm.fLength = 0;
            for (var i = 0, len = vm.syBanner.list.length; i < len; i++) {
                //根据全选状态进行赋值
                if (m) {
                    vm.ids.push(vm.syBanner.list[i].id);
                    var BL = vm.syBanner.list[i].checked;
                    if (BL == false) {
                        vm.fLength += 1
                    }
                    vm.syBanner.list[i].checked = true;
                }
                else {
                    for (var y = 0; y < vm.ids.length; y++) {
                        if(vm.ids[y] == vm.syBanner.list[i].id){
                            vm.ids.splice(y,1);
                        }
                    }
                    vm.syBanner.list[i].checked = false;
                }
            }
        }

        function del(index) {
            vm.ids.push(vm.syBanner.list[index].id);
            var out = $uibModal.open({
                animation : true,
                templateUrl : "app/layouts/messageTips/messageTips.html",
                controller : "messageTipsController",
                controllerAs:"vm",
                size : "md",
                resolve : {
                    items : function() {
                        return '确定删除此条数据吗？'
                    }
                }
            });
            out.result.then(function (result) {//result关闭是回传的值
                if(result == 1){
                    InnovateService.delUnscramble({ids:vm.ids}, function success(obj) {
                        if (obj.status == 'SUCCESS') {
                            toaster.clear();
                            toaster.pop('success', obj.message);
                            init();
                        } else {
                            toaster.clear();
                            toaster.pop('error', obj.message+":"+obj.description);
                        }
                    });
                }
                vm.ids=[];
            }, function (reason) {
                vm.ids=[];
            });
        }

        function dels() {
            if (vm.ids.length<1){
                toaster.clear();
                toaster.pop('warning', '至少选中一条记录！');
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
                        return '确定删除这些数据吗？'
                    }
                }
            });
            out.result.then(function (result) {//result关闭是回传的值
                if(result == 1){
                    InnovateService.delUnscramble({ids:vm.ids}, function success(obj) {
                        if (obj.status == 'SUCCESS') {
                            vm.ids=[];
                            toaster.clear();
                            toaster.pop('success', obj.message);
                            init();
                        } else {
                            toaster.clear();
                            toaster.pop('error', obj.message+":"+obj.description);
                        }
                    });
                }
            }, function (reason) {

            });
        }

        function top(index) {
            var top = vm.syBanner.list[index].top;
            vm.topData={};
            if (top == 0) {
                var out = $uibModal.open({
                    animation : true,
                    templateUrl : "app/content/contentManagement/innovate/unscramble/topModal.html",
                    controller : "topUnscrambleModalController",
                    controllerAs:"vm",
                    size : "lg",
                    resolve : {
                        items : function() {
                            vm.topData.id = vm.syBanner.list[index].id;
                            vm.topData.topImgUrl = vm.syBanner.list[index].topImgUrl;
                            return vm.topData;
                        }
                    }
                });
                out.result.then(function (result) {
                    //console.log(result); //result关闭是回传的值
                }, function (reason) {
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
                        InnovateService.topUnscramble({id:vm.syBanner.list[index].id, topImgUrl:''}, function success(obj) {
                            if (obj.status == 'SUCCESS') {
                                init();
                                toaster.clear();
                                toaster.pop('success', obj.message);
                            } else {
                                toaster.clear();
                                toaster.pop('error', obj.message+":"+obj.description);
                            }
                        });
                    }
                }, function (reason) {

                });
            }
        }

    }
})();