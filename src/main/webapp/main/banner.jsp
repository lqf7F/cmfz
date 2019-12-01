<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; utf-8" %>
<script>
    $(function () {
        $("#bannerList").jqGrid({
            url: "${pageContext.request.contextPath}/banner/selectAll",
            //设置url
            editurl: "${pageContext.request.contextPath}/banner/edit",
            datatype: "json",
            colNames: ["编号", "标题", "描述", "创建时间", "状态", "图片"],
            colModel: [
                {name: "id"},
                {name: "title", editable: true},
                {name: "description", editable: true},
                {name: "time"},
                {
                    name: "state", editable: true, edittype: "select",
                    editoptions: {value: "Y:展示;N:不展示"},
                    formatter: function (a, b, c) {
                        if (a == 'Y') {
                            return "展示";
                        } else {
                            return "不展示";
                        }
                    }
                },
                {
                    name: "img", editable: true, edittype: "file",
                    //cellvalue输出的是图片的名字
                    //options当前表格中所有的参数都可以拿到
                    //当前图片的所有信息
                    formatter: function (cellvalue, options, rowBoject) {
                        return "<img style='width:100px;height:70px' src='${pageContext.request.contextPath}/img/" + cellvalue + "'/>"
                    }
                }
            ],
            styleUI: "Bootstrap",
            autowidth: true,
            //展示分页栏
            pager: "#bannerPager",
            //设置每页展示几条
            rowNum: 2,
            //默认展示第几页
            page: 1,
            //以数组的形式选择展示几条
            rowList: [2, 4, 6, 8],
            //可以多选数据
            multiselect: true,
            //在右下角展示总共有多少条
            viewrecords: true
        }).jqGrid("navGrid", "#bannerPager",
            {
                //处理前台页面按钮组的样式以及展示后者不展示。
                search: false,
            },
            {
                //控制编辑按钮，在编辑之前或者之后要进行的额外操作
                beforeShowForm: function (obj) {
                    obj.find("#img").attr("disabled", true)
                }
            },
            {
                //控制添加按钮，在添加之前或者之后要进行的额外操作
                //打开后关闭
                closeAfterAdd: true,
                afterSubmit: function (response) {
                    var bannerId = response.responseText;
                    $.ajaxFileUpload({
                        url: "${pageContext.request.contextPath}/banner/upload",
                        data: {id: bannerId},
                        fileElementId: "img",
                        success: function (data) {

                        }
                    });
                    return "response"
                }
            },
            {
                //控制删除按钮，在删除之前或者之后进行的额外操作

            }
        )
    })
    $("#btn").click(function () {
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/banner/select",
            success: function (result) {
                console.log("完成");
            }
        })
    })
</script>
<table id="bannerList"></table>
<div id="bannerPager"></div>
<button id="btn" class="gradient-button-1" style="color: #419641">开始导入</button>
