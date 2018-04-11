    UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;

    UE.Editor.prototype.getActionUrl = function (action) {
        //这里很重要，很重要，很重要，要和配置中的imageActionName值一样
        if(action!=null && action == "uploadimage"){
            //这里调用后端我们写的图片上传接口
            return '/boot/uploadimage?action='+action;
        }
        else if(action!=null && action == "uploadfile"){
            return '/boot/uploadimage?action='+action;
        }
        else {
            return this._bkGetActionUrl.call(this, action);
        }
    };