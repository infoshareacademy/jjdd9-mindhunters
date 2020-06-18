$( document ).ready(function(){
    $.getJSON('/api/stats/drinks/category', function (dataset1) {
        console.log(dataset1)
    });
});





/*

    var ctx = document.getElementById("myChart").getContext("2d");


    // draw empty chart
    var myChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: [],
            datasets: [
                {
                    label: "My First dataset",
                    fill: false,
                    lineTension: 0.1,
                    backgroundColor: "rgba(75,192,192,0.4)",
                    borderColor: "rgba(75,192,192,1)",
                    borderCapStyle: 'butt',
                    borderDash: [],
                    borderDashOffset: 0.0,
                    borderJoinStyle: 'miter',
                    pointBorderColor: "rgba(75,192,192,1)",
                    pointBackgroundColor: "#fff",
                    pointBorderWidth: 1,
                    pointHoverRadius: 5,
                    pointHoverBackgroundColor: "rgba(75,192,192,1)",
                    pointHoverBorderColor: "rgba(220,220,220,1)",
                    pointHoverBorderWidth: 2,
                    pointRadius: 1,
                    pointHitRadius: 10,
                    data: [],
                    spanGaps: false,
                }
            ]
        },
        options: {
            tooltips: {
                mode: 'index',
                intersect: false
            },
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero:true
                    }
                }]
            }
        }
    });

    ajax_chart(myChart, json_url);

    // function to update our chart
    function ajax_chart(chart, url, data) {
        var data = data || {};

        $.getJSON('/drinks/category', function (dataset1) {
            console.log(dataset1)
        }







/!*
            chart.data.labels = response.labels;
            chart.data.datasets[0].data = response.data.quantity; // or you can iterate for multiple datasets
            chart.update(); // finally update our chart*!/
        });
    }
});


*/


/*


function drawChart() {

    var ctx = document.getElementById("myChart").getContext("2d");

    var myChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
            datasets: [{
                label: '# of Votes',
                data: [12, 19, 3, 5, 2, 3],
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(255, 159, 64, 0.2)'
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    });


/!*


    $.getJSON('/drinks/category', function (dataset1) {
        let categoriesAndQuantitiesArrayChart1 = [['Category Name', 'Quantity',],];









        /!*    for (let i = 0; i < dataset1.length; i++) {
                let pairCategoryQuantity = dataset1[i];
                let categoryAndQuantityArray = [pairCategoryQuantity.categoryName,
                    pairCategoryQuantity.quantity];
                categoriesAndQuantitiesArrayChart1.push(categoryAndQuantityArray);
            }

            let dataChart1 = google.visualization.arrayToDataTable(
                categoriesAndQuantitiesArrayChart1);

            var optionsChart1 = {
                'title': 'Categories Ranking',
                is3D: true
            };*!/

        let chart1 = new google.visualization.PieChart(
            document.getElementById('piechart1'));

        chart1.draw(dataChart1, optionsChart1);
    });
};*!/
*/
