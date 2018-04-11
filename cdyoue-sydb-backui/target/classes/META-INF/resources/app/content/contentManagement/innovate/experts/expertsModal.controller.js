(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('ExpertsModalController', ExpertsModalController);

    ExpertsModalController.$inject = ['$scope', '$uibModalInstance', 'items', 'InnovateService', 'Upload', 'SYDB', 'toaster'];

    function ExpertsModalController($scope, $uibModalInstance, items, InnovateService, Upload, SYDB, toaster) {
        var vm = this;

        vm.cancel = cancel; // 关闭
        vm.editor = editor; // 编辑
        vm.save = save; // 保存
        vm.uploadImage = uploadImage; // 上传图片
        vm.addCareer = addCareer; // 增加一条经历编辑框
        vm.delCareer = delCareer; // 删除一条经历
        //vm.repeatValidate =  repeatValidate; //添加经历验证

        vm.items = items;
        vm.updateItems={};
        vm.syBannerDetail={};
        vm.queryIndustryModel = {
            type: 0 // 0：专家分类
        };
        vm.dataImage = {file: null};
        vm.editShow = false;
        /*vm.showNull = [];
        // vm.showNull = false;
        vm.showMoreLength = [];
        vm.showRemainWord = [];
        vm.showDetailNull = [];
        vm.showDetailRemainWord = [];
        vm.showDetailMoreLength = [];*/

        initSyBanner();
        function initSyBanner() {
            // 是编辑还是查看
            if(vm.items.showModel == "show"){
                vm.isEditor = false;
            }else{
                vm.isEditor = true;
            }
            if (vm.items.id==null){
                vm.syBannerDetail={};
                vm.updateItems={};
                initIndustry();
                vm.updateItems.genderSelect = [
                    {key:0,val:'男'},
                    {key:1,val:'女'}
                ];
                vm.updateItems.careers=[];
            } else {
                InnovateService.getExpert({id:vm.items.id},function (rel) {
                    if (rel.status == 'SUCCESS'){
                        vm.syBannerDetail = rel.response;
                        vm.updateItems = angular.copy(vm.syBannerDetail);
                        InnovateService.getExpertCareers({expertId:vm.items.id},function (rel) {
                            if (rel.status == 'SUCCESS'){
                                vm.syBannerDetail.careers=rel.response;
                                vm.updateItems.careers=rel.response;
                                /*vm.showNull.length = vm.updateItems.careers.length; //详细内容不能为空
                                vm.showMoreLength.length = vm.updateItems.careers.length;//详细内容超出长度
                                vm.showRemainWord.length = vm.updateItems.careers.length; //详细内容剩余字数
                                vm.showDetailNull.length = vm.updateItems.careers.length;
                                vm.showDetailRemainWord.length = vm.updateItems.careers.length;
                                vm.showDetailMoreLength.length = vm.updateItems.careers.length;
                                vm.showNull.forEach(function (value,index){
                                    vm.showNull[index] = false;
                                });
                                vm.showMoreLength.forEach(function (value,index){
                                    vm.showMoreLength[index] = false;
                                });
                                vm.showRemainWord.forEach(function (value,index){
                                    vm.showRemainWord[index] = true;
                                });

                                vm.showDetailNull.forEach(function (value,index){
                                    vm.showDetailNull[index] = false;
                                });
                                vm.showDetailRemainWord.forEach(function (value,index){
                                    vm.showDetailRemainWord[index] = false;
                                });
                                vm.showDetailMoreLength.forEach(function (value,index){
                                    vm.showDetailMoreLength[index] = false;
                                });*/
                            } else {
                                vm.updateItems.careers=[];
                                /*vm.showNull.length = 0;
                                vm.showMoreLength.length = 0;
                                vm.showRemainWord.length = 0;
                                vm.showDetailNull.length = 0;
                                vm.showDetailRemainWord.length = 0;
                                vm.showDetailMoreLength.length = 0;*/
                            }
                        },function (err) {
                            //console.log(err);
                        });
                        $scope.ueditorSetContent("newsAddContainer",vm.updateItems.content);
                        initIndustry();
                        vm.updateItems.genderSelect = [
                            {key:0,val:'男'},
                            {key:1,val:'女'}
                        ];

                    }else {
                        toaster.clear();
                        toaster.pop('error', rel.message+":"+rel.description);
                        $uibModalInstance.dismiss("cancel");
                    }
                },function (err) {
                    //console.log(err);
                });
            }
        }

        function initIndustry() {
            InnovateService.getIndustrySelect(vm.queryIndustryModel,function (rel) {
                if (rel.status == 'SUCCESS'){
                    vm.updateItems.industrySelect = [];
                    rel.response.forEach(function(item,index,array){
                        vm.updateItems.industrySelect.push(item);
                    });
                }else {
                    //console.log(rel);
                }
            },function (err) {
                //console.log(err);
            });
        }

        function save () {
            /*if (vm.updateItems.careers.length>0){
                vm.updateItems.careers.forEach(function(item,index,array){
                    if(vm.showNull[index] =true){
                        return;
                    }
                    if(vm.showMoreLength[index] =true){
                        return;
                    }
                    if(vm.showDetailNull[index] =true){
                        return;
                    }
                    if(vm.showDetailMoreLength[index] =true){
                        return;
                    }
                });
            }*/
            vm.updateItems.content = $scope.ueditorGetContent("newsAddContainer");
            if($scope.ueditorGetContentTxt("newsAddContainer").trim() === ""){
                vm.editShow = true;
                return;
            } else {
                vm.editShow = false;
            }
            if (vm.items.showModel == "editor"){
                InnovateService.updExpert({'id':vm.syBannerDetail.id},vm.updateItems, function success(obj) {
                    if (obj.status == 'SUCCESS') {
                        toaster.clear();
                        toaster.pop('success', obj.message);
                        cancel();
                    } else {
                        toaster.clear();
                        toaster.pop('error', obj.message+":"+obj.description);
                    }
                });
            } else if(vm.items.showModel == "add"){
                InnovateService.saveExpert(vm.updateItems, function success(obj) {
                    if (obj.status == 'SUCCESS') {
                        toaster.clear();
                        toaster.pop('success', obj.message);
                        cancel();
                    } else {
                        toaster.clear();
                        toaster.pop('error', obj.message+":"+obj.description);
                    }
                });
            } else {
                return;
            }
        }

        function editor () {
            vm.isEditor = true;
            vm.items.showModel = "editor";
        }

        function cancel () {
            $uibModalInstance.dismiss('cancel');
        }

        function uploadImage() {
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
                vm.updateItems.headImgUrl = data.response.url;
                toaster.clear();
                toaster.pop('info', '上传图片成功');
            }).error(function () {
                toaster.clear();
                toaster.pop('warning', '上传图片失败！');
            });
        }

        function addCareer() {
            var career={
                id:0,brief:'',detail:''
            };
            vm.updateItems.careers.push(career);
            /*vm.showNull.length += 1;
            vm.showMoreLength.length += 1;
            vm.showRemainWord.length += 1;
            vm.showDetailNull.length += 1;
            vm.showDetailRemainWord.length += 1;
            vm.showDetailMoreLength.length += 1;*/
        }

        function delCareer(index) {
            vm.updateItems.careers.splice(index,1);
            /*vm.showNull.length -= 1;
            vm.showMoreLength.length -= 1;
            vm.showRemainWord.length -= 1;
            vm.showDetailNull.length -= 1;
            vm.showDetailRemainWord.length -= 1;
            vm.showDetailMoreLength.length -= 1;*/
        }

        /*function  repeatValidate(id){

            var briefValue = vm.updateItems.careers[id].brief;
            var detailValue = vm.updateItems.careers[id].detail;
            // console.log("当前id："+ vm.showNull.length);
            // console.log(vm.updateItems.careers[id].brief.length);
            // console.log(briefValue);
            if(briefValue == null || briefValue == "" || briefValue == undefined)
            {
                vm.showNull[id] = true;
                vm.showMoreLength[id] = false;
                vm.showRemainWord[id] = false;

            }
            if(vm.updateItems.careers[id].brief.length>50){
                vm.showMoreLength[id] = true;
                vm.showRemainWord[id] = false;

            }
            else if(0<vm.updateItems.careers[id].brief.length<=50){
                vm.showNull[id] = false;
                vm.showMoreLength[id] = false;
                vm.showRemainWord[id] = true;
            }
            //详细内容判断
            if(detailValue == null || detailValue == "" || detailValue == undefined){
                vm.showDetailNull[id] = true;
                vm.showDetailRemainWord[id] = false;
                vm.showDetailMoreLength[id] = false;
            }
            if(vm.updateItems.careers[id].detail.length>200){
                vm.showDetailRemainWord[id] = false;
                vm.showDetailMoreLength[id] = true;
            }
            else if(0<vm.updateItems.careers[id].detail.length<=200){
                vm.showDetailNull[id] = false;
                vm.showDetailRemainWord[id] = true;
                vm.showDetailMoreLength[id] = false;
            }

        }*/
    }
})();