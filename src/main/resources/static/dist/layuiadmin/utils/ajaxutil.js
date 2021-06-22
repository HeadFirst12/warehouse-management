const houseAjax = function (urlPath, data, asyncSts) {
    layui.config({
        base: '../../../static/dist/layuiadmin/' //静态资源所在路径
    }).use(['jquery'], function () {
        const $ = layui.jquery;
        //定义ajax通讯状态
        const code_success = "00000000";
        //定义ajax业务成功状态
        const status_success = "09";
        //定义ajax业务失败状态
        const status_fail = "10";
        $.ajax({
            //请求方式
            type: "POST",
            //请求的媒体类型
            contentType: "application/json;charset=UTF-8",
            //是否异步处理
            async: asyncSts,
            //请求地址
            url: urlPath,
            //数据，json字符串
            data: JSON.stringify(data),
            //请求成功
            success : function(result) {
                if(result.code != code_success) {
                    layer.msg('网络异常,请检查网络!', {icon: 2});
                }
                if(result.status == status_fail) {
                    const error_code = result.errorCode;
                    const error_desc = result.errorDesc;
                    layer.msg("操作失败"+error_code+"-"+error_desc, {icon: 2});
                }
                if(result.status == status_success) {
                    layer.msg("操作成功", {icon: 1});
                }
            },
            //请求失败，包含具体的错误信息
            error: function (e) {
                console.log(e.status);
                console.log(e.responseText);
            }
        });
    });
};