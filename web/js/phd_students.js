/**
 * Created by pranita on 22/4/16.
 */
var allStudents;
function setStudents(students) {
    allStudents = students;
}
function updateEntries() {
    var count = 0;
    for(var i = 0 ; i < allStudents.length ; i++) {
        
        if(allStudents[i]['department'] == $("#dept").val() || ($("#dept").val() == 'All departments')) {
            $( "#student-" + i ).show();
            count += 1;
        } else {
            $( "#student-" + i ).hide();
        }
        
        // toggle( allStudents[i]['department'] == $("#dept").val() || ($("#dept").val() == 'All departments'));
    }
    
    
    $('#nostudents').toggle(count == 0);
    
}
