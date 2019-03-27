/**
 * Created by pranita on 22/4/16.
 */
function enroll(index, netID, number, term, year) {
    var should = $('#enroll' + index).text().toString().trim() == "Enroll";
    $.post("/courses", {
            action: should?"enroll" : "drop",
            netId: netID,
            num: number,
            tsem: term,
            tyear: year
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

var allCourses;
function setCourses(courses) {
    allCourses = courses;
    setTerms();
}
function updateEntries() {
    var count = 0;
    for(var i = 0 ; i < allCourses.length ; i++) {
        if((allCourses[i]['department'] == $("#dept").val() || ($("#dept").val() == 'All departments')) &&
            ((allCourses[i]['term'] + " " + allCourses[i]['year'] == $("#term").val() || ($("#term").val() == 'All Terms')))) {
            $( "#course-" + i ).show();
            count += 1;
        } else {
            $( "#course-" + i ).hide();
        }
    }
}

function setTerms() {
    var term = $('#term');
    var min_term = 9999;
    var max_term = 0;
    for(var i = 0 ; i < allCourses.length ; i++) {
        console.log(allCourses[i]['term'] + " " + allCourses[i]['year']);
        if(max_term < allCourses[i]['year'])
            max_term = allCourses[i]['year'];
        if(min_term > allCourses[i]['year'])
            min_term = allCourses[i]['year'];
    }

    for(var j = 0 ; j < (max_term - min_term + 1) ; j++) {
        var value = min_term + j;
        term.append($("<option/>", {
            text: ("Spring " + value)
        }));
        term.append($("<option/>", {
            text: ("Fall " + value)
        }));
    }
}
