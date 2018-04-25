 

 $(document).ready(function() {

    $("#dash2Chart1").sparkline([2,4,4,6,8,5,6,4,8,6,6,2 ], {
            type: 'line',
            width: '100%',
            height: '60',
            lineColor: '#0ab2c7',
            fillColor: 'rgba(19, 218, 254, 0.3)',
            lineWidth: 2,
            spotColor: '#0ab2c7',
            minSpotColor: '#0ab2c7',
            maxSpotColor: '#0ab2c7',
            highlightLineColor: '#0ab2c7',
            highlightSpotColor: '#0ab2c7'
        });

    $("#dash2Chart2").sparkline([0,2,8,6,8,5,6,4,8,6,6,2 ], {
            type: 'line',
            width: '100%',
            height: '60',
            lineColor: '#3f9a43',
            fillColor: 'rgba(25, 234, 10, 0.45)',
            lineWidth: 2,
            spotColor: '#3f9a43',
            minSpotColor: '#3f9a43',
            maxSpotColor: '#3f9a43',
            highlightLineColor: '#3f9a43',
            highlightSpotColor: '#3f9a43'
        });

        $("#dash2Chart3").sparkline([2,4,4,6,8,5,6,4,8,6,6,2], {
            type: 'line',
            width: '100%',
            height: '60',
            lineColor: '#7c218c',
            fillColor: 'rgb(228, 117, 247)', 
            lineWidth: 2,
            spotColor: '#7c218c',
            minSpotColor: '#7c218c',
            maxSpotColor: '#7c218c',
            highlightLineColor: '#7c218c',
            highlightSpotColor: '#7c218c'
        });

        $("#dash2Chart4").sparkline([2,4,4,6,8,5,6,4,8,6,6,2], {
            type: 'line',
            width: '100%',
            height: '60',
            lineColor: '#e89402',
            fillColor: 'rgba(255, 162, 0, 0.58)',
            lineWidth: 2,
            spotColor: '#e89402',
            minSpotColor: '#e89402',
            maxSpotColor: '#e89402',
            highlightLineColor: '#e89402',
            highlightSpotColor: '#e89402'
        });


        // Morris bar chart
    Morris.Bar({
        element: 'dash2Chart5',
        data: [{
            y: '2006',
            a: 100,
            b: 90,
            c: 60
        }, {
            y: '2007',
            a: 75,
            b: 65,
            c: 40
        }, {
            y: '2008',
            a: 50,
            b: 40,
            c: 30
        }, {
            y: '2009',
            a: 75,
            b: 65,
            c: 40
        }, {
            y: '2010',
            a: 50,
            b: 40,
            c: 30
        }, {
            y: '2011',
            a: 75,
            b: 65,
            c: 40
        }, {
            y: '2012',
            a: 100,
            b: 90,
            c: 40
        }],
        xkey: 'y',
        ykeys: ['a', 'b', 'c'],
        labels: ['A', 'B', 'C'],
        barColors:['#0acae2', '#292929', '#ffa200'],
        hideHover: 'auto',
        gridLineColor: '#eef0f2',
        resize: true
    });


     // Morris donut chart
    
        Morris.Donut({
            element: 'dash2Chart6',
            data: [{
                label: "T-Shirts",
                value: 100,

            }, {
                label: "Jeans",
                value: 70
            }, {
                label: "Shoes",
                value: 80
            }],
            resize: true,
            colors:['#0acae2', '#ffa200', '#9c27b0']
        });


    // Morris area chart
        Morris.Area({
           element: 'dash2Chart7',
             data: [{
                period: '2010',
                iphone: 0,
                macbook: 0,
                ipad: 0
            }, {
                period: '2011',
                iphone: 150,
                macbook: 20,
                ipad: 80
            }, {
                period: '2012',
                iphone: 20,
                macbook: 200,
                ipad: 10
            }, {
                period: '2013',
                iphone: 120,
                macbook: 20,
                ipad: 15
            }, {
                period: '2014',
                iphone: 30,
                macbook: 20,
                ipad: 150
            }, {
                period: '2015',
                iphone: 160,
                macbook: 100,
                ipad: 40
            }, {
                period: '2016',
                iphone: 0,
                macbook: 0,
                ipad: 0
            }


            ],
            lineColors: ['#ffa200', '#0acae2', '#e91e63'],
            xkey: 'period',
            ykeys: ['iphone', 'macbook', 'ipad'],
            labels: ['iphone', 'macbook', 'ipad'],
            pointSize: 0,
            lineWidth: 1,
            resize:true,
            fillOpacity: 0.6,
            behaveLikeLine: true,
            gridLineColor: '#e0e0e0',
            hideHover: 'auto'
    
      });

        $('#dash2Chart8').sparkline([ 0, 5, 6, 10, 9, 12, 4, 9,15,10,12,20,12,6], {
            type: 'bar',
            height: '80',
            barWidth: '4',
            resize: true,
            barSpacing: '6',
            barColor: '#ff6a9c'
        });

        $('#dash2Chart9').sparkline([20, 40, 30], {
            type: 'pie',
            height: '80',
            resize: true,
            sliceColors: ['#1f5f67', '#7dd4fb', '#f1f2f7']
        });

        $("#dash2Chart10").sparkline([2,4,4,6,8,5,6,4,8,6,6,2 ], {
            type: 'line',
            width: '100%',
            height: '50',
            lineColor: '#37424e',
            fillColor: '#37424e',
            maxSpotColor: '#4caf50',
            highlightLineColor: 'rgba(0, 0, 0, 0.2)',
            highlightSpotColor: '#37424e'
        });



 });






 