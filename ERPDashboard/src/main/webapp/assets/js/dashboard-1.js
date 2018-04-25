
           $(function () {


            "use strict";

                         // Extra chart
             Morris.Area({
                    element: 'dash1Chart1',
                    data: [{
                                period: '2010',
                                iphone: 0,
                                ipad: 0,
                                itouch: 0
                            }, {
                                period: '2011',
                                iphone: 100,
                                ipad: 20,
                                itouch: 40
                            }, {
                                period: '2012',
                                iphone: 20,
                                ipad: 140,
                                itouch: 10
                            }, {
                                period: '2013',
                                iphone: 100,
                                ipad: 20,
                                itouch: 15
                            }, {
                                period: '2014',
                                iphone: 30,
                                ipad: 20,
                                itouch: 120
                            }, {
                                period: '2015',
                                iphone: 25,
                                ipad: 80,
                                itouch: 40
                            }, {
                                period: '2016',
                                iphone: 10,
                                ipad: 10,
                                itouch: 10
                            }


                            ],
                            lineColors: ['#ffa200', '#0acae2', '#ff3e43'],
                            xkey: 'period',
                            ykeys: ['iphone', 'ipad', 'itouch'],
                            labels: ['Site A', 'Site B', 'Site C'],
                            pointSize: 0,
                            lineWidth: 0,
                            resize:true,
                            fillOpacity: 0.8,
                            behaveLikeLine: true,
                            gridLineColor: '#e0e0e0',
                            hideHover: 'auto'
                    
                });


           //Flot Pie Chart
                  Morris.Donut({
                        element: 'dash1Chart2',
                        data: [{
                            label: "Apple",
                            value: 80,

                        }, {
                            label: "Redme",
                            value: 30
                        }, {
                            label: "Nokia",
                            value: 20
                        }],
                        resize: true,
                        colors:['#0acae2', '#ffa200', '#ff3e43']
                    });

              // Extra chart
                Morris.Area({
                    element: 'dash1Chart3',
                    data: [{
                        period: '2010',
                        SiteA: 0,
                        SiteB: 0,
                        
                    }, {
                        period: '2011',
                        SiteA: 130,
                        SiteB: 100,
                        
                    }, {
                        period: '2012',
                        SiteA: 80,
                        SiteB: 60,
                        
                    }, {
                        period: '2013',
                        SiteA: 70,
                        SiteB: 200,
                        
                    }, {
                        period: '2014',
                        SiteA: 180,
                        SiteB: 150,
                        
                    }, {
                        period: '2015',
                        SiteA: 105,
                        SiteB: 90,
                        
                    },
                     {
                        period: '2016',
                        SiteA: 250,
                        SiteB: 150,
                       
                    }],
                    xkey: 'period',
                    ykeys: ['SiteA', 'SiteB'],
                    labels: ['Site A', 'Site B'],
                    pointSize: 0,
                    fillOpacity: .2,
                    pointStrokeColors:['#0acae2', '#e91e63'],
                    behaveLikeLine: true,
                    gridLineColor: '#e0e0e0',
                    lineWidth: 2,
                    smooth: false,
                    hideHover: 'auto',
                    lineColors: ['#0acae2', '#e91e63'],
                    resize: true
                    
                });

                $('.dash1Chart4').easyPieChart({
                    easing: 'easeOutBounce',
                    barColor : '#6ae86f',
                    lineWidth: 3,
                    animate: 1000,
                    lineCap: 'round',
                    trackColor: '#e5e5e5',
                    onStep: function(from, to, percent) {
                        $(this.el).find('.percent').text(Math.round(percent));
                    }
                });

                $('.dash1Chart5').easyPieChart({
                    easing: 'easeOutBounce',
                    barColor : '#ffeb3b',
                    lineWidth: 3,
                    animate: 1000,
                    lineCap: 'square',
                    trackColor: '#e5e5e5',
                    onStep: function(from, to, percent) {
                        $(this.el).find('.percent').text(Math.round(percent));
                    }
                });

                $('.dash1Chart6').easyPieChart({
                    easing: 'easeOutBounce',
                    barColor : '#fa39ff',
                    lineWidth: 3,
                    animate: 1000,
                    lineCap: 'square',
                    trackColor: '#e5e5e5',
                    onStep: function(from, to, percent) {
                        $(this.el).find('.percent').text(Math.round(percent));
                    }
                });

                $('.dash1Chart7').easyPieChart({
                    easing: 'easeOutBounce',
                    barColor : '#55c2ff',
                    lineWidth: 3,
                    animate: 1000,
                    lineCap: 'square',
                    trackColor: '#e5e5e5',
                    onStep: function(from, to, percent) {
                        $(this.el).find('.percent').text(Math.round(percent));
                    }
                });

                 //Flot Line Chart
            $(document).ready(function () {
                console.log("document ready");
                var offset = 0;
                plot();

                function plot() {
                    var sin = []
                        , cos = [];
                    for (var i = 0; i < 12; i += 0.2) {
                        sin.push([i, Math.sin(i + offset)]);
                        cos.push([i, Math.cos(i + offset)]);
                    }
                    var options = {
                        series: {
                            lines: {
                                show: true
                            }
                            , points: {
                                show: true
                            }
                        }
                        , grid: {
                            hoverable: true //IMPORTANT! this is needed for tooltip to work
                        }
                        , yaxis: {
                            min: -1.2
                            , max: 1.2
                        }
                        , colors: ["#ff3e43", "#ffa200"]
                        , grid: {
                            color: "#AFAFAF"
                            , hoverable: true
                            , borderWidth: 0
                            , backgroundColor: '#FFF'
                        }
                        , tooltip: true
                        , tooltipOpts: {
                            content: "'%s' of %x.1 is %y.4"
                            , shifts: {
                                x: -60
                                , y: 25
                            }
                        }
                    };
                    var plotObj = $.plot($("#dash1Chart8"), [{
                        data: sin
                        , label: "sin(x)"
                    , }, {
                        data: cos
                        , label: "cos(x)"
                        }], options);
                }
            });


        }); 