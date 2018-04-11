(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('professionalInterpretationController', professionalInterpretationController);

    professionalInterpretationController.$inject = ['professionalInterpretationService'];

    function professionalInterpretationController(professionalInterpretationService) {
        var vm = this;

        vm.queryModel = {
            pageNumber: 1,
            pageSize: 10,
            q:null
        };
        vm.q = '';

        vm.selectPage=selectPage; //设置分页
        vm.query = query; //关键字搜索
        vm.selectPageSize = selectPageSize; //设置分页

        init();//初始化
        function init() {
            professionalInterpretationService.getProfessionalInterpretations(vm.queryModel, function (data) {
                vm.pager = data.response;
            })
        }
        
        function query() {
            vm.queryModel.q = vm.q;
            vm.queryModel.pageNumber = 1;
            professionalInterpretationService.getProfessionalInterpretations(vm.queryModel, function (data) {
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
                professionalInterpretationService.getProfessionalInterpretations(vm.queryModel, function (rlt) {
                    vm.pager = rlt.response;
                    vm.pager.pageNum = select;
                })
            }
        }

        function selectPageSize(pageSize) {
            vm.q = vm.queryModel.q;
            vm.queryModel.pageNumber = 1;
            vm.queryModel.pageSize = pageSize;
            professionalInterpretationService.getProfessionalInterpretations(vm.queryModel,function(data){
                vm.pager = data.response;
            })
        }

    }
})();
