/**
 * Created by pranita on 23/4/16.
 */

$(function () {
    $('#add_datetimepicker_event').datetimepicker({
        minDate: new Date()
    });
});

function updateEventView(val) {
    if ($('#' + val + '_modal_type').val() == "Event") {
        $('#' + val + '_datetimepicker_event_parent').show();
        $('#' + val + '_modal_venue_parent').show();
    } else {
        $('#' + val + '_datetimepicker_event_parent').hide();
        $('#' + val + '_modal_venue_parent').hide();
    }
}

function deleteAnnouncement(announcement) {

    var html =
        '<div id="deleteModal" class="modal fade" role="dialog">' +
        '<div class="modal-dialog">' +
        '<div class="modal-content">' +
        '<div class="modal-header">' +
        '<button type="button" class="close" data-dismiss="modal">&times;</button>' +
        '<h4 class="modal-title">Delete Announcement</h4>' +
        '</div>' +
        '<div class="modal-body">' +
        '<p>Are you sure you want to delete Announcement titled <b>' + announcement['title'] + '</b>?' +
        '</p>' +
        '</div>' +
        '<div class="modal-footer">' +
        "<button type='button' class='btn btn-danger' id='deleteAnnouncementButton' >Yes</button>" +
        '<button type="button" class="btn btn-primary" data-dismiss="modal">No</button>' +
        '</div>' +
        '</div>' +
        '</div>' +
        '</div>';

    $('#deleteModalHere').html(html);
    $('#deleteAnnouncementButton').click(function () {
        deleteActionAnnouncement(announcement['id']);
    });
    $('#deleteModal').modal();
}

function deleteActionAnnouncement(id) {
    sendPost("delete", id);
}

function editAnnouncement(announcement) {
    var html =
        '<div id="editModal" class="modal fade" role="dialog">' +
        '<div class="modal-dialog">' +
        '<div class="modal-content">' +
        '<div class="modal-header">' +
        '<button type="button" class="close" data-dismiss="modal">&times;</button>' +
        '<h3 class="modal-title">Edit Announcement</h3>' +
        '</div>' +
        '<div class="modal-body">' +
        '<div class="pad">' +
        '<label for="edit_modal_title">Title</label>' +
        '<input id="edit_modal_title" name="title" type="text" class="form-control" placeholder=""' +
        'autofocus required value="'+ announcement['title'] + '">' +
        '</div>' +
        '<div class="pad">' +
        '<label for="edit_modal_details">Details</label>' +
        '<textarea id="edit_modal_details" name="details" type="text"' +
        'class="form-control">' + announcement['details'] + '</textarea>' +
        '</div>' +
        '<div class="pad">' +
        '<label for="edit_modal_link">Link or Contact Info</label>' +
        '<input id="edit_modal_link" name="link" type="text" class="form-control"' +
        'placeholder="http://link.xyz" value="'+ announcement['link'] + '">' +
        '</div>' +
        '<div class="pad">' +
        '<label for="edit_modal_type">Announcement Type</label>' +
        '<select name="type" class="form-control" id="edit_modal_type" onchange="updateEventView(' + "'edit'" + ');"' +
        'required > <option ';
    
    if(announcement['type'] == "JOB")
    html += 'selected';
    
    html +=    '>Job Posting</option>' +
        '<option ';

    if(announcement['type'] == "EVENT")
        html+='selected';
    
    html +=   '>Event</option>' +
        '<option ';
    
    if(announcement['type'] == "NEWS")
        html+='selected';
    
    html += '>News</option>' +
        '</select>' +
        '</div>' +
        '<div class="pad" id="edit_datetimepicker_event_parent" hidden>' +
        '<label for="edit_datetimepicker_event">Event Date and Time</label>' +
        '<div class="input-group date" id="edit_datetimepicker_event">' +
        '<input type="text" class="form-control"/> <span class="input-group-addon"><span' +
        'class="glyphicon-calendar glyphicon"></span></span>' +
        '</div>' +
        '</div>' +
        '<div class="pad" id="edit_modal_venue_parent" hidden>' +
        '<label for="edit_modal_venue">Event Venue</label>' +
        '<input id="edit_modal_venue" name="venue" type="text" class="form-control"' +
        'placeholder="" value="'+ announcement['eventVenue'] + '">' +
        '</div>' +
        '</div>' +
        '<div class="modal-footer">' +
        '<button type="button" class="btn btn-success" id="editSaveAnnouncementButton">Update</button>' +
        '<button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>' +
        '</div>' +
        '</div>' +
        '</div>' +
        '</div>';

    $('#editModalHere').html(html);
    $('#editSaveAnnouncementButton').click(function () {
        sendPost("update", announcement['id'], $('#edit_modal_title').val(), $('#edit_modal_details').val(),
            $('#edit_modal_link').val(), $('#edit_modal_type').val(), $('#edit_datetimepicker_event').data().date, $('#edit_modal_venue').val());
    });

    $('#edit_datetimepicker_event').datetimepicker({
        defaultDate: announcement['eventDatetime']
    });

    $('#editModal').modal();
    updateEventView("edit");
}

function saveAnnouncement() {
    sendPost("save", null, $('#add_modal_title').val(), $('#add_modal_details').val(),
        $('#add_modal_link').val(), $('#add_modal_type').val(), $('#add_datetimepicker_event').data().date, $('#add_modal_venue').val());
}

function sendPost(action_r, id_r, title_r, details_r, link_r, type_r, event_date_r, event_venue_r) {
    console.log(action_r, title_r, details_r, link_r, type_r, event_date_r, event_venue_r);
    $.post("/announcements",
        {
            id: id_r,
            action: action_r,
            title: title_r,
            details: details_r,
            link: link_r,
            announcement_type: type_r,
            event_datetime: event_date_r,
            event_venue: event_venue_r

        }, function (data, status) {
            if (data == "success") {
                location.reload();
            } else {
                $.toaster({priority: 'danger', title: 'Error', message: 'Failed. Please try again.'});
            }
        });
}