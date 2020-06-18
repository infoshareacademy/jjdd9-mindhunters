$( document ).ready(function(){
    $.getJSON('/api/stats/drinks/category', function (data1) {
        console.log(data1);



    });




    $.getJSON('/api/stats/categories', function (data2) {
        console.log(data2)
    });




    $.getJSON('/api/stats/drinks/top-10', function (data3) {
        console.log(data3)

        let drinkAndQuantityArray = [];
        for (let i = 0; i < 1; i++) {
            let pairDrinkQuantity = data3[0];
            console.log(pairDrinkQuantity[0]);
            drinkAndQuantityArray = [pairDrinkQuantity.get(0),
                pairDrinkQuantity.get(1)];
            console.log(drinkAndQuantityArray);


        }

        xLabels.push(drinkAndQuantityArray);
        var ctx = document.getElementById("myChart").getContext("2d");

    });



});


var ctx = document.getElementById('myChart');
const xLabels = [];
var myChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: xLabels,
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


/*




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
