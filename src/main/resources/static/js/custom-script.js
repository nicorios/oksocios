/*================================================================================
	Item Name: Materialize - Material Design Admin Template
	Version: 3.1
	Author: GeeksLabs
	Author URL: http://www.themeforest.net/user/geekslabs
================================================================================

NOTE:
------
PLACE HERE YOUR OWN JS CODES AND IF NEEDED.
WE WILL RELEASE FUTURE UPDATES SO IN ORDER TO NOT OVERWRITE YOUR CUSTOM SCRIPT IT'S BETTER LIKE THIS. */

function objectifyForm(formArray) {

    var returnArray = {};
    formArray.forEach(function(input, i){
        returnArray[formArray[i]['name']] = formArray[i]['value'];
    });
    return returnArray;
};

// $(function () {
//     var token = $("input[name='_csrf']").val();
//     var header = "X-CSRF-TOKEN";
//     $(document).ajaxSend(function(e, xhr, options) {
//         xhr.setRequestHeader(header, token);
//     });
// });