    (function() {

    'use strict';

    angular
        .module('oddApp')
        .factory('InnovateService', InnovateService);
    InnovateService.$inject = ['$resource','SYDB'];

    function InnovateService($resource,SYDB) {
        var resourceUrl = "/sy/api";
        return $resource(resourceUrl, {}, {
            //知识产权
            'getIntellectualSales': {url:SYDB+"/intellectuals/sales",method: 'GET'},//获取知产出售列表
            'getTrademarkDetails':{url:SYDB+"/intellectuals/sales/trademark/:id",method: 'GET',isArray:false}, //获取商标详情
            'getPatentDetails':{url:SYDB+"/intellectuals/sales/patent/:id",method: 'GET',isArray:false}, //获取知产专利详情
            'getCopyrightDetails':{url:SYDB+"/intellectuals/sales/copyright/:id",method: 'GET',isArray:false}, //获取著作权详情
            'approveSale':{url:SYDB+"/intellectuals/sales/:id/approve/:type",method: 'POST'}, //通过知产出售发布
            'rejectSale':{url:SYDB+"/intellectuals/sales/:id/reject/:type",method: 'POST'}, //拒绝知产出售发布
            'deleteBrand':{url:SYDB+"/intellectuals/sales/trademark/:id",method: 'DELETE'}, //删除商标
            'deletePatent':{url:SYDB+"/intellectuals/sales/patent/:id",method: 'DELETE'}, //删除专利
            'deleteCopyright':{url:SYDB+"/intellectuals/sales/copyright/:id",method: 'DELETE'}, //删除著作权
            'saleTop':{url:SYDB+"/intellectuals/saleTop",method: 'POST'}, //知产出售置顶
            'removeSaleTop':{url:SYDB+"/intellectuals/removeSaleTop/:type/:id",params: {type:'@type',id:'@id'},method: 'PUT'}, //取消知产出售置顶

            'getIntellectualBuys': {url:SYDB+"/intellectuals/seeks",method: 'GET'},//获取知产求购列表
            'getBuyDetails':{url:SYDB+"/intellectuals/seeks/:id",method: 'GET',isArray:false}, //获取知产求购详情
            'approveBuy':{url:SYDB+"/intellectuals/seeks/:id/approve",method: 'POST'}, //通过知产求购发布
            'rejectBuy':{url:SYDB+"/intellectuals/seeks/:id/reject",method: 'POST'}, //拒绝知产求购发布
            'deleteBuy':{url:SYDB+"/intellectuals/seeks/:id",method: 'DELETE'}, //删除知产求购
            'makeBuyTop':{url:SYDB+"/intellectuals/buyTop",method: 'POST'}, //知产求购置顶
            'makeRemoveBuyTop':{url:SYDB+"/intellectuals/removeBuyTop/:id",params: {id:'@id'},method: 'PUT'}, //取消知产求购置顶

            'getPagePartners': {url:SYDB+"/intellectuals/getPagePartners",method: 'GET'},//获取合作伙伴列表
            'getPartnerDetail':{url:SYDB+"/intellectuals/partnerDetail/:id",method: 'GET',isArray:false}, //获取合作伙伴详情
            'insertPartner':{url:SYDB+"/intellectuals/partner",method: 'POST'}, //发布合作伙伴信息
            'updatePartner':{url:SYDB+"/intellectuals/partner/:id",method: 'PUT'}, //编辑合作伙伴信息
            'deletePartner':{url:SYDB+"/intellectuals/delPartner/:id",method: 'DELETE'}, //删除合作伙伴信息
            'deleteAllPartners':{url:SYDB+"/intellectuals/delAllPartner",method: 'DELETE'}, //批量删除合作伙伴信息

            // 助力创新广告发布管理
            'getAds': {url:SYDB+"/advertisement/:view",method: 'GET'},//获取助力创新广告列表
            'getAd': {url:SYDB+"/advertisement/:view/:id",method: 'GET'},//获取助力创新广告详情
            'updAd': {url:SYDB+"/advertisement/:view/:id",method: 'PUT'},//编辑助力创新广告详情

            // 专家库
            'getExperts': {url:SYDB+"/experts",method: 'GET'},//获取专家列表
            'getExpert': {url:SYDB+"/expert/:id",method: 'GET'},//获取专家详情
            'getExpertSelect': {url:SYDB+"/expert/select",method: 'GET'},//获取专家下拉列表
            'getIndustrySelect': {url:SYDB+"/industrys",method: 'GET'},//获取行业分类下拉列表
            'saveExpert': {url:SYDB+"/expert",method: 'POST'},//新增专家
            'updExpert': {url:SYDB+"/expert/:id",method: 'PUT'},//编辑专家
            'topExpert': {url:SYDB+"/expert/:id/top",params: {id:'@id', topImgUrl:'@topImgUrl'},method: 'POST'},//置顶专家
            'delExpert': {url:SYDB+"/expert",method: 'DELETE'},//删除专家
            'getExpertCareers': {url:SYDB+"/expert/:expertId/career",method: 'GET'},//获取专家的职业经历列表

            // 专家成果
            'getFruits': {url:SYDB+"/expertAchievements",method: 'GET'},//获取专家成果列表
            'getFruit': {url:SYDB+"/expertAchievement/:id",method: 'GET'},//获取专家成果详情
            'saveFruit': {url:SYDB+"/expertAchievement",method: 'POST'},//新增专家成果
            'updateFruit': {url:SYDB+"/expertAchievement/:id",method: 'PUT'},//编辑专家成果
            'topFruit': {url:SYDB+"/expertAchievement/:id/top",params: {id:'@id',topImgUrl:'@topImgUrl'},method: 'POST'},//置顶专家成果
            'delFruit': {url:SYDB+"/expertAchievement",method: 'DELETE'},//删除专家成果

            // 专业解读
            'getUnscrambles': {url:SYDB+"/professionalInterpretations",method: 'GET'},//获取专业解读列表
            'getUnscramble': {url:SYDB+"/professionalInterpretation/:id",method: 'GET'},//获取专业解读详情
            'saveUnscramble': {url:SYDB+"/professionalInterpretation",method: 'POST'},//新增专业解读
            'updUnscramble': {url:SYDB+"/professionalInterpretation/:id",method: 'PUT'},//编辑专业解读
            'topUnscramble': {url:SYDB+"/professionalInterpretation/:id/top",params: {id:'@id', topImgUrl:'@topImgUrl'},method: 'POST'},//置顶专业解读
            'delUnscramble': {url:SYDB+"/professionalInterpretation",method: 'DELETE'},//删除专业解读

            //项目解读
            'getProjectUnscramble':{url:SYDB+"/projectInterpretation",method: 'GET'},//获取项目解读列表
            'getProjectUnscrambleDetail':{url:SYDB+"/projectInterpretation/:id",method:"GET"},//获取项目解读详情
            'createProjectUnscramble':{url:SYDB+"/create/projectInterpretation",method:"POST"},//新增项目解读
            'updateProjectUnscramble':{url:SYDB+"/projectInterpretation/:id",method:"PUT"},//编辑项目解读
            'deleteProjectUnscramble':{url:SYDB+"/projectInterpretation",method:"DELETE"},//删除项目解读
            //项目申报
            'getProjectDeclare':{url:SYDB+"/projects",method: 'GET'},//获取项目申报列表
            'getProjectDeclareDetail':{url:SYDB+"/projects/:id",method:"GET"},//获取项目申报详情
            'createProjectDeclare':{url:SYDB+"/create/projects",method:"POST"},//新增项目申报
            'updateProjectDeclare':{url:SYDB+"/projects/:id",method:"PUT"},//编辑项目申报
            'deleteProjectDeclare':{url:SYDB+"/projects",method:"DELETE"},//删除项目申报
            //项目讲座
            'getProjectLecture':{url:SYDB+"/projectLecture",method: 'GET'},//获取项目申报列表
            'getProjectLectureDetail':{url:SYDB+"/projectLecture/:id",method:"GET"},//获取项目申报详情
            'createProjectLecture':{url:SYDB+"/create/projectLecture",method:"POST"},//新增项目申报
            'updateProjectLecture':{url:SYDB+"/projectLecture/:id",method:"PUT"},//编辑项目申报
            'deleteProjectLecture':{url:SYDB+"/projectLecture",method:"DELETE"},//删除项目申报
            'topLecture':{url:SYDB+"/projectLecture/top/:id",params: {id:'@id',topImage:'@topImage'},method:"PUT"},//置顶（取消置顶）项目讲座

            //设备库
            'getEquipmentPageList': {url:SYDB+"/equipment",method: 'GET'},//获取设备分页列表
            'getEquipmentById': {url:SYDB+"/equipment/:id",params: {id:'@id'},method: 'GET'},//获取设备详情
            'createEquipment': {url:SYDB+"/equipment",method: 'POST'},//新建设备
            'deleteEquipment': {url:SYDB+"/equipment/:id",params: {id:'@id'},method: 'DELETE'},//删除设备
            'updateEquipment': {url:SYDB+"/equipment",method: 'PUT'},//编辑设备
            'top': {url:SYDB+"/equipment/top",method: 'PUT'},//置顶设备
            'cancelTop': {url:SYDB+"/equipment/:id/upTop",params: {id:'@id'},method: 'PUT'},//取消置顶设备

            //技术融资
            'getFinancingBackPageList': {url:SYDB+"/financing/back",method: 'GET'},//获取后台分页列表
            'getFinancingById': {url:SYDB+"/financing/:id",method: 'GET'},//获取融资详情
            'deleteFinancing': {url:SYDB+"/financing/:id",method: 'DELETE'},//删除融资
            'madeTop': {url:SYDB+"/financing/top",method: 'PUT'},//置顶
            'cancelFinancingTop': {url:SYDB+"/financing/:id/upTop",params: {id:'@id'},method: 'PUT'},//取消置顶
            'toExamine': {url:SYDB+"/financing/examine",method: 'PUT'},//融资审核

        });
    }
})();
