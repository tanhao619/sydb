(function () {
    'use strict';

    angular
        .module('oddApp')
        .constant('SYDB', '/boot')

        .factory('SELECT_ITEMS',function(){
            return{
            // projectType:[
            //         {key:1,value:'提供项目服务'},
            //         {key:2,value:'企业寻找需求'}
            //     ]
            }
        })
})();
