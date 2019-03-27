/**
 * Created by pranita on 23/4/16.
 */

function deleteDiscussion(discussion) {

    var html =
        '<div id="deleteModal" class="modal fade" role="dialog">' +
        '<div class="modal-dialog">' +
        '<div class="modal-content">' +
        '<div class="modal-header">' +
        '<button type="button" class="close" data-dismiss="modal">&times;</button>' +
        '<h4 class="modal-title">Delete Discussion</h4>' +
        '</div>' +
        '<div class="modal-body">' +
        '<p>Are you sure you want to delete discussion titled <b>' + discussion['title'] + '</b>?' +
        '</p>' +
        '</div>' +
        '<div class="modal-footer">' +
        "<button type='button' class='btn btn-danger' id='deleteDiscussionButton' >Yes</button>" +
        '<button type="button" class="btn btn-primary" data-dismiss="modal">No</button>' +
        '</div>' +
        '</div>' +
        '</div>' +
        '</div>';

    $('#deleteModalHere').html(html);
    $('#deleteDiscussionButton').click(function () {
        deleteActionDiscussion(discussion['id'], discussion['type']);
    });
    $('#deleteModal').modal();
}

function deleteActionDiscussion(id, type) {
    sendPost("delete", id, null, null, type);
}

function editDiscussion(discussion) {
    var html =
        '<div id="editModal" class="modal fade" role="dialog">' +
        '<div class="modal-dialog">' +
        '<div class="modal-content">' +
        '<div class="modal-header">' +
        '<button type="button" class="close" data-dismiss="modal">&times;</button>' +
        '<h3 class="modal-title">Edit Discussion</h3>' +
        '</div>' +
        '<div class="modal-body">' +
        '<div class="pad">' +
        '<label for="edit_modal_title">Title</label>' +
        '<input id="edit_modal_title" name="title" type="text" class="form-control" placeholder=""' +
        'autofocus required value="'+ discussion['title'] + '">' +
        '</div>' +
        '<div class="pad">' +
        '<label for="edit_modal_details">Details</label>' +
        '<textarea id="edit_modal_details" name="details" type="text"' +
        'class="form-control">' + discussion['details'] + '</textarea>' +
        '</div>' +
        '<div class="modal-footer">' +
        '<button type="button" class="btn btn-success" id="editSaveDiscussionButton">Update</button>' +
        '<button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>' +
        '</div>' +
        '</div>' +
        '</div>' +
        '</div>';

    $('#editModalHere').html(html);
    $('#editSaveDiscussionButton').click(function () {
        sendPost("update", discussion['id'], $('#edit_modal_title').val(), $('#edit_modal_details').val());
    });

    $('#editModal').modal();
}

function saveDiscussion(type, discussion_id) {
    sendPost("save", null, $('#add_modal_title').val(), $('#add_modal_details').val(), type, discussion_id);
}

function sendPost(action_r, id_r, title_r, details_r, type_r, discussion_id_r){ 
    $.post("/discussions",
        {
            id: id_r,
            action: action_r,
            title: title_r,
            details: details_r,
            type: type_r,
            discussion_id: discussion_id_r

        }, function (data, status) {
            if (data == "success") {
                if(type_r == "TOPIC")
                    location.href = "/discussions";
                else
                    location.reload();
            } else {
                $.toaster({priority: 'danger', title: 'Error', message: 'Failed. Please try again.'});
            }
        });
}