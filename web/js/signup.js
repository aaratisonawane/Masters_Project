// function validateForm() {
//     var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
//     return re.test(document.loginForm.)
// }
var firstTime = true;
function updateDisplay() {
    if ($('#role').val().toString().trim() == "Student") {
        $('#department').show();
        $('#start_sem').show();
        $('#program').show();
    } else {
        $('#department').hide();
        $('#start_sem').hide();
        $('#program').hide();
    }
    
    if (firstTime) {
        firstTime = false;
        $('#errors').hide();
    }
}

function passwordError() {
    if ($('#pass').val() != $('#cnfPass').val()) {
        $('#passParent').addClass("has-error");
        $('#cnfPassParent').addClass("has-error");
        $('#pass_error').show();
    } else {
        if ($('#passParent').hasClass("has-error")) {
            $('#passParent').removeClass("has-error");
            $('#cnfPassParent').removeClass("has-error");

            $('#passParent').addClass("has-success");
            $('#cnfPassParent').addClass("has-success");

        }
        $('#pass_error').hide();
    }
}

function validateForm() {
    var error_msg = "";
    if($('#pass').val() != $('#cnfPass').val()) {
        $('#passParent').addClass("has-error");
        error_msg += "Passwords do not match.<br/>";
    }
    if($('#netid').val().trim() == ""){
        $('#netid_parent').addClass("has-error");
        error_msg += "NetID can't be empty.<br/>";
    }
    if($('#firstName').val().trim() == "" && ($('#lastName').val().trim() == "")){
        $('#name_parent').addClass("has-error");
        error_msg += "Name can't be empty.<br/>";
    }
    if($('#role').val().trim() == ""){
        error_msg += "Please select your role.<br/>";
    }
    if($('#start_year').val().trim() == "" && $('#role').val().toString().trim() == "Student"){
        error_msg += "Start year can't be empty.<br/>";
    }
    if($('#dept').val().trim() == "" && $('#role').val().toString().trim() == "Student"){
        error_msg += "Please select your department.<br/>";
    }
    if($('#prgm').val().trim() == "" && $('#role').val().toString().trim() == "Student"){
        error_msg += "Please select your program.<br/>";
    }

    if(error_msg != "") {
        $('#errors').show();
        $('#error_msg').html(error_msg);
        console.log(error_msg);
        event.preventDefault();
    }
}