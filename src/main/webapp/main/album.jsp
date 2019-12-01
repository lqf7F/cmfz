<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; utf-8" %>

<script>
    $(function () {
        $("#albumList").jqGrid({
            url: "${pageContext.request.contextPath}/album/queryByPage",
            editurl: "${pageContext.request.contextPath}/album/edit",
            datatype: "json",
            colNames: ["id", "标题", "分数", "作者", "播音", "集数", "简介", "发布时间", "状态", "封面"],
            colModel: [
                {name: "id"},//<input id="id">
                {name: "title", editable: true},
                {name: "score"},
                {name: "author", editable: true},
                {name: "boradcast", editable: true},
                {name: "count"},
                {name: "brief", editable: true},
                {name: "publishDate", formatter: "date"},
                {
                    name: "status", edittype: "select", editable: true,
                    editoptions: {value: "y:展示;n:不展示"},
                    formatter: function (a, b, c) {
                        if (a == 'y') {
                            return "展示";
                        } else {
                            return "不展示";
                        }
                    }
                },
                {
                    name: "cover", editable: true, edittype: "file",
                    formatter: function (a, b, c) {
                        return "<img style='width:100px;height:70px' src='${pageContext.request.contextPath}/img/" + a + "'/>"
                    }
                }
            ],
            styleUI: "Bootstrap",
            autowidth: true,
            pager: "#albumPager",
            rowNum: 2,
            page: 1,
            rowList: [2, 4, 8],
            multiselect: true,
            viewrecords: true,
            height: "60%",
            subGrid: true,
            subGridRowExpanded: function (subGridId, albumId) {
                var tableId = subGridId + "table";
                var pagerId = subGridId + "pager";
                $("#" + subGridId).html(
                    "<table id=" + tableId + "></table>\n" +
                    "<div id=" + pagerId + "></div>"
                );
                $("#" + tableId).jqGrid({
                    url: "${pageContext.request.contextPath}/chapter/queryByPage?aId=" + albumId,
                    editurl: "${pageContext.request.contextPath}/chapter/edit?aId=" + albumId,
                    datatype: "json",
                    colNames: ["id", "标题", "大小", "时长", "音频", "操作"],
                    colModel: [
                        {name: "id"},//<input id="id">
                        {name: "title", editable: true},
                        {name: "size"},
                        {name: "timesize"},
                        {name: "url", editable: true, edittype: "file"},
                        {
                            name: "",
                            formatter: function (a, b, c) {
                                return "<a><span onclick=\"playAudio('" + c.url + "')\" class='glyphicon glyphicon-play'></span></a>" + "                           " +
                                    "<a><span onclick=\"downloads('" + c.url + "')\" class='glyphicon glyphicon-download-alt'></span></a>"
                            }
                        }
                    ],
                    styleUI: "Bootstrap",
                    autowidth: true,
                    pager: "#" + pagerId,
                    rowNum: 2,
                    page: 1,
                    rowList: [2, 4, 8],
                    multiselect: true,
                    viewrecords: true,
                    height: "60%"
                }).jqGrid("navGrid", "#" + pagerId,
                    {},
                    {},
                    {
                        closeAfterAdd: true,
                        afterSubmit: function (response) {
                            var chapterId = response.responseText;
                            $.ajaxFileUpload({
                                url: "${pageContext.request.contextPath}/chapter/upload",
                                fileElementId: "url",
                                data: {chapterId: chapterId},
                                success: function (data) {
                                    $("#" + tableId).trigger("reloadGrid");
                                }
                            });

                            return response;
                        }
                    },
                    {}
                )

            }
        }).jqGrid("navGrid", "#albumPager",
            {},
            {
                closeAfterEdit: true,
                beforeShowForm: function (obj) {
                }
            },
            {
                closeAfterAdd: true,
                afterSubmit: function (response) {
                    var albumId = response.responseText;
                    $.ajaxFileUpload({
                        url: "${pageContext.request.contextPath}/album/upload",
                        fileElementId: "cover",
                        data: {albumId: albumId},
                        success: function (data) {
                            $("#albumList").trigger("reloadGrid");
                        }
                    });

                    return response;
                }
            },
            {}
        )
    });

    function playAudio(a) {
        $("#audioModel").modal("show");
        $("#audioId").attr("src", "${pageContext.request.contextPath}/audio/" + a);
    }

    function downloads(b) {
        location.href = "${pageContext.request.contextPath}/chapter/down?audio=" + b;
    }

</script>
<div id="audioModel" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <audio id="audioId" src="" controls></audio>
    </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<table id="albumList"></table>
<div id="albumPager"></div>