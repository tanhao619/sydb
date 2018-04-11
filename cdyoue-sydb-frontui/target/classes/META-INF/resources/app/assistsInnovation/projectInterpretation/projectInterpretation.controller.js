(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('projectInterpretationController', projectInterpretationController);

    projectInterpretationController.$inject = ['$scope', '$state','projectInterpretationService'];

    function projectInterpretationController($scope, $state,projectInterpretationService) {
        var vm = this;
        vm.queryModel = {
            pageNumber: 0,
            pageSize: 10,
            sort: '-updateTime',
            q:null
        };
        vm.q=null;
        vm.selectPage=selectPage; //设置分页
        vm.selectPageSize = selectPageSize; //设置分页大小
        vm.query = query; // 关键字搜索
        function init() {
            projectInterpretationService.getProjectInterpretation(vm.queryModel, function (data) {
                vm.pager = data.response;
            })
        }
        init();//初始化
        function query(queryModel) {
            queryModel.pageNumber = 0;
            queryModel.pageSize=10;
            queryModel.sort='-updateTime';
            queryModel.q=vm.q;
            projectInterpretationService.getProjectInterpretation(queryModel, function (data) {
                vm.pager = data.response;
            })
        }

        function selectPage(select, current, max) {
            if (select !== current) {
                if (select > max) {
                    return;
                }

                vm.queryModel.pageNumber = select;
                vm.q=vm.queryModel.q;
                init();
                vm.pager.pageNum = select;
            }
        };

        function selectPageSize(pageSize) {
            vm.queryModel.pageNumber = 0;
            vm.queryModel.pageSize = pageSize;
            // projectInterpretationService.getProjectInterpretation(vm.queryModel,function(data){
            //     vm.pager = data.response;
            // })
        }

    }
})();
