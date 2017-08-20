
 //Sampel Bar Chart
 var BarChartSampleData = {
    labels: ["January", "February", "March", "April", "May", "June", "July"],
    datasets: [
        {
            label: "My First dataset",
            fillColor: "rgba(220,220,220,0.5)",
            strokeColor: "rgba(220,220,220,0.8)",
            highlightFill: "rgba(220,220,220,0.75)",
            highlightStroke: "rgba(220,220,220,1)",
            data: [10, 59, 80, 81, 56, 55, 40]
        },
        {
            label: "My Second dataset",
            fillColor: "rgba(151,187,205,0.5)",
            strokeColor: "rgba(151,187,205,0.8)",
            highlightFill: "rgba(151,187,205,0.75)",
            highlightStroke: "rgba(151,187,205,1)",
            data: [98, 48, 40, 19, 86, 27, 90]
        }
    ]
};

 window.onload = function() {


  
  window.BarChartSample = new Chart(document.getElementById("bar-chart-sample").getContext("2d")).Bar(BarChartSampleData,{
   responsive:true
  });

 };
 