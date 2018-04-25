$(document).ready(function() {
    var ctx1 = document.getElementById("chart1").getContext("2d");
    var data1 = {
        labels: ["January", "February", "March", "April", "May", "June", "July"],
        datasets: [{
            label: "My Second dataset",
            fillColor: "rgba(255, 162, 0, 0.73)",
            strokeColor: "rgba(255, 162, 0, 0.73)",
            pointColor: "rgba(255, 162, 0, 0.73)",
            pointStrokeColor: "#fff",
            pointHighlightFill: "#fff",
            pointHighlightStroke: "rgba(255, 162, 0, 0.73)",
            data: [30, 45, 50, 25, 80, 30, 100]
        }, {
            label: "My First dataset",
            fillColor: "#0acae2",
            strokeColor: "#0acae2",
            pointColor: "#0acae2",
            pointStrokeColor: "#fff",
            pointHighlightFill: "#fff",
            pointHighlightStroke: "#0acae2",
            data: [0, 50, 80, 40, 20, 65, 40]
        }],
    };
    Chart.types.Line.extend({
        name: "LineAlt",
        initialize: function() {
            Chart.types.Line.prototype.initialize.apply(this, arguments);
            var ctx = this.chart.ctx;
            var originalStroke = ctx.stroke;
            ctx1.stroke = function() {
                ctx1.save();
                ctx1.shadowColor = 'rgba(0, 0, 0, 0.4)';
                ctx1.shadowBlur = 10;
                ctx1.shadowOffsetX = 8;
                ctx1.shadowOffsetY = 8;
                originalStroke.apply(this, arguments)
                ctx1.restore();
            }
        }
    });
    var chart1 = new Chart(ctx1).LineAlt(data1, {
        scaleShowGridLines: true,
        scaleGridLineWidth: 0,
        scaleShowHorizontalLines: true,
        scaleShowVerticalLines: true,
        bezierCurve: true,
        bezierCurveTension: 0.4,
        pointDot: true,
        pointDotRadius: 4,
        pointDotStrokeWidth: 2,
        pointHitDetectionRadius: 2,
        datasetStroke: true,
        tooltipCornerRadius: 2,
        datasetStrokeWidth: 0,
        datasetFill: false,
        legendTemplate: "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"background-color:<%=datasets[i].strokeColor%>\"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>",
        responsive: true
    });
    var ctx2 = document.getElementById("chart2").getContext("2d");
    var data2 = {
        labels: ["January", "February", "March", "April", "May", "June", "July"],
        datasets: [{
            label: "My First dataset",
            fillColor: "#ff3e43",
            strokeColor: "#ff3e43",
            highlightFill: "#ff3e43",
            highlightStroke: "#ff3e43",
            data: [10, 30, 80, 61, 26, 75, 40]
        }, {
            label: "My Second dataset",
            fillColor: "#0acae2",
            strokeColor: "#0acae2",
            highlightFill: "#0acae2",
            highlightStroke: "#0acae2",
            data: [28, 48, 40, 19, 86, 27, 90]
        }]
    };
    var chart2 = new Chart(ctx2).Bar(data2, {
        scaleBeginAtZero: true,
        scaleShowGridLines: true,
        scaleGridLineWidth: 0,
        scaleShowHorizontalLines: true,
        scaleShowVerticalLines: true,
        barShowStroke: true,
        barStrokeWidth: 0,
        tooltipCornerRadius: 2,
        barDatasetSpacing: 3,
        legendTemplate: "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"background-color:<%=datasets[i].fillColor%>\"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>",
        responsive: true
    });
    var ctx3 = document.getElementById("chart3").getContext("2d");
    var data3 = [{
        value: 300,
        color: "#0acae2",
        highlight: "#0acae2",
        label: "Info"
    }, {
        value: 50,
        color: "#edf1f5",
        highlight: "#edf1f5",
        label: "Light"
    }, {
        value: 50,
        color: "#4caf50",
        highlight: "#4caf50",
        label: "Success"
    }, {
        value: 50,
        color: "#ff3e43",
        highlight: "#ff3e43",
        label: "Danger"
    }, {
        value: 100,
        color: "#ffa200",
        highlight: "#ffa200",
        label: "Warning"
    }];
    var myPieChart = new Chart(ctx3).Pie(data3, {
        segmentShowStroke: true,
        segmentStrokeColor: "#fff",
        segmentStrokeWidth: 0,
        animationSteps: 100,
        tooltipCornerRadius: 0,
        animationEasing: "easeOutBounce",
        animateRotate: true,
        animateScale: false,
        legendTemplate: "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<segments.length; i++){%><li><span style=\"background-color:<%=segments[i].fillColor%>\"></span><%if(segments[i].label){%><%=segments[i].label%><%}%></li><%}%></ul>",
        responsive: true
    });
    var ctx4 = document.getElementById("chart4").getContext("2d");
    var data4 = [{
        value: 300,
        color: "#e91e63",
        highlight: "#e91e63",
        label: "Rose"
    }, {
        value: 80,
        color: "#0acae2",
        highlight: "#0acae2",
        label: "Info"
    }, {
        value: 100,
        color: "#4caf50",
        highlight: "#4caf50",
        label: "Success"
    }];
    var myDoughnutChart = new Chart(ctx4).Doughnut(data4, {
        segmentShowStroke: true,
        segmentStrokeColor: "#fff",
        segmentStrokeWidth: 0,
        animationSteps: 100,
        tooltipCornerRadius: 2,
        animationEasing: "easeOutBounce",
        animateRotate: true,
        animateScale: false,
        legendTemplate: "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<segments.length; i++){%><li><span style=\"background-color:<%=segments[i].fillColor%>\"></span><%if(segments[i].label){%><%=segments[i].label%><%}%></li><%}%></ul>",
        responsive: true
    });
    var ctx5 = document.getElementById("chart5").getContext("2d");
    var data5 = [{
        value: 300,
        color: "#e91e63",
        highlight: "#e91e63",
        label: "Rose"
    }, {
        value: 100,
        color: "#0acae2",
        highlight: "#0acae2",
        label: "Info"
    }, {
        value: 180,
        color: "#4caf50",
        highlight: "#4caf50",
        label: "Success"
    }, {
        value: 60,
        color: "#292929",
        highlight: "#292929",
        label: "Black"
    }];
    var myPolarArea = new Chart(ctx5).PolarArea(data5, {
        scaleShowLabelBackdrop: true,
        scaleBackdropColor: "rgba(255,255,255,0.75)",
        scaleBeginAtZero: true,
        scaleBackdropPaddingY: 2,
        scaleBackdropPaddingX: 2,
        scaleShowLine: true,
        segmentShowStroke: true,
        segmentStrokeColor: "#fff",
        segmentStrokeWidth: 2,
        animationSteps: 100,
        tooltipCornerRadius: 2,
        animationEasing: "easeOutBounce",
        animateRotate: true,
        animateScale: false,
        legendTemplate: "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<segments.length; i++){%><li><span style=\"background-color:<%=segments[i].fillColor%>\"></span><%if(segments[i].label){%><%=segments[i].label%><%}%></li><%}%></ul>",
        responsive: true
    });
    var ctx6 = document.getElementById("chart6").getContext("2d");
    var data6 = {
        labels: ["Site A", "Site B", "Site C", "Site D", "Site E", "Site F", "Site G"],
        datasets: [{
            label: "My First dataset",
            fillColor: "#0acae2",
            strokeColor: "#0acae2",
            pointColor: "#0acae2",
            pointStrokeColor: "#fff",
            pointHighlightFill: "#fff",
            pointHighlightStroke: "#0acae2",
            data: [65, 59, 90, 81, 56, 55, 40]
        }, {
            label: "My Second dataset",
            fillColor: "rgba(233, 30, 99, 0.72)",
            strokeColor: "rgba(233, 30, 99, 0.72)",
            pointColor: "rgba(233, 30, 99, 0.72)",
            pointStrokeColor: "#fff",
            pointHighlightFill: "#fff",
            pointHighlightStroke: "rgba(233, 30, 99, 0.72)",
            data: [28, 48, 40, 19, 96, 27, 100]
        }]
    };
    var myRadarChart = new Chart(ctx6).Radar(data6, {
        scaleShowLine: true,
        angleShowLineOut: true,
        scaleShowLabels: false,
        scaleBeginAtZero: true,
        angleLineColor: "rgba(0,0,0,.1)",
        angleLineWidth: 1,
        pointLabelFontFamily: "'Arial'",
        pointLabelFontStyle: "normal",
        pointLabelFontSize: 12,
        pointLabelFontColor: "#666",
        pointDot: true,
        pointDotRadius: 3,
        tooltipCornerRadius: 2,
        pointDotStrokeWidth: 1,
        pointHitDetectionRadius: 20,
        datasetStroke: true,
        datasetStrokeWidth: 2,
        datasetFill: true,
        legendTemplate: "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"background-color:<%=datasets[i].strokeColor%>\"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>",
        responsive: true
    });
});