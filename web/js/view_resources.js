/**
 * Created by pranita on 21/4/16.
 */
function cancelIt(r_name, net_Id, slot_date, slot_time_range) {
    console.log(name, net_Id, slot_date, slot_time_range);
    $.post("/reserve_resource", 
        {
            name: r_name,
            netId: net_Id, 
            slot_date: slot_date,
            slot_time: slot_time_range
        },
        function (data, status) {
        if(data == "success"){
            $.toaster({ priority : 'success', title : 'Success', message : 'Successfully cancelled reservation.'});
            setTimeout(function(){
                window.location = "/view_resources";
            }, 1000);
        } else {
            $.toaster({ priority : 'danger', title : 'Error', message : 'Unable to cancel reservation. Try again!!'});
        }
    });
}