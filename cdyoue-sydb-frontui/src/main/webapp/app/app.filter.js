(function () {
    'use strict';

    angular
        .module('sydbApp')
        .filter('filterProperty', filterProperty)
        .filter('areaFilter',areaFilter)
        .filter('workPlaceDetailFilter', workPlaceDetailFilter)
        .filter('customDateFilter',customDateFilter)
        .filter('activityFilter',activityFilter)
        .filter('approveStatusFilter',approveStatusFilter)
        .filter('knowledgeTimeFilter',knowledgeTimeFilter)
        .filter('elementsIntro',elementsIntro)// 富文本处理
        .filter('buyNameFilter',buyNameFilter)
        .filter('siteNameFilter',siteNameFilter)
        .filter('NameFilter',NameFilter)
        .filter('SalePriceFilter',SalePriceFilter)
        .filter('AreaAddressFilter',AreaAddressFilter)
        .filter('genderFilter',genderFilter)
        .filter('peaceFilter',peaceFilter)
        .filter('PriceFilter',PriceFilter)
        .filter('lengthFilter',lengthFilter) // 字符串过长省略过滤器
        .filter('patentTypeFilter',patentTypeFilter)
        .filter('workPlaceLimitFilter',workPlaceLimitFilter)

    function elementsIntro($sce) {
        return function (elementsIntro) {
            return $sce.trustAsHtml(elementsIntro);
        }
    }

    function filterProperty() {
        return function (filterProperty) {
            switch (filterProperty) {
                case 1:
                    return "明德物业";
                case 2:
                    return "南京高度物业";
                case 3:
                    return "科技园物业";
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

    function workPlaceDetailFilter() {
        return function (rel) {
            if(rel != '' && rel != null && rel != undefined){
                var city = angular.fromJson(rel);
                var add = city.province + city.city+city.address;
                return add;
            }
        }
    }

    function workPlaceLimitFilter() {
        return function (rel) {
            if(rel != '' && rel != null && rel != undefined){
                var city = angular.fromJson(rel);
                var add = city.province + city.city+city.address;
                if(add.length > 15){
                 add = add.substring(0,15)+"...";
                }
                return add;
            }
        }
    }

    function AreaAddressFilter() {
        return function (rel) {
            if(rel != '' && rel != null && rel != undefined){
                var city = angular.fromJson(rel);
                return city.city+city.address;
            }
        }
    }

    function customDateFilter() {
        return function (value) {
            return value.toString().substring(0,10);
        }
    }

    function activityFilter() {
        return function (rel) {
            if(rel != '' && rel != null && rel != undefined){
                var city = angular.fromJson(rel);
                return city.province+"·"+city.city;
            }
        }
    }
    
    function approveStatusFilter() {
        return function (val) {
            switch (val) {
                case 0:
                    return "审核中";
                case 1:
                    return "审核通过";
                case 2:
                    return "审核未通过";
            }
        }
    }

    function knowledgeTimeFilter() {
        return function (val) {
            return val.substring(0,10);
        }
    }

    function buyNameFilter() {
        return function (val) {
            if(val.length>10){
                var name = val.toString().substring(0,10)+"...";
                return name;
            }else{
                return val;
            }
        }
    }

    function siteNameFilter() {
        return function (val) {
            if(val.length>16){
                var name = val.toString().substring(0,16)+"...";
                return name;
            }else{
                return val;
            }
        }
    }

    function NameFilter() {
        return function (val) {
            if(val != '' && val != null && val != undefined && val.length>30){
                var name = val.toString().substring(0,30)+"...";
                return name;
            }else{
                return val;
            }
        }
    }

    function SalePriceFilter() {
        return function (val) {
            if (val != '' && val != null && val != undefined && val.length>8){
                var pri = val.toString().substring(0,8).replace(".","");
                return Number(pri) + "...";
            }else {
                return Number(val);
            }
        }
    }

    function PriceFilter() {
        return function (val) {
                return Number(val);
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

    function patentTypeFilter() {
        return function (val) {
            switch (val) {
                case 1:
                    return "发明专利";
                case 2:
                    return "实用新型专利";
                case 3:
                    return "外观设计专利";
                default:
                    break;
            }
        }
    }

    function peaceFilter() {
        return function (val) {
            return parseFloat(val).toFixed(2);;
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

            return value + (tail || '…');//'...'可以换成其它文字
        };
    }

})();
