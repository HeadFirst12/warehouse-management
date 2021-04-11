/** layuiAdmin.std-v1.0.0 LPPL License By http://www.layui.com/admin/ */
;layui.define(["table", "form"], function (t) {
    var e = layui.$, i = layui.table, n = layui.form;
    i.render({
        elem: "#LAY-app-content-list",
        url: "/app/content/get-entry-warehouse-list",
        cols: [[
            {
                type: "checkbox",
                fixed: "left"
            },
            {
                field: "id",
                title: "入库日志Id",
                width: "120",
                sort: !0
            },
            {
                field: "entryOrderId",
                title: "入库订单号",
                width: "200",
            },
            {
                field: "entryGoodsId",
                title: "入库商品编号",
                width: "220",
            },
            {
                field: "entryGoodsName",
                title: "入库商品名",
                width: "200",
            },
            {
                field: "entryGoodsTime",
                title: "入库商品时间",
                width: "200",
                sort: !0
            },
            {
                field: "entryGoodsNumber",
                title: "入库商品数量",
                width: "140",
            },
            {
                field: "beforeEntryGoodsNumber",
                title: "入库前商品数量",
                width: "140",
            },
            {
                field: "afterEntryGoodsNumber",
                title: "入库后商品数量",
                width: "140",
            },
            {
                field: "operatorId",
                title: "操作员",
                width: "120",
            },
            {
                field: "createTime",
                title: "创建时间",
                width: "200",
                sort: !0
            },
        ]],
        page: !0,
        limit: 10,
        limits: [10, 15, 20, 25, 30],
        text: "对不起，加载出现异常！"
    }), i.on("tool(LAY-app-content-list)", function (t) {
        var e = t.data;
        "del" === t.event ? layer.confirm("确定删除此文章？", function (e) {
            t.del(), layer.close(e)
        }) : "edit" === t.event && layer.open({
            type: 2,
            title: "编辑文章",
            content: "../../../views/app/content/listform.html?id=" + e.id,
            maxmin: !0,
            area: ["550px", "550px"],
            btn: ["确定", "取消"],
            yes: function (e, i) {
                var l = window["layui-layer-iframe" + e],
                    a = i.find("iframe").contents().find("#layuiadmin-app-form-edit");
                l.layui.form.on("submit(layuiadmin-app-form-edit)", function (i) {
                    var l = i.field;
                    t.update({
                        label: l.label,
                        title: l.title,
                        author: l.author,
                        status: l.status
                    }), n.render(), layer.close(e)
                }), a.trigger("click")
            }
        })
    }), i.render({
        elem: "#LAY-app-content-tags",
        url: layui.setter.base + "json/content/tags.js",
        cols: [[{type: "numbers", fixed: "left"}, {field: "id", width: 100, title: "ID", sort: !0}, {
            field: "tags",
            title: "分类名",
            minWidth: 100
        }, {title: "操作", width: 150, align: "center", fixed: "right", toolbar: "#layuiadmin-app-cont-tagsbar"}]],
        text: "对不起，加载出现异常！"
    }), i.on("tool(LAY-app-content-tags)", function (t) {
        var i = t.data;
        if ("del" === t.event) layer.confirm("确定删除此分类？", function (e) {
            t.del(), layer.close(e)
        }); else if ("edit" === t.event) {
            e(t.tr);
            layer.open({
                type: 2,
                title: "编辑分类",
                content: "../../../views/app/content/tagsform.html?id=" + i.id,
                area: ["450px", "200px"],
                btn: ["确定", "取消"],
                yes: function (e, i) {
                    var n = i.find("iframe").contents().find("#layuiadmin-app-form-tags"),
                        l = n.find('input[name="tags"]').val();
                    l.replace(/\s/g, "") && (t.update({tags: l}), layer.close(e))
                },
                success: function (t, e) {
                    var n = t.find("iframe").contents().find("#layuiadmin-app-form-tags").click();
                    n.find('input[name="tags"]').val(i.tags)
                }
            })
        }
    }), i.render({
        elem: "#LAY-app-content-comm",
        url: layui.setter.base + "json/content/comment.js",
        cols: [[{type: "checkbox", fixed: "left"}, {
            field: "id",
            width: 100,
            title: "ID",
            sort: !0
        }, {field: "reviewers", title: "评论者", minWidth: 100}, {
            field: "content",
            title: "评论内容",
            minWidth: 100
        }, {field: "commtime", title: "评论时间", minWidth: 100, sort: !0}
        , {
            title: "操作",
            width: 150,
            align: "center",
            fixed: "right",
            toolbar: "#table-content-com"
        }]],
        page: !0,
        limit: 10,
        limits: [10, 15, 20, 25, 30],
        text: "对不起，加载出现异常！"
    }), i.on("tool(LAY-app-content-comm)", function (t) {
        t.data;
        "del" === t.event ? layer.confirm("确定删除此条评论？", function (e) {
            t.del(), layer.close(e)
        }) : "edit" === t.event && layer.open({
            type: 2,
            title: "编辑评论",
            content: "../../../views/app/content/contform.html",
            area: ["450px", "300px"],
            btn: ["确定", "取消"],
            yes: function (t, e) {
                var n = window["layui-layer-iframe" + t], l = "layuiadmin-app-comm-submit",
                    a = e.find("iframe").contents().find("#" + l);
                n.layui.form.on("submit(" + l + ")", function (e) {
                    e.field;
                    i.reload("LAY-app-content-comm"), layer.close(t)
                }), a.trigger("click")
            },
            success: function (t, e) {
            }
        })
    }), t("contlist", {})
    ,i.render({
        elem: "#LAY-warehouse-ware-list",
        url: "/warehouse/goods-list",
        cols: [[
            {
                type: "checkbox",
                fixed: "left"
            },
            {
                field: "goodsId",
                title: "货物编号",
                width: "220",
                sort: !0
            },
            {
                field: "goodsName",
                title: "货物名字",
                width: "200",
            },
            {
                field: "goodsNumber",
                title: "货物数量",
                width: "120",
            },
            {
                field: "yesterdayGoodsNumber",
                title: "昨天货物数量",
                width: "120",
            },
            {
                field: "lastWeekGoodsNumber",
                title: "上周货物数量",
                width: "120",
            },
            {
                field: "lastMonthGoodsNumber",
                title: "上个月货物数量",
                width: "140",
            },
            {
                field: "lastQuarterGoodsNumber",
                title: "上个季度货物数量",
                width: "160",
            },
            {
                field: "lastYearGoodsNumber",
                title: "去年货物数量",
                width: "140",
            },
            {
                field: "lastOperatorId",
                title: "最后操作的操作员",
                width: "160",
            },
            {
                field: "quantityCeiling",
                title: "货物上限",
                width: "120",
            },
            {
                field: "quantityFloor",
                title: "货物下限",
                width: "120",
            },
            {
                field: "goodsStatusId",
                title: "库存状态",
                width: "200",
            },
            {
                field: "createTime",
                title: "创建时间",
                width: "170",
                sort: !0
            },
            {
                field: "updateTime",
                title: "更新时间",
                width: "170",
                sort: !0
            },
            {
                title: "操作",
                width: 150,
                align: "center",
                fixed: "right",
                toolbar: "#table-content-list"
            }
        ]],
        page: !0,
        limit: 10,
        limits: [10, 15, 20, 25, 30],
        text: "对不起，加载出现异常！"
    }), i.render({
        elem: "#LAY-app-content-comm",
        url: layui.setter.base + "json/content/comment.js",
        cols: [[{type: "checkbox", fixed: "left"}, {
            field: "id",
            width: 100,
            title: "ID",
            sort: !0
        }, {field: "reviewers", title: "评论者", minWidth: 100}, {
            field: "content",
            title: "评论内容",
            minWidth: 100
        }, {field: "commtime", title: "评论时间", minWidth: 100, sort: !0}
            , {
                title: "操作",
                width: 150,
                align: "center",
                fixed: "right",
                toolbar: "#table-content-com"
            }]],
        page: !0,
        limit: 10,
        limits: [10, 15, 20, 25, 30],
        text: "对不起，加载出现异常！"
    }), i.on("tool(LAY-app-content-comm)", function (t) {
        t.data;
        "del" === t.event ? layer.confirm("确定删除此条评论？", function (e) {
            t.del(), layer.close(e)
        }) : "edit" === t.event && layer.open({
            type: 2,
            title: "编辑评论",
            content: "../../../views/app/content/contform.html",
            area: ["450px", "300px"],
            btn: ["确定", "取消"],
            yes: function (t, e) {
                var n = window["layui-layer-iframe" + t], l = "layuiadmin-app-comm-submit",
                    a = e.find("iframe").contents().find("#" + l);
                n.layui.form.on("submit(" + l + ")", function (e) {
                    e.field;
                    i.reload("LAY-app-content-comm"), layer.close(t)
                }), a.trigger("click")
            },
            success: function (t, e) {
            }
        })
    }), i.render({
        elem: "#LAY-app-out-goods-list",
        url: "/app/content/get-out-warehouse-list",
        cols: [[
            {
                type: "checkbox",
                fixed: "left"
            },
            {
                field: "id",
                title: "出库日志Id",
                width: "120",
                sort: !0
            },
            {
                field: "outOrderId",
                title: "出库订单号",
                width: "200",
            },
            {
                field: "outGoodsId",
                title: "出库商品编号",
                width: "220",
            },
            {
                field: "outGoodsName",
                title: "出库商品名",
                width: "200",
            },
            {
                field: "outGoodsTime",
                title: "出库商品时间",
                width: "200",
                sort: !0
            },
            {
                field: "outGoodsNumber",
                title: "出库商品数量",
                width: "140",
            },
            {
                field: "beforeOutGoodsNumber",
                title: "出库前商品数量",
                width: "140",
            },
            {
                field: "afterOutGoodsNumber",
                title: "出库后商品数量",
                width: "140",
            },
            {
                field: "operatorId",
                title: "操作员",
                width: "120",
            },
            {
                field: "createTime",
                title: "创建时间",
                width: "200",
                sort: !0
            },
        ]],
        page: !0,
        limit: 10,
        limits: [10, 15, 20, 25, 30],
        text: "对不起，加载出现异常！"
    })
});