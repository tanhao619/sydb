(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('projectLectureController', projectLectureController);

    projectLectureController.$inject = ['$scope', '$state', 'projectLectureService'];

    function projectLectureController($scope, $state, projectLectureService) {
            var vm = this;
            vm.q=null;
            vm.getLectureDetail=getLectureDetail;
            vm.queryModel = {
                pageNumber: 0,
                pageSize: 10,
                sort: '-updateTime',
                q: null
            };
            vm.selectPage = selectPage; //设置分页
            vm.selectPageSize = selectPageSize; //设置分页大小
            vm.query = query; // 关键字搜索
            function init() {
                projectLectureService.getProjectLecture(vm.queryModel, function (data) {
                    vm.pager = data.response;
                })
            }


            init();//初始化
            function query(queryModel) {
                queryModel.pageNumber = 0;
                queryModel.pageSize = 10;
                queryModel.sort = '-updateTime';
                queryModel.q=vm.q;
                projectLectureService.getProjectLecture(queryModel, function (data) {
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
                // projectLectureService.getProjectLecture(vm.queryModel, function (data) {
                //     vm.pager = data.response;
                // })
            }
        function getLectureDetail(id) {
          $state.go("projectLectureDetail",{id:id});
        }
    }
})();
