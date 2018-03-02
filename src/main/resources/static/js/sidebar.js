$(document).ready(function(){

    $.ajax({
        type : "GET",
        contentType : "application/json",
        url : "/establishments/ajax",
        success : function(data) {
            display(data);
        },
        error : function(e) {
            console.log("ERROR: ", e);
        },
        done : function(e) {
            console.log("DONE");
        }
    });

    function display(array){
        console.log(array);
        var list = $('#establishments-list');
        for (var i in array) {
            var establishment = array[i];
            list.prepend('<li><a href="/get-entries?establishment='+ establishment.id +'">'+ establishment.name +'</a></li>')
        }
    }

});