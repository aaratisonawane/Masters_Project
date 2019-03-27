var firstTime = true;
function updateDisplay() {
    if (firstTime) {
        firstTime = false;
        $('#errors').hide();
    }
}
function validateForm() {
    var error_msg = "";
    if($('#course_num').val().trim() == ""){
        $('#course_num_parent').addClass("has-error");
        error_msg += "Course number can't be empty.<br/>";
    }
    if($('#course_name').val().trim() == ""){
        $('#course_name_parent').addClass("has-error");
        error_msg += "Course name can't be empty.<br/>";
    }

    if(error_msg != "") {
        $('#errors').show();
        $('#error_msg').html(error_msg);
        console.log(error_msg);
        event.preventDefault();
    }
}