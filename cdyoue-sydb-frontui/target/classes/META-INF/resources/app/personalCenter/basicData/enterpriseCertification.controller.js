/**
 * Created by Administrator on 2017/7/12 0012.
 */
(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('enterpriseCertificationController', enterpriseCertificationController);

    enterpriseCertificationController.$inject = ['loginService', 'toaster', 'Upload', '$state', 'basicDataService', 'SYDB', '$uibModal'];

    function enterpriseCertificationController(loginService, toaster, Upload, $state, basicDataService, SYDB, $uibModal) {
        var vm = this;

        vm.certification = {};
        vm.dataImage = {file: null};


        init();
        vm.submit = submit;
        vm.uploadFile = uploadFile;


        function init() {
            var token = $.cookie('syFrontUserTokenKey');
            loginService.checkLogin({token:token}, function (rel) {
                if (rel.code === 20) {
                    basicDataService.getCertification({type: 'realname'}, function (data) {
                        switch (data.code) {
                            case 20:
                                vm.certification = data.response;
                                switch (data.response.reviewStatus){
                                    case 0:vm.certification.submit = false;vm.certification.editor=true;break;
                                    case 1:vm.certification.submit = true;break;
                                    case 2:vm.certification.submit = false;vm.certification.editor=true;break;
                                }
                                break;
                            case 40:
                                vm.certification = {};
                                vm.certification.submit = false;
                                vm.certification.editor = false;
                                vm.certification.reviewStatus = 3;
                                vm.certification.frontImg = null;
                                break;
                            default:
                                break;
                        }
                    });
                } else {
                    $state.go("login");
                }
            },function (err) {
                $state.go("login");
            })
        }

        function submit() {
            var realNameApplyInfo = vm.certification;
            switch (vm.certification.editor) {
                case true:
                    basicDataService.editCertification({id: vm.certification.id}, realNameApplyInfo, function (data) {
                        if (data.status == 'SUCCESS') {
                            var model = $uibModal.open({
                                templateUrl: 'app/personalCenter/basicData/enterpriseCertificationSubmitModal.html',
                                controller: 'enterpriseCertificationSubmitModalController',
                                controllerAs: 'vm',
                                // size: size,
                                //resolve: {

                            });
                            init();
                        } else {
                            toaster.clear();
                            toaster.pop('info', data.message + "：" + data.description);
                            //alert(data.message+":"+data.description);
                        }
                    });
                    break;
                case false:
                    var type='realname';
                    basicDataService.saveCertification({type:type}, realNameApplyInfo, function (data) {
                        if (data.status == 'SUCCESS') {
                            var model = $uibModal.open({
                                templateUrl: 'app/personalCenter/basicData/enterpriseCertificationSubmitModal.html',
                                controller: 'enterpriseCertificationSubmitModalController',
                                controllerAs: 'vm',
                                // size: size,
                                //resolve: {

                            });
                            init();
                        } else {
                            toaster.clear();
                            toaster.pop('info', data.message + "：" + data.description);
                            //alert(data.message+":"+data.description);
                        }
                    });
                    break;
            }

        }

        function uploadFile() {
            var url = SYDB+'/common/upload';
            var data = angular.copy(vm.dataImage || {});
            data.file = vm.dataImage.file;
            if (data.file.type.substring(0, data.file.type.indexOf('/')) !== 'image') {
                toaster.clear();
                toaster.pop('warning', '上传文件格式不对！');
                return;
            }
            Upload.upload({
                url: url,
                data: data
            }).success(function (data) {
                vm.certification.frontImg = data.response.url;
                toaster.pop("info", "上传成功!");
            }).error(function () {
                toaster.pop("error", "上传失败!");
            });
        }
    }
})();
