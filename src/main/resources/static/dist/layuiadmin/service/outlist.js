layui.config({
    base: '../../../static/dist/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'contlist', 'table'], function(){
    var table = layui.table
        ,form = layui.form;

    //监听搜索
    form.on('submit(LAY-app-out-goods-search)', function(data){
        var field = data.field;

        //执行重载
        table.reload('LAY-app-out-goods-list', {
            url: '/warehouse/out-goods-search'
            ,where: field
        });
    });

    var $ = layui.$, active = {
        add: function(){
            layer.open({
                type: 2
                ,title: '添加出库日志'
                ,content: '/app/workorder/outlistform'
                ,maxmin: true
                ,area: ['450px', '300px']
                ,btn: ['确定', '取消']
                ,yes: function(index, layero){
                    //点击确认触发 iframe 内容中的按钮提交
                    var submit = layero.find('iframe').contents().find("#LAY-app-out-goods-submit");
                    submit.click();
                    layer.close(index)
                }
            });
        }
    };

    //监听重置条件
    form.on('submit(LAY-app-entry-goods-refresh)', function(data){
        var field = data.field;
        $("#searchOutOrderId").val("");
        $("#searchOutGoodsId").val("");
        return false;
    });

    $('.layui-btn.layuiadmin-btn-list').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

});