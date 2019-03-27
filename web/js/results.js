/**
 * Created by pranita on 23/4/16.
 */

function addResult(action, result) {
    var html = '<div id="createModal" class="modal fade" role="dialog"><div class="modal-dialog"><!-- Modal content--><div class="modal-content"><div class="modal-header"><button type="button" class="close" data-dismiss="modal">&times;</button><h3 class="modal-title">' + action + ' Result</h3></div><div class="modal-body">' +
        '<div class="pad">' +
        '<label for="add_modal_name">Exam name</label>' +
        '<select name="add_modal_name" class="form-control" id="add_modal_name">' +
        '<option selected value=""></option>';

        for(var i = 0 ; i < exams.length ; i++) {
                html += '<option value="' + exams[i].examID +'">' + exams[i].name + '</option>'
        }

    html += '</select>' +

            // '<input id="add_modal_name" name="name" type="text" class="form-control" placeholder="" autofocus required>' +
        '</div>' +
        '<div class="pad"><label for="add_modal_details">Result</label><textarea id="add_modal_details" name="details" type="text" class="form-control"></textarea></div><div class="modal-footer"><button type="button" class="btn btn-success" id="createResultButton">' + action + '</button><button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button></div></div></div></div>';
    $('#' + action + 'ModalHere').html(html);
    $('#createResultButton').click(function () {
        sendPost(action, (action == "Update")? result['resultID']: null, $('#add_modal_name').val(), $('#add_modal_details').val());
    });
    if(action == "Update") {
        // $('#add_modal_name').val(result['examName']);
        $('#add_modal_name option[value=' + result.examID + ']').prop('selected', true);
        $('#add_modal_details').val(result['resultDetails']);
    }
    $('#createModal').modal();
}

function deleteResult(result) {
    
    var html =
        '<div id="deleteModal" class="modal fade" role="dialog"><div class="modal-dialog"><div class="modal-content"><div class="modal-header"><button type="button" class="close" data-dismiss="modal">&times;</button><h4 class="modal-title">Delete Result</h4></div><div class="modal-body"><p>Are you sure you want to delete result for exam <b>' + result['examName'] + '</b>? </p></div><div class="modal-footer"><button type="button" class="btn btn-danger" id="deleteResultButton" >Yes</button><button type="button" class="btn btn-primary" data-dismiss="modal">No</button></div></div></div></div>';

    $('#deleteModalHere').html(html);
    
    $('#deleteResultButton').click(function () {
        sendPost('delete', result['resultID']);
    });
    $('#deleteModal').modal();
}

function sendPost(action, resultID, name, details){
    console.log(action, resultID, name, details);
    // return;
    $.post("/results",
        {
            action: action,
            resultID: resultID,
            examID: name,
            result_details: details

        }, function (data, status) {
            if (data == "success") {
                location.reload();
            } else {
                $.toaster({priority: 'danger', title: 'Error', message: 'Failed. Please try again.'});
            }
        });
}

var exams;

function setExams(e) {
    exams = e;
}