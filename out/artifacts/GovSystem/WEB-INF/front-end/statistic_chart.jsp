<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>问卷统计</title>
    <script type="text/javascript" src="${pageContext.request.contextPath }/frontjs/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/frontjs/highcharts.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/frontjs/highcharts-exporting.js"></script>
</head>
<body>
    <div id="container" style="min-width: 400px;hight:400px"></div>
    <h3>共有 ${requestScope.get("num")} 人参与此问卷</h3>
    <script>
        $(function () {
            $('#container').highcharts({
                chart: {
                    plotBackgroundColor: null,
                    plotBorderWidth: null,
                    plotShadow: false
                },
                title: {
                    text: "${requestScope.get("title")}"
                },
                tooltip: {
                    headerFormat: '{series.name}<br>',
                    pointFormat: '{point.name}: <b>{point.percentage:.1f}%</b>'
                },
                plotOptions: {
                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        dataLabels: {
                            enabled: true,
                            format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                            style: {
                                color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                            }
                        }
                    }
                },
                series: [{
                    type: 'pie',
                    name: '满意程度',
                    data: [
                        {
                        name: '非常满意',
                        y: ${requestScope.get("a")},
                        sliced: true,
                        selected: true
                        },
                        ['比较满意',   ${requestScope.get("b")}],
                        ['一般',      ${requestScope.get("c")}],
                        ['不满意',  ${requestScope.get("d")}]
                    ]
                }]
            });
        });
    </script>
</body>
</html>
