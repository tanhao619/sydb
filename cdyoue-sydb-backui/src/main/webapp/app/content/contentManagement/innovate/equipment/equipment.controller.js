(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('EquipmentController', EquipmentController);

    EquipmentController.$inject = ['$scope','$uibModal', 'InnovateService','toaster'];

    function EquipmentController($scope, $uibModal,InnovateService,toaster) {
        var vm = this;

        vm.boolean = false;//控制页面显示
        vm.query={//分页信息
            pageSize:10,
            pageNumber:0,
            sort:"-top|-createTime",
            q:""
        }
        vm.page = {};//分页栏信息
        vm.selectCheckedAll = false;//
        vm.equipmentTop = {};//分页列表
        vm.ids=[];//批量数组

        vm.selectPage = selectPage;//分页方法
        vm.show = show;//查看详情
        vm.deleteEquipment = deleteEquipment;//删除
        vm.deleteAll = deleteAll;//批量删除
        vm.getEquipmentPageList = getEquipmentPageList;//获取分页列表
        vm.top = top;//置顶
        vm.cancelTop = cancelTop;//取消置顶
        vm.checkedOne = checkedOne;//单选
        vm.checkedAll = checkedAll;//全选
        vm.topCover = topCover;//置顶封面

        //获取分页列表
        getEquipmentPageList();
        function getEquipmentPageList() {
            InnovateService.getEquipmentPageList(vm.query,function (rel) {
                if(rel.code == 20){
                    vm.equipmentTop = rel.response.list;
                    vm.page = rel.response;
                    vm.boolean = true;
                    console.log(vm.equipmentTop);
                }else{
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
                vm.getEquipmentPageList();
            }
        }


        //关键字查询
        vm.findTitle = function findTitle() {
            vm.query.q = vm.q;
            vm.query.sort = "id";
            vm.query.pageNumber = 0;
            InnovateService.getEquipmentPageList(vm.query,function (rel) {
                if(rel.code == 20){
                    vm.equipmentTop = rel.response.list;
                    vm.page = rel.response;
                    vm.boolean = true;
                }else{
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
                vm.ids.splice(0,vm.equipmentTop.length);
                for(var i = 0; i < vm.equipmentTop.length; i++){
                    vm.equipmentTop[i].checked = true;
                    vm.ids.push(vm.equipmentTop[i].id);
                }

            }else{
                for(var i = 0; i < vm.equipmentTop.length; i++){
                    vm.equipmentTop[i].checked = false;
                }
                vm.ids.splice(0,vm.equipmentTop.length);
            }
        }

        //查看详情
        function show (id,type) {
            if(type == 0){//查看详情
                var out = $uibModal.open({
                    animation : true,
                    templateUrl : "app/content/contentManagement/innovate/equipment/equipmentModal.html",
                    controller : "EquipmentModalController",
                    controllerAs:"vm",
                    size : "lg",
                    resolve : {
                        items : function() {
                            return id;
                        }
                    }
                });
                out.result.then(function (result) {
                }, function () {
                });
            }else if(type == 1){//编辑
                var out = $uibModal.open({
                    animation : true,
                    templateUrl : "app/content/contentManagement/innovate/equipment/equipmentEditor.html",
                    controller : "EquipmentEditorController",
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
                        getEquipmentPageList();
                    }
                }, function () {
                });
            }else if(type == 2){//新增
                var out = $uibModal.open({
                    animation : true,
                    templateUrl : "app/content/contentManagement/innovate/equipment/equipmentAdd.html",
                    controller : "EquipmentAddController",
                    controllerAs:"vm",
                    size : "lg",
                    resolve : {
                        items : function() {
                            return 0;
                        }
                    }
                });
                out.result.then(function (result) {
                    if(result){
                        if(result){
                            getEquipmentPageList();
                        }
                    }
                }, function () {
                });
            }

        }

        //删除设备
        function deleteEquipment(id) {
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
                    InnovateService.deleteEquipment({id:id},function (rel) {
                        toaster.pop('info', rel .message);
                        getEquipmentPageList();
                    },function (err) {

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
                    InnovateService.deleteEquipment({id:vm.ids},function (rel) {
                        toaster.pop('info', rel .message);
                        getEquipmentPageList();
                    },function (err) {

                    })
                }
            }, function (reason) {
            });

        }


        //置顶
        function top(id) {
            var out = $uibModal.open({
                animation : true,
                templateUrl : "app/content/contentManagement/innovate/equipment/equipmentTopModal.html",
                controller : "equipmentTopModalController",
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
                    getEquipmentPageList();
                }
            }, function () {
            });
        }

        //取消置顶
        function cancelTop(id) {
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
                    InnovateService.cancelTop({id: id}, function (rel) {
                        toaster.clear();
                        toaster.pop('info', rel .message);
                        getEquipmentPageList();
                    }, function (err) {
                        toaster.clear();
                        toaster.pop('info', "错误！");
                    })
                }
            }, function (reason) {
            });
        }
        
        // 置顶封面
        function topCover() {
            var out = $uibModal.open({
                animation : true,
                templateUrl : "app/content/contentManagement/innovate/equipment/equipmentTopCoverModal.html",
                controller : "equipmentTopCoverModalController",
                controllerAs:"vm",
                size : "lg",
                resolve : {
                    items : function() {

                    }
                }
            });
            out.result.then(function (result) {
                if(result){
                    getEquipmentPageList();
                }
            }, function () {
            });
        }
    }
})();