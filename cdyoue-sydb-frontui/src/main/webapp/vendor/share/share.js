/**
 * Created by Administrator on 2017/10/12 0012.
 */
//

    setTimeout(share,0);
    function share(){
        // alert(123);
        var a = "自定义";
        var b = window.location.href;
        var c = "";
        // init(a,b,c);
// function init (a,b,c) {
        window._bd_share_config = {
            common: {
                bdText: a,
                bdDesc: '',
                bdUrl: b,
                bdPic:c
            },
            share: [{
                "bdSize": 16
            }],
            slide: [{
                bdImg: 0,
                bdPos: "right",
                bdTop: 100
            }],
            image: [{
                viewType: 'list',
                viewPos: 'top',
                viewColor: 'black',
                viewSize: '16',
                viewList: ['qzone', 'tsina', 'huaban', 'tqq', 'renren']
            }],
            selectShare: [{
                "bdselectMiniList": ['qzone', 'tqq', 'kaixin001', 'bdxc', 'tqf']
            }]
        }
        with (document)0[(getElementsByTagName('head')[0] || body).appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?cdnversion=' + ~(-new Date() / 36e5)];
// }
    }


