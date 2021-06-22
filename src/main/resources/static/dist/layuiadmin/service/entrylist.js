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
        table.reload('LAY-app-content-list', {
            url: '/warehouse/entry-goods-search'
            ,where: field
        });
    });

    var $ = layui.$, active = {
        add: function(){
            layer.open({
                type: 2
                ,title: '添加出库信息'
                ,content: '/app/workorder/entrylistform'
                ,maxmin: true
                ,area: ['450px', '300px']
                ,btn: ['确定', '取消']
                ,yes: function(index, layero){

                    //点击确认触发 iframe 内容中的按钮提交
                    var submit = layero.find('iframe').contents().find('#LAY-app-workorder-submit');
                    submit.click();

                    layer.close(index)
                }
            });
        }
    };

    //监听重置条件
    form.on('submit(LAY-app-out-goods-refresh)', function(data){
        var field = data.field;
        $("#searchEntryOrderId").val("");
        $("#searchEntryGoodsId").val("");
        return false;
    });

    $('.layui-btn.layuiadmin-btn-list').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

});