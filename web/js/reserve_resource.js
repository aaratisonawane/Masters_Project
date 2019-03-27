/**
 * Created by pranita on 17/4/16.
 */
function getTimeSlots(name, index) {
    var vals = {
        "9-10": "9am - 10am",
        "10-11": "10am - 11am",
        "11-12": "11am - 12pm",
        "12-1": "12pm - 1pm",
        "1-2": "1pm - 2pm",
        "2-3": "2pm - 3pm",
        "3-4": "3pm - 4pm",
        "4-5": "4pm - 5pm",
        "5-6": "5pm - 6pm",
        "6-7": "6pm - 7pm"
    };
    var date = $('#date-' + index).val();
    $.get("/TimeSlots?name=" + name + "&date=" + date, function (data, status) {
        var cList = $('#time-slots-' + index);
        for (var i = 0; i < data['data'].length; i++) {
            delete vals[data['data'][i]];
        }
        cList.empty();
        cList.append('<label>Available timeslots: </label><br/>');
        $.each(vals, function (i) {
            cList.append('<input type="radio" name="timeslot" value="' + i + '" required> ' + vals[i] + '<br/>');
        });
        cList.append('<br/>');
    });
}

function reserve(r_name, netId, index) {
    event.preventDefault();
    var date = $('#date-' + index).val();
    var slot = $('input[name="timeslot"]:checked').val();
    console.log(r_name, netId, date, slot);
    $.post("/TimeSlots",
        {
            name: r_name,
            netID: netId,
            slot_date: date,
            slot_time: slot
        },
        function(data, status){
            if(data == "success") {
                $.toaster({ priority : 'success', title : 'Success', message : 'Resource successfully reserved.'});
            } else {
                $.toaster({ priority : 'danger', title : 'Error', message : 'Failed to reserve resource.'});
            }
            var cList = $('#time-slots-' + index);
            cList.empty();
            $('.resource_additional').val("");
            
        });
}

function updateMinDate() {
    $('.resource_additional').attr("min", new Date().toISOString().slice(0, 10));
}

