(function() {
    'use strict';

    angular
        .module('sydbApp')
        .factory('BuyIPService', BuyIPService);

    BuyIPService.$inject = ['$resource','SYDB'];

    function BuyIPService($resource,SYDB) {
        var resourceUrl = "/sy/api";
        return $resource(resourceUrl, {}, {
            // 'home': {url:SYDB+"/homes",method: 'GET',isArray:true},//获取首页信息
            // 'independent':{url:SYDB+"/independent/:id",method: 'GET',isArray:false}, //数据分析
            'getIntellectuals':{url:SYDB+"/intellectuals/seeks",method: 'GET',isArray:false}, //获取知产求购列表
            'publish':{url:SYDB+"/intellectuals/seeks",method: 'POST'}, //发布知产求购信息
            'update':{url:SYDB+"/intellectuals/seeks/:id",method: 'PUT',params:{id:'@id'}}, //更新知产求购信息
            'getDetails':{url:SYDB+"/intellectuals/seeks/:id",method: 'GET',isArray:false} //获取知产求购詳情
        });
    }
})();
