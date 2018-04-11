/**
 * Created by Administrator on 2017/9/19 0019.
 */
(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('expertAchievementController', expertAchievementController);

    expertAchievementController.$inject = ['expertAchievementService'];

    function expertAchievementController(expertAchievementService) {
        var vm = this;

        vm.queryModel = {
            pageNumber: 1,
            pageSize: 6,
            q:null
        };
        vm.q = '';

        vm.selectPage=selectPage; //设置分页
        vm.selectPageSize = selectPageSize; //设置分页
        vm.query = query; // 关键字搜索

        init();//初始化
        function init() {
            expertAchievementService.getExpertAchievements(vm.queryModel, function (data) {
                vm.pager = data.response;
            })
        }

        function query() {
            vm.queryModel.q = vm.q;
            vm.queryModel.pageNumber = 1;
            expertAchievementService.getExpertAchievements(vm.queryModel, function (data) {
                vm.pager = data.response;
            })
        }

        function selectPage(select, current, max) {
            if (select !== current) {
                if (select > max) {
                    return;
                }
                vm.q = vm.queryModel.q;
                vm.queryModel.pageNumber = select + 1;
                expertAchievementService.getExpertAchievements(vm.queryModel, function (rlt) {
                    vm.pager = rlt.response;
                    vm.pager.pageNum = select;
                })
            }
        }

        function selectPageSize(pageSize) {
            vm.q = vm.queryModel.q;
            vm.queryModel.pageNumber = 1;
            vm.queryModel.pageSize = pageSize;
            expertAchievementService.getExpertAchievements(vm.queryModel,function(data){
                vm.pager = data.response;
            })
        }

    }
})();
