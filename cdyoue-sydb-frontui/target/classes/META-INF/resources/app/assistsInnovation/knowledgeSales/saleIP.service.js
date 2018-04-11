(function() {
    'use strict';

    angular
        .module('sydbApp')
        .factory('SaleIPService', SaleIPService);

    SaleIPService.$inject = ['$resource','SYDB'];


    function SaleIPService($resource,SYDB) {
        var resourceUrl = "/sy/api";
        return $resource(resourceUrl, {}, {
            'getIntellectuals':{url:SYDB+"/intellectuals/sales",method: 'GET',isArray:false}, //获取知产出售专利列表
            'publish':{url:SYDB+"/intellectuals/sales",method: 'POST'}, //发布知产求购
            'getTrademarkDetails':{url:SYDB+"/intellectuals/sales/trademark/:id",method: 'GET',isArray:false}, //获取商标详情
            'getPatentDetails':{url:SYDB+"/intellectuals/sales/patent/:id",method: 'GET',isArray:false}, //获取知产专利详情
            'getCopyrightDetails':{url:SYDB+"/intellectuals/sales/copyright/:id",method: 'GET',isArray:false}, //获取著作权详情
            'publishTrademark':{url:SYDB+"/intellectuals/salesBrand",method: 'POST'}, //发布商标
            'publishPatent':{url:SYDB+"/intellectuals/salesPatent",method: 'POST'}, //发布专利
            'publishCopyright':{url:SYDB+"/intellectuals/salesWork",method: 'POST'}, //发布著作权
            'updateCopyright':{url:SYDB+"/intellectuals/sales/copyright/:id",method: 'PUT',params:{id:'@id'}}, //更新著作权
            'updatePatent':{url:SYDB+"/intellectuals/sales/patent/:id",method: 'PUT',params:{id:'@id'}}, //更新专利
            'updateTrademark':{url:SYDB+"/intellectuals/sales/trademark/:id",method: 'PUT',params:{id:'@id'}}, //更新商标
            'delCopyright': {url: SYDB+'/intellectuals/sales/copyright/:id', method: 'DELETE'}, //删除出售著作权
            'delPatent': {url: SYDB+'/intellectuals/sales/patent/:id', method: 'DELETE'}, //删除出售专利
            'delTrademark': {url: SYDB+'/intellectuals/sales/trademark/:id', method: 'DELETE'}, //删除出售商标
        });
    }
})();
