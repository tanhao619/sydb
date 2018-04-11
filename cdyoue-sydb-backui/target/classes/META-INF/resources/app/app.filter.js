(function () {
    'use strict';

    angular
        .module('oddApp')
        .filter('keywordType', keywordType) // 关键字类型
        .filter('apiMethod', apiMethod) // api提交方式转换(1.POST 2.GET 3.PUT 4.DELETE)
        .filter('isURL', isURL) // 是否是网址（区分angular的路由地址还是http地址）
        .filter('reviewStatus', reviewStatus) // 审核状态
        .filter('status', status) // 开关状态
        .filter('medium', medium) // 日期格式化
        .filter('elementsIntro',elementsIntro) // 富文本处理
        .filter('educationalBackground',educationalBackground) // 学历
        .filter('zscqCategory',zscqCategory) // 知识产权类型
        .filter('role',role) // 角色处理
        .filter('accountStatus',accountStatus) // 账户是否被禁止 0未激活 1正常 2 禁用
        .filter('authenticationType',authenticationType) // 认证类型：1实名，2大咖，3导师
        .filter('patentType',patentType) // 知识产权类型
        .filter('shearContent',shearContent) // 剪切内容（当内容长度大于传入长度就省略）
        .filter('answerContent',answerContent) // 内容大于大于8个字符就省略加上查看详情链接
        .filter('incubatorLevel',incubatorLevel) // 孵化器级别（1:国家 2：省 3：市 4：区）
        .filter('timeFilter',timeFilter)//时间格式过滤
        .filter('workPlaceDetailFilter',workPlaceDetailFilter)//地址处理
        .filter('saleStatusFilter',saleStatusFilter)//出售状态处理
        .filter('SalePriceFilter',SalePriceFilter)//出售价格处理
        .filter('top',top)//置顶处理（0：置顶操作；1：取消置顶操作）
        .filter('genderFilter',genderFilter)//性别（0：♂；1：♀）
        .filter('areaTypeFilter',areaTypeFilter)//场地类别（1空间，2工位）
        .filter('areaFilter',areaFilter)//场地区域
        .filter('newFilter',newFilter)//申请入驻基地和创业指导的“新”标志
        .filter('assessTypeFilter',assessTypeFilter)//知产评估类别
        .filter('lengthFilter',lengthFilter)//字符串过长省略
        .filter('projectNameFilter',projectNameFilter)//项目申报附件名处理

    function keywordType() {
        return function (name) {
            switch (name) {
                case 'key':
                    return '关键字';
                case 'price':
                    return '价格';
                case 'time':
                    return '日期';
                case 'type':
                    return '类型';
                case 'unit':
                    return '单位';
                default:
                    break;
            }
        }
    }
    function isURL() {
        return function (url,tag) {
            if(url.indexOf(".")>= 0){
                if (tag == "true") {
                    return true;
                }else {
                    return false;
                }
            }else {
                if (tag== "false"){
                    return true;
                }else {
                    return false;
                }
            }
        }
    }
    function timeFilter(){
        return function(val){
            if(val){
                return val.slice(0,10).replace(/\//g,'-');
            }
        }
    }

    function shearContent() {
        return function (content,number) {
            var leng = content.length;
            if(leng>number){
                return content.substring(0,number);
            } else{
                return content;
            }
        }
    }
    function apiMethod() {
        return function (number) {
            switch (number) {
                case 1:
                    return 'POST';
                case 2:
                    return 'GET';
                case 3:
                    return 'PUT';
                case 4:
                    return 'DELETE';
                default:
                    break;
            }
        }
    }
    function accountStatus() {
        return function (account) {
            switch (account) {
                case 0:
                    return '未激活';
                case 1:
                    return '正常';
                case 2:
                    return '已禁用';
                default:
                    break;
            }
        }
    }
    function authenticationType() {
        return function (type) {
            switch (type) {
                case 1:
                    return '实名';
                case 2:
                    return '大咖';
                case 3:
                    return '导师';
                default:
                    break;
            }
        }
    }
    function role() {
        return function (role) {
            switch (role) {
                // case 0:
                //     return '个人用户';
                case 1:
                    return '企业用户';
                case 2:
                    return '超级管理员';
                default:
                    break;
            }
        }
    }
    function incubatorLevel() {
        return function (level) {
            switch (level) {
                case 1:
                    return '国家级孵化器';
                case 2:
                    return '省级孵化器';
                case 3:
                    return '市级孵化器';
                case 4:
                    return '区级孵化器';
                default:
                    break;
            }
        }
    }
    function medium() {
        return function (date) {
            if(!date){
                return;
            }
            var data=date.replace(/\//g,'-');
            var dataSplit=data.slice(-2);
            if(dataSplit==".0"){
                return data.slice(0,-2);
            }else {
                return data;

            }
            //if(date){
            //    var time = new Date(date);
            //    return time.getFullYear()+"-"+(time.getMonth()+1)+"-"+time.getDate() +" "+ time.getHours()+":"+time.getMinutes()+":"+time.getSeconds();
            //}else {
            //    return "";
            //}
        }
    }
    function reviewStatus() {
        return function (reviewStatus) {
            switch (reviewStatus) {
                case 0:
                    return '待审核';
                case 1:
                    return '审核通过';
                case 2:
                    return '审核未通过';
                default:
                    break;
            }
        }
    }
    function workPlaceDetailFilter() {
        return function (rel) {
            if(rel != '' && rel != null && rel != undefined){

                var city = angular.fromJson(rel);
                return city.province + city.city+city.address;
            }
            // var loc = angular.toJson(rel);

            // return loc.province + loc.city;
        }
    }

    function answerContent() {
        return function (content) {
            var leng = content.length;
            if(leng>10){
                var topTen = content.substring(0,10);
                return topTen;
            } else{
                return content;
            }
        }
    }
    // 知识产权类别：1商标，2专利，3著作权
    function zscqCategory() {
        return function (reviewStatus) {
            switch (reviewStatus) {
                case 1:
                    return '商标';
                case 2:
                    return '专利';
                case 3:
                    return '著作权';
                default:
                    break;
            }
        }
    }
    // 专利类别：1 发明专利，2 实用新型，3 外观设计专利等 ,
    function patentType() {
        return function (reviewStatus) {
            switch (reviewStatus) {
                case 1:
                    return '发明专利';
                case 2:
                    return '实用新型';
                case 3:
                    return '外观设计专利';
                default:
                    break;
            }
        }
    }
    function elementsIntro($sce){
        return function (elementsIntro){
            return $sce.trustAsHtml(elementsIntro);
        }
    }
    function status() {
        return function (status) {
            switch (status) {
                case 0:
                    return '关闭';
                case 1:
                    return '开放';
                default:
                    break;
            }
        }
    }
    function enterTypeFilter() {
        return function (type) {
            switch (type){
                case 'edit':
                    return '修改';
                case 'show':
                    return '查看';
                case 'add' :
                    return '新增';
                default:
                    break;
            }
        }
    }
    function saleStatusFilter() {
        return function (status) {
            switch (status){
                case 0:
                    return '待审核';
                break;
                case 1:
                    return '审核通过';
                    break;
                case 2 :
                    return '审核失败';
                    break;
                default:
                    return '-';
                    break;
            }
        }
    }

    function SalePriceFilter() {
        return function (val) {
            return Number(val);
        }
    }

    function educationalBackground() {
        return function (educationalBackground) {
            switch (educationalBackground) {
                case 1:
                    return "高中/中专";
                case 2:
                    return "大专";
                case 3:
                    return '本科';
                case 4:
                    return '硕士';
                case 5:
                    return '博士';
                case 6:
                    return '博士后';
                case 7:
                    return '不限';
                default:
                    break;
            }
        }
    }
    
    function top() {
        return function (val) {
            switch (val) {
                case 0:
                    return "置顶";
                case 1:
                    return "取消置顶";
                default:
                    break;
            }
        }
    }

    function genderFilter() {
        return function (val) {
            switch (val) {
                case 0:
                    return "男";
                case 1:
                    return "女";
                default:
                    break;
            }
        }
    }
    function areaTypeFilter() {
        return function (val) {
            switch (val) {
                case 1:
                    return "空间";
                case 2:
                    return "工位";
                default:
                    break;
            }
        }
    }

    function areaFilter() {
        return function (rel) {
            if(rel != '' && rel != null && rel != undefined){
                var city = angular.fromJson(rel);
                return city.province;
            }
        }
    }

    function newFilter() {
        return function (val) {
            switch (val) {
                case 0:
                    return "新";
                case 1:
                    return "";
                default:
                    break;
            }
        }
    }

    function assessTypeFilter() {
        return function (val) {
            switch (val) {
                case 1:
                    return "商标";
                case 2:
                    return "专利";
                case 3:
                    return "其他知识产权评估";
                default:
                    break;
            }
        }
    }

    function projectNameFilter() {
        return function (val) {
            if(val == undefined || val == "")
            {
                return val;
            } else{
                var str = new Array(4);
                str = val.toString().split("/");
                return str[2];
            }

        }
    }

    function lengthFilter() {
        return function(value, max, tail) {
            if (!value) return '';

            /*max = parseInt(max, 10);
             if (!max) return value;*/
            if (value.length <= max) return value;

            value = value.substr(0, max);
            /*if (wordwise) {
             var lastspace = value.lastIndexOf(' ');
             if (lastspace != -1) {
             value = value.substr(0, lastspace);
             }
             }*/

            return value + (tail || ' …');//'...'可以换成其它文字
        };
    }

})();
