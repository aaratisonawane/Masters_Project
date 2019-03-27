/**
 * Created by pranita on 22/4/16.
 */

function deleteAlumni(alumni) {

    var html =
        '<div id="deleteModal" class="modal fade" role="dialog">' +
        '<div class="modal-dialog">' +
        '<div class="modal-content">' +
        '<div class="modal-header">' +
        '<button type="button" class="close" data-dismiss="modal">&times;</button>' +
        '<h4 class="modal-title">Delete Alumni</h4>' +
        '</div>' +
        '<div class="modal-body">' +
        '<p>Are you sure you want to delete Alumni <b>' + alumni['name'] + '</b>?' +
        '</p>' +
        '</div>' +
        '<div class="modal-footer">' +
        "<button type='button' class='btn btn-danger' id='deleteAlumniButton' >Yes</button>" +
        '<button type="button" class="btn btn-primary" data-dismiss="modal">No</button>' +
        '</div>' +
        '</div>' +
        '</div>' +
        '</div>';

    $('#deleteModalHere').html(html);
    $('#deleteAlumniButton').click(function () {
        deleteActionAlumni(alumni['name']);
    });
    $('#deleteModal').modal();
}

function deleteActionAlumni(alumni_name) {
    sendPost("delete", alumni_name);
}

function editAlumni(alumni) {
    var html =
        '<div id="editModal" class="modal fade" role="dialog">' +
        '<div class="modal-dialog">' +
        '<!-- Modal content-->' +
        '<div class="modal-content">' +
        '<div class="modal-header">' +
        '<button type="button" class="close" data-dismiss="modal">&times;</button>' +
        '<h4 class="modal-title">Alumni</h4>' +
        '</div>' +
        '<div class="modal-body">' +
        '<p>' +
        '<label for="edit_modal_name">Name</label>' +
        '<input id="edit_modal_name" name="name" type="text" class="form-control" placeholder=""' +
        'autofocus value="' + alumni['name'] + '">' +
        '<label for="edit_modal_homepage">Homepage</label>' +
        '<input id="edit_modal_homepage" name="homepage" type="text" class="form-control"' +
        'placeholder="http://alumni.xyz" value=' + alumni['homepage'] + '>' +
        '<label for="edit_modal_description">Description</label>' +
        '<textarea id="edit_modal_description" name="description" type="text"' +
        'class="form-control">' + alumni['description'] + '</textarea>' +
        '<label for="edit_modal_image">Image</label>' +
        '<input id="edit_modal_image" name="image" type="text" class="form-control"' +
        'placeholder="http://alumni.xyz/logo.png" value=' + alumni['image'] + '>' +
        '</p>' +
        '</div>' +
        '<div class="modal-footer">' +
        '<button type="button" class="btn btn-success" id="editSaveAlumniButton">Update</button>' +
        '<button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>' +
        '</div>' +
        '</div>' +
        '</div>' +
        '</div>';

    $('#editModalHere').html(html);
    $('#editModal').modal();
    $('#editSaveAlumniButton').click(function () {
        sendPost("update", $('#edit_modal_name').val(), $('#edit_modal_homepage').val(),
            $('#edit_modal_description').val(), $('#edit_modal_image').val(), alumni['name']);
    });
}

function saveAlumni() {
    sendPost("save", $('#add_modal_name').val(), $('#add_modal_homepage').val(),
        $('#add_modal_description').val(), $('#add_modal_image').val());
}

function sendPost(action_r, name_r, homepage_r, description_r, image_r, old_name_r) {
    $.post("/alumni",
        {
            action: action_r,
            name: name_r,
            homepage: homepage_r,
            description: description_r,
            image: image_r,
            old_name: old_name_r

        }, function (data, status) {
            if (data == "success") {
                location.reload();
            } else {
                $.toaster({priority: 'danger', title: 'Error', message: 'Failed. Please try again.'});
            }
        });
}