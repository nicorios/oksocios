$(document).ready(function () {

    var users = [];
    $.ajax({
        type : "GET",
        contentType : "application/json",
        url : "/customers/ajax",
        success : function(data) {
            users = display(data);
            createAutocomplete(users);
        },
        error : function(e) {
            console.log("ERROR: ", e);
        },
        done : function(e) {
            console.log("DONE");
        }
    });
});

function createAutocomplete(users){
    var options = {
        lookup: users,
        onSelect: function (suggestion) {
            window.location.href = '/customers/' + suggestion.data;
        }
    };
    $('#search-bar').autocomplete(options);
}

function display(array){
    var users = [];
    for (var i in array) {
       var aux = array[i];
       var user = {
           value: aux.name + ' ' + aux.lastName,
           data: aux.id
       };
       users.push(user);
    };
    return users;
}

