(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('ApplyEquipmentController', ApplyEquipmentController);

    ApplyEquipmentController.$inject = ['$scope','toaster','$uibModal', 'ConsultationService'];

    function ApplyEquipmentController($scope,toaster, $uibModal,ConsultationService) {
        var vm = this;
        vm.applyEquipment = {};
        vm.boolean = false;
        vm.openModal = openModal;
        vm.selectPage = selectPage;
        vm.delete = deleteEquipmentApplyById;
        vm.deleteAll = deleteAll;//批量删除
        vm.checkedOne = checkedOne;//单选
        vm.checkedAll = checkedAll;//全选
        vm.ids=[];//批量数组
        vm.query={//分页信息
            pageSize:10,
            pageNumber:0,
            sort:"-createTime",
            q:""
        }
        vm.page = {};
        vm.getEquipmentApplyPageList = getEquipmentApplyPageList

        //查询分页列表
        getEquipmentApplyPageList();
        function getEquipmentApplyPageList() {
            ConsultationService.getEquipmentApplyPageList(vm.query,function (rel) {
                if(rel.code == 20){
                    vm.applyEquipment = rel.response.list;
                    vm.page = rel.response;
                    vm.boolean =  true;
                }else{
                    vm.boolean =  false;
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
                vm.getEquipmentApplyPageList();
            }
        }

        //关键字查询
        vm.findTitle = function findTitle() {
            vm.query.q = vm.q;
            vm.query.sort = "id";
            vm.query.pageNumber = 0;
            ConsultationService.getEquipmentApplyPageList(vm.query,function (rel) {
                if(rel.code == 20){
                    vm.applyEquipment = rel.response.list;
                    vm.page = rel.response;
                    vm.boolean =  true;
                }else{
                    vm.boolean =  false;
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
                vm.ids.splice(0,vm.applyEquipment.length);
                for(var i = 0; i < vm.applyEquipment.length; i++){
                    vm.applyEquipment[i].checked = true;
                    vm.ids.push(vm.applyEquipment[i].id);
                }

            }else{
                for(var i = 0; i < vm.applyEquipment.length; i++){
                    vm.applyEquipment[i].checked = false;
                }
                vm.ids.splice(0,vm.applyEquipment.length);
            }
        }

        //查看详情
        function openModal(id,name) {
                var out = $uibModal.open({
                    animation : true,
                    templateUrl : "app/content/contentManagement/consultation/applyEquipment/applyEquipmentModal.html",
                    controller : "ApplyEquipmentModalController",
                    controllerAs:"vm",
                    size : "lg",
                    resolve : {
                        items : function() {
                            return id;
                        },
                        item:function () {
                            return name;
                        }
                    }
                });
                out.result.then(function (result) {
                }, function () {
                });
        }

        //删除
        function deleteEquipmentApplyById(id) {
            // ConsultationService.deleteEquipmentApplyById({id:id},function (rel) {
            //     toaster.pop('info', "删除成功！");
            //     getEquipmentApplyPageList();
            // },function (err) {
            //
            // })

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
                    ConsultationService.deleteEquipmentApplyById({id:id},function (rel) {
                        getEquipmentApplyPageList();
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
            // var b = confirm("确定删除？")
            // if(b){
            //     ConsultationService.deleteEquipmentApplyById({id:vm.ids},function (rel) {
            //         toaster.pop('info', "删除成功！");
            //         getEquipmentApplyPageList();
            //     },function (err) {
            //
            //     })
            // }

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
                    ConsultationService.deleteEquipmentApplyById({id:vm.ids},function (rel) {
                        getEquipmentApplyPageList();
                        toaster.pop('info', rel .message);
                    },function (err) {

                    })
                }
            }, function (reason) {
            });
        }
    }
})();