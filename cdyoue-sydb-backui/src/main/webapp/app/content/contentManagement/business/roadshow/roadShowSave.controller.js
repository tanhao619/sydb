/**
 * Created by Administrator on 2017/5/13 0013.
 */
(function(){
    'use strict';
    angular.module('oddApp')
        .controller('RoadshowSaveController',RoadshowSaveController);

    RoadshowSaveController.$inject = ['$scope', 'SYDB','$timeout','Upload','toaster','BusinessService', '$uibModalInstance','items'];

    function RoadshowSaveController($scope, SYDB,$timeout,Upload,toaster,BusinessService, $uibModalInstance,items) {
        var vm = this;
        vm.cancel = cancel; // 关闭
        vm.details={};
        vm.entity={};
        vm.entity.contact={};
        vm.dataImage={file:null};
        vm.startConfig = {
            format : 'YYYY-MM-DD HH:mm:ss',
            maxTime: '2050-12-01',
            onChange:function (ev) {
                vm.entity.startTime = ev;
                if(vm.entity.endTime){
                    vm.twoTime=new Date(vm.entity.endTime)-new Date(vm.entity.startTime);
                }
                $scope.$apply();
            }
        };
        vm.endConfig = {
            format : 'YYYY-MM-DD HH:mm:ss',
            minTime:vm.entity.startTime,
            maxTime: '2050-12-01',
            onChange:function (ev) {
                vm.entity.endTime = ev;
                console.log(ev);
                if(vm.entity.startTime){
                    vm.twoTime=new Date(vm.entity.endTime)-new Date(vm.entity.startTime);
                }
                console.log(vm.twoTime);
                $scope.$apply();
            }
        };

        $timeout(initTime, 100);
        function initTime() {
            // TimeSelect.getTime("start", "end");
            $("#start").datetimepicker({
                language:  'zh',
                format: "yyyy-MM-dd hh:ii:ss",
                autoclose: true,
                todayBtn: true,
                pickerPosition: "bottom",
                minuteStep: 1
            }).on('change',function(ev){
                var startDate = $('#start').val();
                $("#end").datetimepicker('setStartDate',startDate);
                $("#start").datetimepicker('hide');
            });
            $("#end").datetimepicker({
                language:  'zh',
                format: "yyyy-MM-dd hh:ii:ss",
                autoclose: true,
                todayBtn: true,
                pickerPosition: "bottom",
                minuteStep: 1
            }).on('change',function(ev){
                var returnDate = $("#end").val();
                $("#start").datetimepicker('setReturnDate',returnDate);
                $("#end").datetimepicker('hide');
            });
        }

        function cancel () {
            $uibModalInstance.dismiss('cancel');
        }
        // console.log(vm.twoTime);
        vm.uploadImage=uploadImage;
        vm.save = save;
        var id =items.id;
        $timeout(function () {
            initEdit();
        },200);
        // initEdit();
        $timeout(function() {
        $('#locations').citys({
            required:true,
            nodata:'disabled',
            onChange:function(data){
                var text = data['direct']?'(直辖市)':'';
                vm.selectLocation={
                    province:data['province'],
                    city:data['city']
                }

            }
        });
        },13);

        function initEdit() {
            if (id) {
                BusinessService.getActivityDetail({'id': id}, success, error);
            }else {
                console.log('初始化');
            }
            function success(obj) {
                obj.response.startTime = obj.response.startTime.replace(/\//g,"-");
                obj.response.endTime = obj.response.endTime.replace(/\//g,"-");
                vm.details = obj.response;
                vm.entity = angular.copy(vm.details);
                $scope.ueditorSetContent("containerContent",vm.details.content);
                // console.log(obj.response.peopleNumber);
                var location=$.parseJSON(obj.response.location);
                $('#locations').citys({
                    province:location['province'],
                    city:location['city']
                });
                vm.selectLocation={
                    province:location['province'],
                    city:location['city']
                };
                vm.address=location['address'];
            }
            function error(error) {
            }
        }


        vm.ueditorShow = false;
        function save(){
            var bif= $scope.ueditorGetContentTxt("containerContent");
            if (vm.entity.briefing.length < 1){
                if (bif.length >140){
                    vm.entity.briefing = bif.substring(0,125);
                }else {
                    vm.entity.briefing = bif.substring(0,bif.length -1)
                }
            }
            vm.entity.content=$scope.ueditorGetContent("containerContent");
            if(vm.selectLocation==undefined){
                vm.selectLocation={
                    province:'北京',
                    city:'东城区'
                };
            }
            var str = $scope.ueditorGetContent('containerContent');
            // console.log(str.length);
            if(str.length <1){
                vm.ueditorShow = true;
                return ;
            }else {
                vm.ueditorShow = false;
            }
            var jsonLocation = $.extend({}, vm.selectLocation, {'address':vm.address});
            vm.entity.location= JSON.stringify(jsonLocation);
            if(isNotEmpty(vm.entity.startTime) && isNotEmpty(vm.entity.endTime)){
                if(!TimeToCompare()){
                    return;
                }
            }else {
                toaster.clear();
                toaster.pop('info', "活动起止时间不能为空");
            }
            if(id){
                BusinessService.updateActivity({'id': id},vm.entity, createSuccess, createError);
            }else {
                if (vm.entity.startTime===undefined ||vm.entity.endTime===undefined  ){
                    vm.timeremind='请选择时间';
                    return
                }
                BusinessService.saveActivity(vm.entity, createSuccess, createError);
            }
            function createSuccess(obj) {
                if(id){
                    if (obj.status==='FAIL'){
                        toaster.clear();
                        toaster.pop('info', '提交失败，请稍后重试');
                    }else{
                        toaster.clear();
                        toaster.pop('info','编辑成功');
                       window.history.go(0);
                    }

                }else{
                    if (obj.status==='FAIL'){
                        toaster.clear();
                        toaster.pop('info','提交失败');
                    }else {
                        toaster.clear();
                        toaster.pop('info','提交成功');
                        window.history.go(0);
                    }
                }

            }
            function createError() {

            }
        }

        function uploadImage() {
            var url = SYDB+'/common/upload';
            var data = angular.copy(vm.dataImage || {});
            data.file = vm.dataImage.file;
            if (data.file == null) return
            if (!data.file.name.match("\\w+\\.(jpg|jpeg|gif|png).*")){
                toaster.clear();
                toaster.pop('info', '您选择的不是图片');
                return;
            }
            Upload.upload({
                url: url,
                data: data
            }).success(function (data) {
                vm.entity.coverImgUrl = data.response.url;
                // console.log(data);
                toaster.clear();
                toaster.pop("info","上传成功!");
            }).error(function () {
                toaster.clear();
                toaster.pop("error","上传失败!");
            });
        }
        function TimeToCompare() {

            var start;
            var end;
            if(vm.entity.startTime != null){
                start  = new Date(vm.entity.startTime.replace(/-/g, "/"))
            }
            if(vm.entity.endTime != null){
                end  = new Date(vm.entity.endTime.replace(/-/g, "/"))
            }
            if(start != null && end != null){
                if(start > end){
                    toaster.clear();
                    toaster.pop("info","开始时间不能大于结束时间");
                    return false;
                }
            }
            return true;

        }

        function isNotEmpty(val) {
            if(val != null && val != undefined && val != ''){
                return true;
            }else {
                return false;
            }
        }

    }
})();