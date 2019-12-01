<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>ECharts</title>
    <!-- 引入 echarts.js -->
    <script src="${pageContext.request.contextPath}/boot/js/jquery-2.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/boot/js/echarts.js"></script>
    <script src="${pageContext.request.contextPath}/boot/js/goeasy-1.0.3.js" type="text/javascript"></script>
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
            text: '7,14,21天用户量',
            link: 'http://www.baidu.com',
            textStyle: {
                color: 'black'
            }
        },
        tooltip: {},
        legend: {
            data: ['用户数量'],
            type: 'plain'
        },
        xAxis: {
            data: ["7天", "14天", "21天"]
        },
        yAxis: {},
        series: [{
            name: '用户数量',
            type: 'bar',
            //data: [5, 20, 36, 10, 10, 20]
        },]
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


    var goEasy = new GoEasy({
        host: 'hangzhou.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
        appkey: "BC-90d8e6e924df45dca08793f840b92664", //替换为您的应用appkey
    });

    //接收消息
    goEasy.subscribe({
        channel: "asd", //替换为您自己的channel
        onMessage: function (result) {
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

        }
    });
</script>
</body>
</html>