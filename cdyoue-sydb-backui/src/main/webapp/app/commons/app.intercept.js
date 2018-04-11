(function () {
    'use strict';

    angular
        .module('oddApp')
        .factory('UserToken', UserToken)
        .factory('myInterceptor', myInterceptor);
    myInterceptor.$inject = ['$q', 'UserToken'];
    UserToken.$inject = [];
    function myInterceptor($q, UserToken) {
        var tokenItem = sessionStorage.getItem("oddBackUserTokenKey");
        if(!tokenItem){
            window.location = "#!/logIn";
        }
        var interceptor = {
            'request': function (config) {
                if (UserToken.getUserToken()) {
                    var tokenInfo = UserToken.getUserToken().response;
                    if(tokenInfo){
                        config.headers['Authorization'] = tokenInfo.token_type + ' ' + tokenInfo.access_token;
                    }
                }
                return config;
            },
            'response': function (config) {
                if (config.data.code >= 60 && config.data.code < 69) {
                    sessionStorage.removeItem('oddBackUserKey');
                    sessionStorage.removeItem('oddBackUserTokenKey');
                    window.location = "#!/logIn";
                }
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
            var tokenItem = sessionStorage.getItem("oddBackUserTokenKey");
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
                sessionStorage.setItem("oddBackUserTokenKey", JSON.stringify(token));
            }
        }
        return userToken
    }

})();
