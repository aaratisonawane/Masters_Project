/**
 * Created by pranita on 22/4/16.
 */
$(function () {
    $('#keywords').tablesorter();
});

function showModal(title, content, buttonPositive, buttonNegative) {

    var html = '<div id="myModal" class="modal fade" role="dialog">' +
        '<div class="modal-dialog">' +
        '<!-- Modal content-->' +
        '<div class="modal-content">' +
        '<div class="modal-header">' +
        '<button type="button" class="close" data-dismiss="modal">&times;</button>' +
        '<h4 class="modal-title">' + title + '</h4>' +
        '</div>' +
        '<div class="modal-body">' +
        '<p>' + content + '</p>' +
        '</div>' +
        '<div class="modal-footer">' +
        '<button type="button" class="btn btn-success" data-dismiss="modal">' + buttonPositive + '</button>';

    if (buttonNegative != undefined)
        html += '<button type="button" class="btn btn-danger" data-dismiss="modal">' + buttonNegative + '</button>';

    html +=
        '</div>' +
        '</div>' +
        '</div>' +
        '</div>';
    $('#modalHere').html(html);
    $('#myModal').modal();

}

function showEditModal(course) {

    var old_number = course['number'];
    var old_term = course['term'];
    var old_year = course['year'];

    var html =
        '<div id="myModal" class="modal fade" role="dialog">' +
        '<div class="modal-dialog">' +
        '<!-- Modal content-->' +
        '<div class="modal-content">' +
        '<div class="modal-header">' +
        '<button type="button" class="pull-right btn btn-danger"  data-dismiss="modal" style="margin: 2px;">Cancel</button>' +
        '<button id="editCourseSubmit" type="button" class="pull-right btn btn-success" style="margin: 2px;">Update</button>' +
        '<h4 class="modal-title">' + "Edit Course" + '</h4>' +
        '</div>' +
        '<div class="modal-body">' +
        '<form name="loginForm" class="form-signin" action="courses"' +
        'onsubmit="validateForm();" method="post" style="max-width: 800px !important;" >' +
        '<div hidden class="pad" id="name_parent">' +
        '<label for="name">Course Details</label>' +
        '<div class="row" id="name">' +
        '<div class="col-lg-6">' +
        '<input id="course_num" name="course_num" type="text" class="form-control" placeholder="Number" autofocus required value="' + course['number'] + '">' +
        '</div>' +
        '<div class="col-lg-6">' +
        '<input id="course_name" name="course_name" type="text" class="form-control" placeholder="Name" required value="' + course['name'] + '">' +
        '</div>' +
        '</div>' +
        '</div>' +
        '<div hidden class="pad" id="offered_sem">' +
        '<label for="sem">Offered Term</label>' +
        '<div class="row" id="sem">' +
        '<div class="col-lg-6">' +
        '<select name="sem" class="form-control">' +
        '<option ';
    if (course['term'] == "Fall")
        html += 'selected';
    html += '>Fall</option>' +
        '<option ';
    if (course['term'] == "Spring")
        html += 'selected';
    html += '>Spring</option>' +
        '</select>' +
        '</div>' +
        '<div class="col-lg-6">' +
        '<input name="year" id="start_year" type="number" class="form-control col-lg-6" placeholder="Year" required value="' + course['year'] + '">' +
        '</div>' +
        '</div>' +
        '</div>' +
        '<div class="pad" id="department">' +
        '<label for="dept">Department</label>' +
        '<select name="department" class="form-control" id="dept">' +
        '<option ';

    if (course['department'] == "Chemical Engineering")
        html += 'selected';

    html +=
        '>Chemical Engineering</option>' +
        '<option ';

    if (course['department'] == "Computer Science")
        html += 'selected';

    html += '>Computer Science</option>' +
        '<option ';

    if (course['department'] == "Computer Engineering")
        html += 'selected';

    html += '>Computer Engineering</option>' +
        '<option ';

    if (course['department'] == "Electrical Engineering")
        html += 'selected';

    html += '>Electrical Engineering</option>' +
        '<option ';

    if (course['department'] == "Environmental Sciences")
        html += 'selected';

    html += '>Environmental Sciences</option>' +
        '<option ';

    if (course['department'] == "Mechanical Engineering")
        html += 'selected';

    html += '>Mechanical Engineering</option>' +
        '</select>' +
        '</div>' +
        '<div class="pad" id="course_syllabus_parent">' +
        '<label for="course_syllabus">Course Syllabus</label>' +
        '<textarea id="course_syllabus" name="course_syllabus" class="form-control" placeholder="">' + course['course_syllabus'] + '</textarea>' +
        '</div>' +
        '<div class="pad">' +
        '<label for="office_details">Instructor Office Details</label>' +
        '<div class="row" id="office_details">' +
        '<div class="col-lg-6">' +
        '<input id="ins_office" name="ins_office" type="text" class="form-control" placeholder="Location(Example: WCH 111)" value="' + course['ins_office'] + '">' +
        '</div>' +
        '<div class="col-lg-6">' +
        '<input id="ins_office_hour" name="ins_office_hour" type="text" class="form-control" placeholder="Hours" value="' + course['ins_office_hour'] + '">' +
        '</div>' +
        '</div>' +
        '</div>' +
        '<div class="pad">' +
        '<label for="ta_name_details">TA Details</label>' +
        '<div class="row" id="ta_name_details">' +
        '<div class="col-lg-6">' +
        '<input id="ta_name" name="ta_name" type="text" class="form-control" placeholder="Name" value="' + course['ta_name'] + '">' +
        '</div>' +
        '<div class="col-lg-6">' +
        '<input id="ta_email" name="ta_email" type="email" class="form-control" placeholder="Email" value="' + course['ta_email'] + '">' +
        '</div>' +
        '</div>' +
        '<div class="row">' +
        '<div class="col-lg-6">' +
        '<input id="ta_office" name="ta_office" type="text" class="form-control" placeholder="Location(Example: WCH 111)" value="' + course['ta_office'] + '">' +
        '</div>' +
        '<div class="col-lg-6">' +
        '<input id="ta_office_hour" name="ta_office_hour" type="text" class="form-control" placeholder="Office Hours" value="' + course['ta_office_hour'] + '">' +
        '</div>' +
        '</div>' +
        '</div>' +
        '</form>' + '</div>';
    $('#modalHere').html(html);
    $('#myModal').modal();

    $('#editCourseSubmit').click(function () {
        console.log('Submitted');
        $.post("/courses", {
                action: "update",
                department: $('#dept').val(),
                course_syllabus: $('#course_syllabus').val(),
                ins_office_hour: $('#ins_office_hour').val(),
                ins_office: $('#ins_office').val(),
                ta_name: $('#ta_name').val(),
                ta_office_hour: $('#ta_office_hour').val(),
                ta_office: $('#ta_office').val(),
                ta_email: $('#ta_email').val(),
                number: old_number,
                term: old_term,
                year: old_year

            },
            function (data, status) {
                if (data == "success") {
                    location.reload();
                } else {
                    $.toaster({priority: 'danger', title: 'Error', message: 'Failed, Please try again.'});
                }
            });
    });

}
