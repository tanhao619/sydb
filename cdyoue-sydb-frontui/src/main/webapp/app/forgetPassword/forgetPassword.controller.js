(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('forgetPasswordController', forgetPasswordController);

    forgetPasswordController.$inject = ['$rootScope', '$state','forgetPasswordService','$timeout','$uibModal'];

    function forgetPasswordController($rootScope, $state,forgetPasswordService,$timeout,$uibModal) {
        var vm = this;
        vm.nav = [];
        vm.nav = ['确认账号','安全验证','重置密码'];
        vm.show = 1;
        vm.code="";
        vm.tel="";
        vm.check = check;
        vm.testInput = "";
        vm.input="";
        vm.modalTips = "";
        //验证账户信息和验证码信息
        function check() {
            vm.modalTips = "";
            forgetPasswordService.checkAccount({tel:vm.tel,email:vm.tel,input:vm.input.toLocaleLowerCase(),testInput:vm.testInput.toLocaleLowerCase()},function (rel) {
                if(rel.response == -1){
                    vm.modalTips = "账号不存在";
                }else if(rel.response == 1){
                    vm.modalTips = "请输入验证码";
                }else if(rel.response == 2){
                    vm.modalTips = "验证码错误";
                }else if(rel.response == 0){
                    $state.go("securityValidate",{tel:vm.tel});
                }
                if(vm.modalTips.length>0){
                    var out = $uibModal.open({
                        animation : true,
                        templateUrl : "layouts/messageTips/messageTipsModal.html",
                        controller : "messageTipsModalController",
                        controllerAs:"vm",
                        size : "sm",
                        resolve : {
                            items : function() {
                                return vm.modalTips;
                            }
                        }
                    });
                }
            },function (err) {
            })

        }

        //生成验证码
        $timeout(
             function validate(){
                !(function(window, document) {
                    function GVerify(options) { //创建一个图形验证码对象，接收options对象为参数
                        this.options = { //默认options参数值
                            id: "", //容器Id
                            canvasId: "verifyCanvas", //canvas的ID
                            width: "100", //默认canvas宽度
                            height: "30", //默认canvas高度
                            type: "blend", //图形验证码默认类型blend:数字字母混合类型、number:纯数字、letter:纯字母
                            code: ""
                        }

                        if(Object.prototype.toString.call(options) == "[object Object]"){//判断传入参数类型
                            for(var i in options) { //根据传入的参数，修改默认参数值
                                this.options[i] = options[i];
                            }
                        }else{
                            this.options.id = options;
                        }

                        this.options.numArr = "0,1,2,3,4,5,6,7,8,9".split(",");
                        this.options.letterArr = getAllLetter();

                        this._init();
                        this.refresh();
                    }

                    GVerify.prototype = {
                        /**版本号**/
                        version: '1.0.0',

                        /**初始化方法**/
                        _init: function() {
                            var con = document.getElementById(this.options.id);
                            var canvas = document.createElement("canvas");
                            this.options.width = con.offsetWidth > 0 ? con.offsetWidth : "100";
                            this.options.height = con.offsetHeight > 0 ? con.offsetHeight : "30";
                            canvas.id = this.options.canvasId;
                            canvas.width = this.options.width;
                            canvas.height = this.options.height;
                            canvas.style.cursor = "pointer";
                            canvas.innerHTML = "您的浏览器版本不支持canvas";
                            con.appendChild(canvas);
                            var parent = this;
                            canvas.onclick = function(){
                                parent.refresh();
                            }
                        },

                        /**生成验证码**/
                        refresh: function() {
                            this.options.code = "";
                            var canvas = document.getElementById(this.options.canvasId);
                            if(canvas.getContext) {
                                var ctx = canvas.getContext('2d');
                            }else{
                                return;
                            }

                            ctx.textBaseline = "middle";

                            ctx.fillStyle = randomColor(180, 240);
                            ctx.fillRect(0, 0, this.options.width, this.options.height);

                            if(this.options.type == "blend") { //判断验证码类型
                                var txtArr = this.options.numArr.concat(this.options.letterArr);
                            } else if(this.options.type == "number") {
                                var txtArr = this.options.numArr;
                            } else {
                                var txtArr = this.options.letterArr;
                            }

                            for(var i = 1; i <= 4; i++) {
                                var txt = txtArr[randomNum(0, txtArr.length)];
                                this.options.code += txt;
                                ctx.font = randomNum(this.options.height/2, this.options.height) + 'px SimHei'; //随机生成字体大小
                                ctx.fillStyle = randomColor(50, 160); //随机生成字体颜色
                                ctx.shadowOffsetX = randomNum(-3, 3);
                                ctx.shadowOffsetY = randomNum(-3, 3);
                                ctx.shadowBlur = randomNum(-3, 3);
                                ctx.shadowColor = "rgba(0, 0, 0, 0.3)";
                                var x = this.options.width / 5 * i;
                                var y = this.options.height / 2;
                                var deg = randomNum(-30, 30);
                                /**设置旋转角度和坐标原点**/
                                ctx.translate(x, y);
                                ctx.rotate(deg * Math.PI / 180);
                                ctx.fillText(txt, 0, 0);
                                /**恢复旋转角度和坐标原点**/
                                ctx.rotate(-deg * Math.PI / 180);
                                ctx.translate(-x, -y);
                            }
                            /**绘制干扰线**/
                            for(var i = 0; i < 4; i++) {
                                ctx.strokeStyle = randomColor(40, 180);
                                ctx.beginPath();
                                ctx.moveTo(randomNum(0, this.options.width), randomNum(0, this.options.height));
                                ctx.lineTo(randomNum(0, this.options.width), randomNum(0, this.options.height));
                                ctx.stroke();
                            }
                            /**绘制干扰点**/
                            for(var i = 0; i < this.options.width/4; i++) {
                                ctx.fillStyle = randomColor(0, 255);
                                ctx.beginPath();
                                ctx.arc(randomNum(0, this.options.width), randomNum(0, this.options.height), 1, 0, 2 * Math.PI);
                                ctx.fill();
                            }
                            //$("#testInput").attr("ng-init","vm.yzm="+this.options.code);
                            vm.testInput = this.options.code;
                            // alert(this.options.code);
                        },

                        /**验证验证码**/
                        validate: function(code){
                            var code = code.toLowerCase();
                            var v_code = this.options.code.toLowerCase();
                            if(code == v_code){
                                return true;
                            }else{
                                this.refresh();
                                return false;
                            }
                        }
                    }
                    /**生成字母数组**/
                    function getAllLetter() {
                        var letterStr = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z";
                        return letterStr.split(",");
                    }
                    /**生成一个随机数**/
                    function randomNum(min, max) {
                        return Math.floor(Math.random() * (max - min) + min);
                    }
                    /**生成一个随机色**/
                    function randomColor(min, max) {
                        var r = randomNum(min, max);
                        var g = randomNum(min, max);
                        var b = randomNum(min, max);
                        return "rgb(" + r + "," + g + "," + b + ")";
                    }
                    window.GVerify = GVerify;
                })(window, document);
                var verifyCode = new GVerify("v_container");
            }

        ,50)

    }
})();
