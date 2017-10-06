/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var siteId = String($('#currentSightingId').val());

function viewSightingProfile(id) {
//   clear errorMessages
    $('#errorMessages').empty();
    
    var date;

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/HEROdb/showSightingById/' + id,
        success: function (data, status) {
            $('#profile-site-id').text(data.sightingId);
            $('#profile-site-name').text(data.name);
            $('#profile-description').text(data.description);
            $('#siteStreet').text(data.street);
            $('#siteCity').text(data.city);
            $('#siteState').text(data.state);
            $('#siteZip').text(data.zip);
            $('#siteLatitude').text(data.latitude);
            $('#siteLongitude').text(data.longitude);
            var rawDate = data.sightingDate;
                console.log(data.sightingDate);
                createDate();
            $('#profile-site-date').text(date);
            
            var rawDate;

                function createDate() {
                    var year = rawDate.year;
                    var month = rawDate.month;
                    var day = rawDate.dayOfMonth;
                    date = (month + " " + day + ", " + year);
                }
        },
        error: function () {
            $('#errorMessages')
                    .append($('<li>')
                            .attr({class: 'list-group-item list-group-item-danger'})
                            .text('Error calling web service.  Please try again later.'));
        }
    });

}

$('#profileButton').on('click', function () {
    loadSupersPresent(siteId);
});

function loadSupersPresent() {
    //clear errorMessages
    $('#errorMessages').empty();
    // clear list of present SuperHumans
    $('#currentSuperRows').empty();
    
    var currentSuperRows = $('#currentSuperRows');

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/HEROdb/superHumansBySite/' + siteId,
        success: function (data, status) {
            $.each(data, function (index, s) {
                var name = s.superHumanName;
                //alert("The Super's name is " + name);
                var description = s.description;
                //alert("The super's description: " + description)
                var id = s.superHumanId;
                var row = '<tr>';
                row += '<td class="col-md-3">' + name + '</td>';
                row += '<td class="col-md-9"><h6>' + description + '</h6></td>';
                row += '</tr>';
                currentSuperRows.append(row);
            });
        },
        error: function () {
            $('#errorMessages').append($('<li>')
                    .attr({class: 'list-group-item list-group-item-danger'})
                    .text('Error calling web service.  Please try again later.'));
        }
    });
}





