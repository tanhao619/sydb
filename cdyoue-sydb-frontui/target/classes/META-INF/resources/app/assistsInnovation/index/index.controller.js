(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('AssistsInnovationController', AssistsInnovationController);

    AssistsInnovationController.$inject = ['assistsInnovationService', '$scope', '$state', '$uibModal'];

    function AssistsInnovationController(assistsInnovationService, $scope, $state, $uibModal) {
        var vm = this;
        //专业解读获取焦点
        vm.showPicture =0;
        vm.getFoucs = getFoucs;
        //轮播图片
        vm.sliderImg = [];

        /*vm.sliderImg = [
            {
                imgSrc:'/assets/images/assistsInnovation/img-banner.png'
            },
            {
                imgSrc:'/assets/images/assistsInnovation/img-banner.png'
            },
            {
                imgSrc:'/assets/images/assistsInnovation/img-banner.png'
            }];*/
        vm.iconAndText = [];
        vm.iconAndText = [
            {
                iconUrl:'/assets/images/assistsInnovation/icon-banner-expert.png',
                title:'专家库',
                href:'expertInterpretation'

            },
            {
                iconUrl:'/assets/images/assistsInnovation/icon-banner-project.png',
                title:'项目申报库',
                href:'projectDeclaration'
            },
            {
                iconUrl:'/assets/images/assistsInnovation/icon-banner-equipment.png',
                title:'设备库',
                href:'deviceLibrary'
            },
            {
                iconUrl:'/assets/images/assistsInnovation/icon-banner-know.png',
                title:'知产评估',
                href:'knowledgeAssess'
            },
        ];
        vm.expertRecommendation = [];
        vm.professionalInterpretationRecommendation = [];
        vm.expertAchievementRecommendation = [];
        vm.BuysBanner = [];
        vm.SalesBanner = [];

        init();//初始化
        function init() {
            assistsInnovationService.getAds({view:'zlcx'}, function (obj) {
                if(obj.code == 20){
                    vm.sliderImg = obj.response;
                }
            },function error (obj) {

            });

            assistsInnovationService.getExpertRecommendation(function (data) {
                switch (data.code) {
                    case 20:
                        vm.expertRecommendation = data.response;
                        break;
                    case 40:

                }
            });
            assistsInnovationService.getProfessionalInterpretationRecommendation(function (data) {
                switch (data.code) {
                    case 20:
                        vm.professionalInterpretationRecommendation = data.response;
                        break;
                    case 40:

                }
            });
            assistsInnovationService.getExpertAchievementRecommendation(function (data) {
                switch (data.code) {
                    case 20:
                        vm.expertAchievementRecommendation = data.response;
                        break;
                    case 40:

                }
            });
            assistsInnovationService.getBuysBanner(function (data) {
                        vm.BuysBanner = data.response;
            });
            assistsInnovationService.getSalesBanner(function (data) {
                        vm.SalesBanner = data.response;
            });
        };

        /*vm.ExpertRecommendation = [
            {imgUrl:'/assets/images/assistsInnovation/pho-Expert-recommendation-left.png'},
            {imgUrl:'/assets/images/assistsInnovation/pho-Expert-recommendation-center.png'},
            {imgUrl:'/assets/images/assistsInnovation/pho-Expert-recommendation-right.png'}
        ];*/
       function getFoucs(index){
           vm.showPicture = index;


       }
        //资产求购
        vm.getDetails = getDetails;//点击查看详情
        vm.getSaleDetails = getSaleDetails;//点击查看详情
        function getDetails(id) {
            $state.go("buyIPDetails", {id: id});
        };
        function getSaleDetails(id,type) {
            $state.go("saleIPDetails", {type:type,id: id});
        };

        
        //========================================设备==========================================//
        vm.deviceRecommendation = {};//初始化设备
        getEquipmentTop();//获取设备top列表
        function getEquipmentTop() {
            assistsInnovationService.getEquipmentCover({view:'zlcxsbk',id:1},function (rel) {
                if (rel.status == 'SUCCESS'){
                    vm.deviceCover = rel.response;
                } else {
                }
            });
            assistsInnovationService.getEquipmentTop(function (rel) {
                vm.deviceRecommendation = rel.response;
            },function (err) {

            })
        }
        //=====================================融资===========================================//
        vm.financing = "";//初始化设备
        getFinancingTop();//获取设备top列表及其封面
        function getFinancingTop() {
            assistsInnovationService.getFinancingTop(function (rel) {
                vm.financing = rel.response;
            },function (err) {

            })
        }


        //项目申报列表
        vm.projectDeclaration={};
        vm.queryModel2={
            sort: "-publishTime",
            pageSize: 7,
            pageNumber: 0
        };
        function getNewProjectDeclaration() {
            assistsInnovationService.getProjectDeclaration(vm.queryModel2,function (rel) {
                vm.projectDeclaration=rel.response;
            },function (err) {
                console.log(err);
            })
        }
        getNewProjectDeclaration();

        //项目解读列表
        vm.projectInterpretation={};
        vm.queryModel1={
            sort: "-publishTime",
            pageSize: 5,
            pageNumber: 0
        };
        function getNewProjectInterpretation() {
            assistsInnovationService.getProjectInterpretation(vm.queryModel1,function (rel) {
                vm.projectInterpretation=rel.response;
            },function (err) {
                console.log(err);
            })
        }
        getNewProjectInterpretation();

        //项目讲座
        vm.projectLecture={};
        function getNewProjectLecture() {
            assistsInnovationService.getTopProjectLecture(function (rel) {
                vm.projectLecture=rel.response;
            },function (err) {
                console.log(err);
            })
        }
        getNewProjectLecture();
    }
})();
