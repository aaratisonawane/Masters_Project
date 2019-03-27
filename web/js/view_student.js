/**
 * Created by pranita on 22/4/16.
 */
function getStatus(term, year) {
    var current_year = new Date().getYear();
    return (current_year - year);
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

function updateStudent() {
    $.post("/view_student",
        {
            netID: $('#netID').val(),
            firstName: $('#firstName').val(),
            lastName: $('#lastName').val(),
            oldPass: $('#oldPass').val(),
            newPass: $('#pass').val(),
            phoneNumber: $('#phoneNumber').val(),
            advisor: $('#advisor_firstName').val()

        }, function (data, status) {
            if (data == "success") {
                location.reload();
            } else {
                $.toaster({priority: 'danger', title: 'Error', message: 'Failed. Please try again.'});
            }
        });
}
