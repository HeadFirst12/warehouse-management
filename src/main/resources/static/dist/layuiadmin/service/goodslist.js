document.write("<script language=javascript src='/static/dist/layuiadmin/utils/ajaxutil.js'></script>");
layui.config({
    base: '../../../static/dist/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'contlist', 'table'], function(){
    var table = layui.table
        ,form = layui.form;

    //监听搜索
    form.on('submit(LAY-app-goods-search)', function(data){
        var field = data.field;

        //执行重载
        table.reload('LAY-warehouse-ware-list', {
            url: '/warehouse/goods-search'
            ,where: field
        });
    });

    var $ = layui.$, active = {
        batchdel: function(){
            var checkStatus = table.checkStatus('LAY-warehouse-ware-list')
                ,checkData = checkStatus.data; //得到选中的数据

            if(checkData.length === 0){
                return layer.msg('请选择数据');
            }

            layer.confirm('确定删除吗？', function(index) {
                //获取goodsId组成json集合
                const goodsIdListJson = new Array();
                $.each(checkData,function(index,item){
                    const goodsIdJson = {"goodsId":item.goodsId};
                    goodsIdListJson.push(goodsIdJson);
                });
                const result = {"goodsIdListJson":goodsIdListJson}
                //发送ajax
                houseAjax("/warehouse/goods-del",result,false);
                //重新加载表格
                table.reload('LAY-warehouse-ware-list');
            });
        },
        add: function(){
            layer.open({
                type: 2
                ,title: '添加库存'
                ,content: '/app/workorder/addgoodsform'
                ,maxmin: true
                ,area: ['400px', '550px']
                ,btn: ['确定', '取消']
                ,yes: function(index, layero){
                    //点击确认触发 iframe 内容中的按钮提交
                    var submit = layero.find('iframe').contents().find("#LAY-warehouse-add-goods-submit");
                    submit.click();
                }
            });
        },
    };

    //监听行工具事件
    table.on('tool(LAY-warehouse-ware-list)', function(obj){
        var data = obj.data;
        console.log(data);
        if(obj.event === 'del'){
            layer.confirm('真的删除行么', function(index){
                //获取goodsId组成json
                const goodsIdJson = {"goodsId":data.goodsId}
                const goodsIdJsonList = new Array();
                goodsIdJsonList.push(goodsIdJson);
                const result = {"goodsIdListJson":goodsIdJsonList};
                //发送ajax
                houseAjax("/warehouse/goods-del",result,false);
                //obj.del(); 不能前端直接删除，以后是否考虑如何这样实现
                //关闭弹窗
                layer.close(index);
                //重构表格
                table.reload('LAY-warehouse-ware-list');
            });
        } else if(obj.event === 'edit'){
            layer.open({
                type: 2,
                title: "编辑库存",
                content: "/app/work-order/edit-goods-form?goodsId="+data.goodsId,
                area: ["500px", "480px"],
                btn: ["确定", "取消"],
                yes: function (index, layero) {
                    //点击确认触发 iframe 内容中的按钮提交
                    var submit = layero.find('iframe').contents().find("#LAY-warehouse-edit-goods-submit");
                    submit.click();
                    layer.close(index)
                }
            })
        }
    });

    //监听重置条件
    form.on('submit(LAY-app-goods-refresh)', function(data){
        var field = data.field;
        $("#searchGoodsId").val("");
        $("#searchGoodsName").val("");
        $("#searchGoodsStatusId").val("");
        $("#searchLastOperatorId").val("");
        return false;
    });

    $('.layui-btn.layuiadmin-btn-list').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

});