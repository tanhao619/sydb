    (function () {
    'use strict';
    angular
        .module('sydbApp')
        .controller('deviceLibraryController', deviceLibraryController);
    deviceLibraryController.$inject = ['$scope', '$state','deviceLibraryService'];
    function deviceLibraryController($scope, $state,deviceLibraryService) {

        var vm = this;
        vm.boolean = true;
        vm.type = "";
        vm.query={
            pageSize:10,
            pageNumber:0,
            sort:"-createTime",
            type:"",
            q:""
        }
        vm.page = {};
        vm.industrysListType ={
            type :1
        };
        getEquipmentPageList();
        getIndustrys();
        vm.selectPage = selectPage;

        //获取行业分类
        function getIndustrys(){

            deviceLibraryService.getIndustrys(vm.industrysListType,function (rel) {
                vm.industrysList = rel.response;
            },function (err) {

            })
        }

        //获取设备分页列表
        function getEquipmentPageList() {
            deviceLibraryService.getEquipmentPageList(vm.query,function (rel) {
                if(rel.code == 20){
                    vm.page = rel.response;
                    vm.equipmentList = rel.response.list;
                    vm.boolean = true;
                }else {
                    vm.boolean = false;
                }

            },function (err) {
                vm.boolean = false;
            })
        }

        //分页插件
        function selectPage(select, current, max) {
            if (select != current) {
                if (select > max) {
                    return;
                }
                vm.query.pageNumber = select;
                getEquipmentPageList();
            }
        }

        // 行业查询
        vm.statusIndustrys = function statusSelected(type) {
            if(type == 0){
                vm.query.type = "";
                vm.type ="";
                vm.query.sort = "-createTime";
                vm.query.pageNumber = 0;
                vm.query.q = "";
            }else{
                vm.query.type = type;
                vm.query.sort = "-createTime";
                vm.query.pageNumber = 0;
                vm.type = type;
            }

            deviceLibraryService.getEquipmentPageList(vm.query,function (rel) {
                if(rel.code == 20){
                    vm.page = rel.response;
                    vm.equipmentList = rel.response.list;
                    vm.boolean = true;
                }else {
                    vm.boolean = false;
                }
            },function (err) {

            })
        }

        //排序查询
        vm.activeClass = 1;
        vm.statusSelected = function statusSelected(type) {
            if(type == 0){
                vm.query.sort = "id";
            }else if(type == 1){
                vm.query.sort = "-createTime"
                vm.activeClass = 1;
            }else if(type == 2){
                vm.query.sort = "-viewCount";
                vm.activeClass = 2;
            }
            vm.query.pageNumber = 0;
            vm.query.q = vm.search;
            vm.query.type = vm.type;;
            deviceLibraryService.getEquipmentPageList(vm.query,function (rel) {
                if(rel.code == 20){
                    vm.page = rel.response;
                    vm.equipmentList = rel.response.list;
                    vm.boolean = true;
                }else {
                    vm.boolean = false;
                }
            },function (err) {

            })
        }


        //关键字查询
        vm.findTitle = function findTitle() {
            vm.query.q = vm.search;
            vm.query.sort = "id";
            vm.query.type = vm.type;
            vm.query.pageNumber = 0;
            deviceLibraryService.getEquipmentPageList(vm.query,function (rel) {
                if(rel.code == 20){
                    vm.page = rel.response;
                    vm.equipmentList = rel.response.list;
                    vm.boolean = true;
                }else {
                    vm.boolean = false;
                }
            },function (err) {

            })
        }


    }
})();
