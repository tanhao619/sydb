(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('LectureController', LectureController);

    LectureController.$inject = ['$scope','$uibModal', 'InnovateService','toaster'];

    function LectureController($scope, $uibModal,InnovateService,toaster) {
        var vm = this;
        vm.queryModel={
            sort:"-updateTime",
            pageSize: 10,
            pageNumber: 0,
            q:null
        };
        vm.ids=[];//id数组
        vm.selectPage = selectPage; //设置分页
        vm.selectPageSize = selectPageSize; //设置分页大小
        vm.query = query; // 关键字搜索
        vm.openModal = openModal; // 打开弹窗
        vm.del = del; // 删除
        vm.dels=dels;//批量删除
        vm.checkedOne = checkedOne;// 单选
        vm.checkedAll = checkedAll; // 全选
        vm.top=top;//置顶
        vm.cancelTop=cancelTop;//取消置顶
        vm.ProjectLecturePage={};
        vm.dataWarning="";
        vm.q=null;
        function init() {
            InnovateService.getProjectLecture(vm.queryModel,function (data) {
                if(data.status != "SUCCESS"){
                    vm.dataShow = false;
                    if(data.code==40){
                        vm.dataWarning="抱歉！暂无数据！";
                    }else {
                        vm.dataWarning = data.message +"("+ data.description+")！";
                    }
                    return;
                }
                vm.ProjectLecturePage=data.response;
                vm.dataShow = true;
            },function (err) {
                vm.dataShow = false;
                console.log(err)
            })
        }
        init();

        function query(queryModel) {
            queryModel.pageNumber = 0;
            queryModel.pageSize = 10;
            queryModel.sort = '-updateTime';
            queryModel.q=vm.q;
            init();
        }

        function selectPage(select, current, max) {
            if (select !== current) {
                if (select > max) {
                    return;
                }

                vm.queryModel.pageNumber = select;
                vm.q=vm.queryModel.q;
                init();
                vm.ProjectLecturePage.pageNum = select;
            }
        };

        function selectPageSize(pageSize) {
            vm.queryModel.pageNumber = 0;
            vm.queryModel.pageSize = pageSize;
        }

        function openModal (index,model) {
            vm.passData={};
            var out = $uibModal.open({
                animation : true,
                templateUrl : "app/content/contentManagement/innovate/lecture/lectureModal.html",
                controller : "LectureModalController",
                controllerAs:"vm",
                size : "lg",
                resolve : {
                    items : function() {
                        if (index != null){
                            vm.passData.id=vm.ProjectLecturePage.list[index].id;
                        } else {
                            vm.passData.id=null;
                        }
                        vm.passData.showModel = model;
                        return vm.passData;
                    }
                }
            });
            out.result.then(function (result) {
                console.log(result); //result关闭是回传的值
            }, function (reason) {
                init();
            });
        }

        //置顶
        function top($index) {
            vm.topData={};
            var out = $uibModal.open({
                animation : true,
                templateUrl : "app/content/contentManagement/innovate/lecture/lectureTopModal.html",
                controller : "lectureTopModalController",
                controllerAs:"vm",
                size : "lg",
                resolve : {
                    items : function() {
                        vm.topData.projectLectureid=vm.ProjectLecturePage.list[$index].id;
                        vm.topData.topImage=vm.ProjectLecturePage.list[$index].topImage;
                        return vm.topData;
                    }
                }
            });
            out.result.then(function (result) {
                //result关闭是回传的值
                if(result){
                    init();
                }
            }, function () {
            });
        }

        //取消置顶
        function cancelTop(id) {
            var out =$uibModal.open({
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
                    InnovateService.topLecture({id: id,topImage:null}, function success(obj) {
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

        function del(index) {
            vm.ids.push(vm.ProjectLecturePage.list[index].id);
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
                    InnovateService.deleteProjectLecture({ids:vm.ids}, function success(obj) {
                        if (obj.status == 'SUCCESS') {
                            toaster.clear();
                            toaster.pop('info', obj.message);
                            init();
                        } else {
                            //反馈信息
                            toaster.clear();
                            toaster.pop('error', obj.description);
                        }
                    },function error (obj) {
                        if(obj.message){
                            vm.msg = obj.message +"("+ obj.description+")！";
                        }
                    });
                    vm.ids=[];//删除过后将id数组置空
                }
            }, function (reason) {

            });
        }

        function dels() {
            if (vm.ids.length<1){
                toaster.clear();
                toaster.pop('warning',"至少选中一条记录！");
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
                        return '确定删除这些数据吗'
                    }
                }
            });
            out.result.then(function (result) {//result关闭是回传的值
                if(result == 1){
                    InnovateService.deleteProjectLecture({ids:vm.ids}, function success(obj) {
                        if (obj.status == 'SUCCESS') {
                            toaster.clear();
                            toaster.pop('info', obj.message);
                            init();
                        } else {
                            //反馈信息
                            toaster.clear();
                            toaster.pop('error', obj.description);
                        }
                    },function error (obj) {
                        if(obj.message){
                            vm.msg = obj.message +"("+ obj.description+")！";
                        }
                    });
                    vm.ids=[];//删除过后将id数组置空
                }
            }, function (reason) {

            });
        }

        function checkedOne(id, index, checkOne) {
            if(checkOne){
                vm.ids.push(id);
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
            if (vm.fLength == vm.ProjectLecturePage.list.length) {
                vm.selectCheckedAll = true;
            }
            else {
                vm.selectCheckedAll = false;
            }
        }

        function checkedAll(m) {
            vm.fLength = 0;
            for (var i = 0, len = vm.ProjectLecturePage.list.length; i < len; i++) {
                //根据全选状态进行赋值
                if (m) {
                    vm.ids.push(vm.ProjectLecturePage.list[i].id);
                    var BL = vm.ProjectLecturePage.list[i].checked;
                    if (BL == false) {
                        vm.fLength += 1
                    }
                    vm.ProjectLecturePage.list[i].checked = true;
                }
                else {
                    for (var y = 0; y < vm.ids.length; y++) {
                        if(vm.ids[y] == vm.ProjectLecturePage.list[i].id){
                            vm.ids.splice(y,1);
                        }
                    }
                    vm.ProjectLecturePage.list[i].checked = false;
                }
            }
        }
    }
})();