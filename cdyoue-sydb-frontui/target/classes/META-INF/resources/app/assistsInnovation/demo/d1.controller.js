(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('DemoNewController', DemoNewController);
    DemoNewController.$inject = ['Demo', '$uibModal', 'TimeSelect', 'toaster', 'deleteFunc', 'Upload'];

    function DemoNewController(Demo, $uibModal, TimeSelect, toaster, deleteFunc, Upload) {
        var vm = this;
        console.log(333);
        vm.queryModel = {
            sort: 'createTime',
            pageNumber: 0,
            pageSize: 10
        };
        init();//初始化数据
        initTimeSelect();//初始化时间选择器
        vm.upload = upload;//图片上传
        vm.del = del;//删除提示
        vm.selectPage=selectPage; //设置分页
        vm.selectPageSize = selectPageSize; //设置分页
        vm.enterpriseDetail = enterpriseDetail;//初始化模态框

        function init() {
            Demo.getList(vm.queryModel, function (data) {
                console.log(data);
                vm.eList = data.list;
            })
        }

        //设置分页数据
        function selectPage(select, current, max) {
            if (select != current) {
                if (select > max) {
                    return;
                }

                vm.queryModel.pageNumber = select;
                Demo.getList(vm.queryModel, function (rlt) {
                    vm.pager = rlt;
                    vm.pager.pageNum = select;
                })
            }
        }

        //设置分页size
        function selectPageSize(pageSize) {
            vm.queryModel.pageNumber = 1;
            vm.queryModel.pageSize = pageSize;
            Demo.getList(vm.queryModel,function(data){
                vm.pager = data;
            })
        }


        //模态框初始化
        var modalInstance=null;
        function enterpriseDetail(eId) {
            console.log(eId);
            var resetModal = function () {
                modalInstance = null;
            };
            if (modalInstance !== null) return;
            modalInstance = $uibModal.open({
                    animation: false,
                    templateUrl: 'app/demo/d1.detail.html',
                    controller: 'EnterpriseDetailController',
                    controllerAs: 'vm',
                    resolve: {
                        entity: function () {
                            return {
                                enterpriseId: eId
                            };
                        }
                    }
                }
            );

            modalInstance.result.then(
                resetModal,
                resetModal
            );
        }

        //时间选择器
        function initTimeSelect() {
            TimeSelect.getTime("start","end");
        }

        //图片上传
        function upload() {
            if (!vm.data.file){
                return;
            }
            var url = 'zuul/ycgems/api/common/upload';
            var data = angular.copy(vm.data || {});
            data.file = vm.data.file;
            Upload.upload({
                url:url,
                data:data
            }).success(function(data){
                console.info(data);
                vm.entity.imgurl = data.fullPath+data.url;
                vm.entity.imageIdentify = data.id;
                toaster.pop("info","上传成功!"); //dialog提示
            }).error(function () {
                toaster.pop("error","上传失败!");//dialog提示
            });
        }

        //删除前的提示，用checkbox删除时请传入false
        function del(id, name) {
            var items=[];
            if(id){
                var item={
                    id:id,
                    title:name
                };
                items.push(item);
            }else{
                vm.datas.map(function(item,index){
                    if(item.state){
                        items.push(item);
                    }
                });
                if(items.length==0){
                    toaster.pop('info',"请选择后在删除");
                    return;
                }
            }

            var config = {
                title: '确认删除选中的资讯？',// 删除名字
                data: items, //删除数据
                name: 'title',// name 表示 显示属性
                id: 'id',    // id表示显示的id
                confirm: function (id) {
                    var str='';
                    if(id instanceof Array){
                        id.map(function(item,index){
                            str+=item+','
                        });
                    }else{
                        str=id.id;
                    }
                    Demo.del({id:str}, function () {
                        //
                        vm.queryModel.pageNumber = 0;
                        toaster.pop('success',"删除成功");
                        //删除成功
                        vm.init();
                    }, function () {
                        toaster.pop('error',"删除失败");
                    })
                } // 这个方法为函数确定按钮回掉函数  会返回删除的id
            };
            deleteFunc.disposeData(config);
        }

    }
})();