<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; utf-8" %>
<script>
    $(function () {
        $("#albumList").jqGrid({
            url: "${pageContext.request.contextPath}/article/queryByPage",
            dataType: "json",
            colNames: ["id", "标题", "分数", "作者", "播音", "集数", "简介", "发布时间", "状态", "封面"],
            colModel: [
                {name: "id"},
                {name: "title", editable: true},
                {name: "score"},
                {name: "author", editable: true},
                {name: "boradcast"},
            ]
        })
    })
</script>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>

</body>
<table id="albumList"></table>
<div id="albumPager"></div>
</html>