(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('basicDataController', basicDataController);

    basicDataController.$inject = ['loginService', '$scope', '$state', '$uibModal', 'basicDataService'];

    function basicDataController(loginService, $scope, $state, $uibModal, basicDataService) {
        var vm = this;

        vm.open = open;
        //打开弹窗并进行页面跳转
        vm.openModal = openModal;

        vm.basic;
        vm.certification={
            val:null,
            info:null
        };

        init();//初始化
        function init() {
            var token = $.cookie('syFrontUserTokenKey');
            loginService.checkLogin({token:token}, function (rel) {
                if (rel.code === 20) {
                    basicDataService.getBase(function (data) {
                        vm.basic = data.response;
                    });
                    basicDataService.getCertification({type:'realname'}, function (data) {
                        switch (data.code){
                            case 20:switch (data.response.reviewStatus){
                                case 0:vm.certification.val='认证审核中';vm.certification.info=data.response;break;
                                case 1:vm.certification.val='已认证';vm.certification.info=data.response;break;
                                case 2:vm.certification.val='认证失败，前去编辑';vm.certification.info=data.response;break;
                            };break;
                            case 40:vm.certification.val='未认证，前往认证';break;
                            default:break;
                        }
                    })
                } else {
                    $state.go("login");
                }
            },function (err) {
                $state.go("login");
            });
        }

       function openModal (size) {
            $state.go('enterpriseCertification');
            if (vm.certification.info.reviewStatus === 1 && vm.certification.info.look === 0) {
                basicDataService.lookCertification({id:vm.certification.info.id},function () {
                    switch (data.code){
                        case 20:break;
                        default:break;
                    }
                });
                var model = $uibModal.open({
                    templateUrl: 'app/personalCenter/basicData/enterpriseCertificationPassModal.html',
                    controller: 'enterpriseCertificationPassModal',
                    controllerAs: 'vm',
                    size: size,
                    //resolve: {

                });
            }

       }
        //打开编辑弹框
        function open(id, size, basic) {
            var model = $uibModal.open({
                templateUrl: 'app/personalCenter/basicData/basicDataModal.html',
                controller: 'basicDataModalController',
                controllerAs: 'vm',
                size: size,
                resolve: {
                    entity: function () {
                        return {
                            enterAccountSumary: basic
                        };
                    }
                }

            });
            model.result.then(function (selectedItem) {
                $scope.selected = selectedItem;
            }, function () {
                //getEntInfo(vm.entId);
                init();
                // $log.info('Modal dismissed at: ' + new Date())
            });
        }


    }
})();
