<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>ECharts</title>
    <!-- 引入 echarts.js -->
    <script src="../boot/js/jquery-2.2.1.min.js"></script>
    <script src="../boot/js/echarts.js"></script>
    <script src="../boot/js/goeasy-1.0.3.js" type="text/javascript"></script>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:400px;"></div>


<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '持明法洲app活跃用户',
            link: 'http://www.jd.com',
            //show:false
            textStyle: {
                color: 'green'
            }
        },
        tooltip: {},
        legend: {
            data: ['用户数量'],
            type: 'plain'
        },
        xAxis: {
            data: ["第一周", "第二周", "第三周"]
        },
        yAxis: {},
        series: [{
            name: '用户数量',
            type: 'bar',
        }]
    };

    $.ajax({
        url: '${pageContext.request.contextPath}/user/date',
        type: 'get',
        datatype: 'json',
        success: function (result) {
            myChart.setOption({
                series: [{
                    data: [result.day7, result.day14, result.day21]
                }]
            });
        }
    })


    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>
</body>
</html>