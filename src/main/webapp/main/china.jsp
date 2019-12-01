<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<script src="${pageContext.request.contextPath}/boot/js/jquery-2.2.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/boot/js/echarts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/boot/js/china.js"></script>

<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="china" style="width: 600px;height: 600px;margin-top: 30px;margin-left: 30px">

</div>

<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('china'));

    function randomData() {
        return Math.round(Math.random() * 100);
    }

    var option = {
        title: {
            text: '用户分布图',
            subtext: '2019年11月26日 最新数据',
            left: 'center'
        },
        tooltip: {},
        // 说明
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['男', '女']
        },
        visualMap: {
            min: 0,
            max: 300,
            left: 'left',
            top: 'bottom',
            text: ['高', '低'],           // 文本，默认为数值文本
            calculable: true
        },
        // 工具箱
        toolbox: {
            show: true,
            orient: 'vertical',
            left: 'right',
            top: 'center',
            feature: {
                dataView: {readOnly: false},
                restore: {},
                saveAsImage: {}
            }
        },
        series: [
            {
                name: '男',
                type: 'map',
                mapType: 'china',
                roam: false,
                label: {
                    normal: {
                        show: true
                    },
                    emphasis: {
                        show: true
                    }
                },

            },
            {
                name: '女',
                type: 'map',
                mapType: 'china',
                label: {
                    normal: {
                        show: true
                    },
                    emphasis: {
                        show: true
                    }
                },

            }
        ]
    };


    $.ajax({
        url: '${pageContext.request.contextPath}/user/province',
        type: 'get',
        datatype: 'json',
        success: function (result) {
            myChart.setOption({
                series: [{
                    data: result["man"]

                }, {
                    data: result["woman"]
                }]
            });
        }
    })
    myChart.setOption(option);

    var goEasy = new GoEasy({
        host: 'hangzhou.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
        appkey: "BC-90d8e6e924df45dca08793f840b92664", //替换为您的应用appkey
    });

    goEasy.subscribe({
        channel: "asd", //替换为您自己的channel
        onMessage: function (result) {
            $.ajax({
                url: '${pageContext.request.contextPath}/user/province',
                type: 'get',
                datatype: 'json',
                success: function (result) {
                    myChart.setOption({
                        series: [{
                            data: result["man"]

                        }, {
                            data: result["woman"]
                        }]
                    });
                }
            })
        }
    });

</script>












