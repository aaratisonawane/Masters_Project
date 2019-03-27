/**
 * Created by pranita on 23/4/16.
 */


function enroll(index, exam) {
    var should = $('#enroll' + index).text().toString().trim() == "Enroll";
    $.post("/exams", {
            action: should?"enroll" : "drop",
            examID: exam['examID']
        },
        function(data, status){
            if(data == "success") {

                if (should) {
                    $('#enroll' + index).removeClass("btn-primary");
                    $('#enroll' + index).addClass("btn-success");
                    $('#enroll' + index).text("Enrolled");  
                } else {
                    $('#enroll' + index).removeClass("btn-success").removeClass("btn-danger");
                    $('#enroll' + index).addClass("btn-primary");
                    $('#enroll' + index).text("Enroll");
                }
            } else {
                $.toaster({ priority : 'danger', title : 'Error', message : 'Failed, Please try again.'});
            }
        });
}


function makeDrop(index) {
    if($('#enroll' + index).text().toString().trim() == "Enrolled")
        $('#enroll' + index).text('Drop').removeClass('btn-success').addClass('btn-danger');
}

function makeEnrolled(index){
    if($('#enroll' + index).text().toString().trim() == "Drop")
        $('#enroll' + index).text('Enrolled').removeClass('btn-danger').addClass('btn-success');
}


function addExam(action, exam) {
    var html = '<div id="createModal" class="modal fade" role="dialog"><div class="modal-dialog"><!-- Modal content--><div class="modal-content"><div class="modal-header"><button type="button" class="close" data-dismiss="modal">&times;</button><h3 class="modal-title">' + action + ' Exam</h3></div><div class="modal-body"><div class="pad"><label for="add_modal_name">Exam name</label><input id="add_modal_name" name="name" type="text" class="form-control" placeholder="" autofocus required></div><div class="pad"><label for="add_modal_details">Additional Details</label><textarea id="add_modal_details" name="details" type="text" class="form-control"></textarea></div><div><label>Exam date: </label><br/><input id="date" type="date"><br><br></div></div><div class="modal-footer"><button type="button" class="btn btn-success" id="createExamButton">' + action + '</button><button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button></div></div></div></div>';
    $('#' + action + 'ModalHere').html(html);
    $('#createExamButton').click(function () {
        sendPost(action, (action == "Update")? exam['examID']: null, $('#add_modal_name').val(), $('#date').val(), $('#add_modal_details').val());
    });
    $('#date').attr("min", new Date().toISOString().slice(0, 10));
    if(action == "Update") {
        $('#add_modal_name').val(exam['name']);
        $('#date').val(exam['dateOfExam']);
        $('#add_modal_details').val(exam['additionalDetails']);
    }
    $('#createModal').modal();
}

function deleteExam(exam) {
    
    var html =
        '<div id="deleteModal" class="modal fade" role="dialog"><div class="modal-dialog"><div class="modal-content"><div class="modal-header"><button type="button" class="close" data-dismiss="modal">&times;</button><h4 class="modal-title">Delete Exam</h4></div><div class="modal-body"><p>Are you sure you want to delete exam <b>' + exam['name'] + '</b>? </p></div><div class="modal-footer"><button type="button" class="btn btn-danger" id="deleteExamButton" >Yes</button><button type="button" class="btn btn-primary" data-dismiss="modal">No</button></div></div></div></div>';

    $('#deleteModalHere').html(html);
    
    $('#deleteExamButton').click(function () {
        sendPost('delete', exam['examID']);
    });
    $('#deleteModal').modal();
}

function sendPost(action_r, examID, name, date_of_exam, details){
    $.post("/exams",
        {
            action: action_r,
            examID: examID,
            name: name,
            date_of_exam: date_of_exam,
            additional_details: details

        }, function (data, status) {
            if (data == "success") {
                location.reload();
            } else {
                $.toaster({priority: 'danger', title: 'Error', message: 'Failed. Please try again.'});
            }
        });
}