layui.config({
    base: '../../../../static/dist/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'form'], function(){
    var $ = layui.$
        ,form = layui.form;

    //监听提交
    form.on('submit(LAY-app-workorder-submit)', function(data){
        //获取提交的字段
        var field = data.field;
        //先得到当前iframe层的索引
        var index = parent.layer.getFrameIndex(window.name);

        //提交 Ajax 成功后，关闭当前弹层并重载表格
        $.ajax({
            //请求方式
            type : "POST",
            //请求的媒体类型
            contentType: "application/json;charset=UTF-8",
            //请求地址
            url : "/warehouse/entry-warehouse-add",
            //数据，json字符串
            data : JSON.stringify(field),
            //请求成功
            success : function(result) {
                if(result.code != "00000000") {
                    layer.msg('网络异常', {icon: 5});
                }

                if(result.status == "10") {
                    console.log(result.errCode+'-'+result.errDesc, {icon: 5});
                    layer.msg(result.errCode+'-'+result.errDesc, {
                        offset: 't',
                        anim: 6
                    });
                }

                if(result.status == "09") {
                    parent.layer.close(index);
                }
            }
        });

        //重载表格
        parent.layui.table.reload('LAY-app-content-list');
    });
});