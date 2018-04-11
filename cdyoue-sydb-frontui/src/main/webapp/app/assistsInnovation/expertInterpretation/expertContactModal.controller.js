/**
 * Created by Administrator on 2017/7/12 0012.
 */
(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('expertContactModalController', expertContactModalController);

    expertContactModalController.$inject = ['$scope', 'loginService', '$uibModalInstance', 'entity', 'expertInterpretationService', 'toaster'];

    function expertContactModalController($scope, loginService, $uibModalInstance, entity, expertInterpretationService, toaster) {
        var vm = this;

        vm.enterName = '';

        vm.cancel = cancel;
        vm.save = save;

        // 页面加载完成执行
        $scope.$watch('$viewContentLoaded', function() {
            init();
        });

        function init() {
            vm.contactExpertId = entity.contactExpertId;
            var token = $.cookie('syFrontUserTokenKey');
            loginService.getUserMessageByToken({token:token},function (rel) {
                if (rel.code === 20) {
                    vm.enterName = rel.response.userName;
                } else {

                }
            });
        }

        function cancel() {
            vm.contactExpertModel = {};
            $uibModalInstance.dismiss('cancel');
        }

        function save() {
            expertInterpretationService.contactExpert({id: vm.contactExpertId},vm.contactExpertModel,function(data){
                if (data.status == 'SUCCESS') {
                    vm.contactExpertModel = {};
                    toaster.clear();
                    toaster.pop('info', "提交成功");
                    //alert("提交成功");
                    cancel();
                } else {
                    toaster.clear();
                    toaster.pop('info', data.message + "：" + data.description);
                    //alert(data.message+":"+data.description);
                }
            })
        }

    }
})();
