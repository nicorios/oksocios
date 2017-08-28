$(document).ready(function(){

    var establishments;
    if(localStorage.ok_establishments){
        establishments = localStorage.getItem('ok_establishments');
        display(JSON.parse(establishments))
    }else{
        $.ajax({
            type : "GET",
            contentType : "application/json",
            url : "/establishments/ajax",
            success : function(data) {
                localStorage.setItem('ok_establishments', JSON.stringify(data));
                display(data);
            },
            error : function(e) {
                console.log("ERROR: ", e);
            },
            done : function(e) {
                console.log("DONE");
            }
        });
    }

    function display(array){
        console.log(array);
        var list = $('#establishments-list');
        for (var i in array) {
            var establishment = array[i];
            list.append('<li><a href="/get-entries?establishment='+ establishment.id +'">'+ establishment.name +'</a></li>')
        }
    }

});