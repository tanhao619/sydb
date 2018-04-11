(function () {
    'use strict';

    angular
        .module('sydbApp')
        .factory('UserToken', UserToken)
        .factory('myInterceptor', myInterceptor);
    myInterceptor.$inject = ['$q', 'UserToken'];
    UserToken.$inject = [];
    function myInterceptor($q, UserToken) {
        var interceptor = {
            'request': function (config) {
                if ($.cookie('syFrontUserTokenKey')) {
                    var tokenInfo = $.cookie('syFrontUserTokenKey');
                    if(tokenInfo){
                        config.headers['Authorization'] = "web" + ' ' + tokenInfo;
                    }
                }
                return config;
            },
            'response': function (config) {
                return config;
            }
        };
        return interceptor;
    }

    function UserToken() {
        var userToken = {
            getUserToken: getUserToken,
            setUserToken: setUserToken
        };

        function getUserToken() {
            var tokenItem = sessionStorage.getItem("syFrontUserTokenKey");
            if (tokenItem != null) {
                var token = JSON.parse(tokenItem);
                var timestamp = Date.parse(new Date());
                if (token.expires_in < timestamp) {
                    $.post('/boot/refreshToken?freshToken=' + token.refresh_token + '', function (obj) {
                        setUserToken(obj);
                        return obj;
                    })
                } else {
                    return token;
                }
            }
        }
        function setUserToken(token) {
            if (token) {
                sessionStorage.setItem("syFrontUserTokenKey", JSON.stringify(token));
            }
        }
        return userToken
    }

})();
