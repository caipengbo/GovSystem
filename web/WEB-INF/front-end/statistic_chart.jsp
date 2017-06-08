<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>问卷统计</title>
    <%@include file="/WEB-INF/front-end/header.jsp" %>
    <script type="text/javascript" src="${pageContext.request.contextPath }/frontjs/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/frontjs/highcharts.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/frontjs/highcharts-exporting.js"></script>
</head>
<body>
    <div id="container" style="min-width: 400px;hight:400px"></div>
    <%--<h3>共有  人参与此问卷</h3>--%>
    <div class="ui orange statistic">
        <div class="label">
            参与此问卷人数
        </div>
        <div class="value">
            ${requestScope.get("num")}
        </div>
    </div>
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
