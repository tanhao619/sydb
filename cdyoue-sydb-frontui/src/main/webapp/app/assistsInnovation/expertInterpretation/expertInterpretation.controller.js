(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('expertInterpretationController', expertInterpretationController);

    expertInterpretationController.$inject = ['expertInterpretationService', '$uibModal', 'loginService'];

    function expertInterpretationController(expertInterpretationService, $uibModal, loginService) {
        var vm = this;

        vm.queryModel = {
            pageNumber: 1,
            pageSize: 6,
            industryType:null,
            q:null
        };
        vm.industrytype = {
            type: 0//0代表专家行业分类
        };
        vm.contactExpertModel = {};
        vm.q = '';

        init();//初始化
        vm.selectPage=selectPage; //设置分页
        vm.selectPageSize = selectPageSize; //设置分页
        vm.selectIndustry = selectIndustry; //选择行业
        vm.query = query; // 关键字搜索
        vm.open = open; // 联系专家弹窗
        vm.closeApplyUse = closeApplyUse; // 取消提交联系
        vm.applyTOUse = applyTOUse; // 提交联系

        function init() {
            vm.industryList = [
                {id:null, name:'全部'}
            ];
            expertInterpretationService.getIndustryType(vm.industrytype, function (data) {
                switch (data.code) {
                    case 20:
                        data.response.forEach(function(item,index,array){
                            vm.industryList.push(item);
                        });
                        break;
                    case 40:

                }
            });
            expertInterpretationService.getExpertInterpretations(vm.queryModel, function (data) {
                vm.pager = data.response;
            });
        }

        function query() {
            vm.queryModel.q = vm.q;
            vm.queryModel.pageNumber = 1;
            expertInterpretationService.getExpertInterpretations(vm.queryModel, function (data) {
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
                expertInterpretationService.getExpertInterpretations(vm.queryModel, function (rlt) {
                    vm.pager = rlt.response;
                    vm.pager.pageNum = select;
                })
            }
        }

        function selectPageSize(pageSize) {
            vm.q = vm.queryModel.q;
            vm.queryModel.pageNumber = 1;
            vm.queryModel.pageSize = pageSize;
            expertInterpretationService.getExpertInterpretations(vm.queryModel,function(data){
                vm.pager = data.response;
            })
        }

        function selectIndustry(industryId) {
            vm.queryModel.industryType = industryId;
            query(vm.queryModel);
        }

        function open(id, size) {
            var token = $.cookie('syFrontUserTokenKey');
            loginService.checkLogin({token:token}, function (rel) {
                if (rel.code === 20) {
                    var model = $uibModal.open({
                        templateUrl: 'app/assistsInnovation/expertInterpretation/expertContactModal.html',
                        controller: 'expertContactModalController',
                        controllerAs: 'vm',
                        size: size,
                        resolve: {
                            entity: function () {
                                return {
                                    contactExpertId: id
                                };
                            }
                        }
                    });
                } else {
                    var out = $uibModal.open({
                        animation : true,
                        templateUrl : "layouts/notLoginModal/notLoginModal.html",
                        controller : "notLoginModalController",
                        controllerAs:"vm",
                        size : "sm"
                    });
                }
            },function (err) {
                var out = $uibModal.open({
                    animation : true,
                    templateUrl : "layouts/notLoginModal/notLoginModal.html",
                    controller : "notLoginModalController",
                    controllerAs:"vm",
                    size : "sm"
                });
            })
        }

        function applyTOUse() {
            expertInterpretationService.contactExpert({id: vm.contactExpertId},vm.contactExpertModel,function(data){
                if (data.status == 'SUCCESS') {
                    //alert(data.message);
                    cancel();
                } else {
                    //alert(data.message+":"+data.description);
                }
            });
            vm.contactExpertModel = null;
        }

        function closeApplyUse() {
            vm.contactExpertModel = null;
        }
    }
})();
