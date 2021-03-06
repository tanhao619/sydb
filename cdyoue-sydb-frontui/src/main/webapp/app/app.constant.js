(function () {
    'use strict';
    // DO NOT EDIT THIS FILE, EDIT THE GULP TASK NGCONSTANT SETTINGS INSTEAD WHICH GENERATES THIS FILE
    angular
        .module('sydbApp')
        .constant('VERSION', "1.0-RELEASE")
        .constant('API_PATH', "/api")
        .constant('SYSTEM_NAME', '沈阳大数据')
        .constant('SYDB', '/boot')
    ;

    angular
        .module('sydbApp')
        .factory('SELECT_ITEMS', function () {
            return {
                talentsSearchPlace: [
                    {key: 0, value: '不限'},
                    {key: 1, value: '盐城'},
                    {key: 2, value: '城北'},
                    {key: 3, value: '城南'}
                ]
            }
        })
})();
