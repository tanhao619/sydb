/**
 * Created by PC-45 on 2017/4/20.
 */
(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('assistsProjectController', assistsProjectController);

    assistsProjectController.$inject = ['$scope','assistsProjectService','$state'];

    function assistsProjectController($scope,assistsProjectService,$state) {
        var vm = this;
        //展开显示全部
        vm.showAllClick = showAllClick;
        //轮播图片
        vm.sliderImg = [];
        vm.iconAndText = [];
        vm.iconAndText = [
            {
                iconUrl:'/assets/images/assistsProject/icon-banner-base.png',
                title:'基地介绍',
                href:'shenyangBase'
            },
            {
                iconUrl:'/assets/images/assistsProject/icon-banner-Settled.png',
                title:'入驻企业',
                href:'settledEnterprise'
            },
            {
                iconUrl:'/assets/images/assistsProject/icon-banner-apply.png',
                title:'创业指导',
                href:'projectGuidance'
            }

        ];
        vm.show = [];
        // vm.show[index1] = false;
        function showAllClick(index1){
            // vm.show[index1] = false;
         vm.show[index1] = !vm.show[index1];
        }
        init();//初始化
        function init() {
            assistsProjectService.getAds({view: 'zlcy'}, function (obj) {
                if (obj.code == 20) {
                    vm.sliderImg = obj.response;
                }
            }, function error(obj) {

            });
            assistsProjectService.getUrls(function (obj) {
                if (obj.code == 20) {
                    vm.urls = obj.response;
                    vm.urls.forEach(function(value, index, array) {
                        vm.urls[index].links = value.content.split(";");
                        vm.show.length = vm.urls.length;
                        vm.show.forEach(function (value, index) {
                           // this.index = false;
                            vm.show[index] = false;
                        })
                    });
                }
            })
        }
        //企业推荐
        vm.enterpriseRecommend = {};
        function initEnterprise() {
            assistsProjectService.getEnterprises(function (data) {
                vm.enterpriseRecommend=data.response;
                console.log(vm.enterpriseRecommend)
            },function (err) {
                console.log(err);
            })
        }
        initEnterprise();

        //培训机构推荐
        vm.trainingRecommend={};
        function initTraining() {
            assistsProjectService.getTrainings(function (data) {
                vm.trainingRecommend=data.response;
            },function (err) {
                console.log(err);
            })
        }
        initTraining();

        //场地推荐
        vm.areaBanners=[];
        function initAreaBanner() {
            assistsProjectService.getAreaBanner(function (data) {
                vm.areaBanners=data.response;
            })
        }
        initAreaBanner();
        vm.getAreaDetails = getAreaDetails;//点击查看详情
        function getAreaDetails(id) {
            $state.go("fieldDetail", {id: id});
        };

        //路演活动推荐
        vm.activityBanners=[];
        function initActivityBanner() {
            assistsProjectService.getActivityBanner(function (data) {
                vm.activityBanners=data.response;
            })
        }
        initActivityBanner();
        vm.getActivityDetails = getActivityDetails;//点击查看详情
        function getActivityDetails(id) {
            $state.go("ActivityDetail", {id: id});
        };

      //融资平台
        vm.projectPlatform = [];
        vm.projectPlatform = ['投融界','投融界','投融界','投融界','投融界','投融界','投融界','投融界','投融界','投融界','投融界','投融界','投融界',];










    }
})
();
