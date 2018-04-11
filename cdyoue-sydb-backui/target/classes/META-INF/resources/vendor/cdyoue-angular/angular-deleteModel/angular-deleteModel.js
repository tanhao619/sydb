/**
 * Created by PC-45 on 2017/3/7.
 */
(function () {
    'use strict';
    angular
        .module('deleteModel',[])
        .factory('deleteFunc', deleteFunc);
    function deleteFunc(){
        var config={
            title:'删除数据',
            data:[],
            id:'id',
            name:'name',
            confirm:Object
        };

        function  showDataModel(config){
            var str=" <div class='modal fade' id='model' tabindex='-1' role='dialog' aria-labelledby='myModalLabel' aria-hidden='true'>"
                +" <div class='modal-dialog'>"
                +"<div class='modal-content'>"
                +"<div class='modal-header'>"
                +"<h7 class='modal-title' id='myModalLabel'>"+config.title+"</h7><button type='button' class='close' data-dismiss='modal'><span aria-hidden='true'>&times;</span><span class='sr-only'>Close</span></button>"
                +"</div>"
                +"<div class='modal-body'>";
            if( Object.prototype.toString.call(config.data) == "[object Array]"){
                config.data.forEach(function(data,index){
                    str+="<span >"+data[config.id] +":"+   data[config.name]+"</span><br>";
                })
            }else{
                str+="<span >"+config.data[config.id] +":"+ config.data[config.name]  +"</span><br>";
            }
            str+="</div>"
                +"<div class='modal-footer'>"
                +"<button type='button' class='btn btn-md btn-add'   id='deleteId'  data-dismiss='modal'>确认</button>"
                +"<button type='button' class='btn btn-md btn-del' data-dismiss='modal'>取消</button>"
                +"</div></div></div></div>";
            $("#deleteModel").html(str);
            $("#model").modal();
            dynamicDeleteModel(config);
        }

        function dynamicDeleteModel(config){
            jQuery("body").off("click", '#deleteId');
            jQuery("body").on('click','#deleteId',function(){
                var ids=[];
                if(Object.prototype.toString.call(config.data) == "[object Array]"){
                    config.data.forEach(function(data,index){
                        ids.push(data[config.id])
                    });
                }else {
                    ids={id:config.data[config.id]}
                }
                config.confirm(ids);
            });
        }

        function disposeData(configs){
            for(var item in configs){
                if(config[item]){
                    config[item]=configs[item]
                }
            }
            showDataModel(config);
        }

        var service={
            disposeData:disposeData
        }

        return service;
    }

})();