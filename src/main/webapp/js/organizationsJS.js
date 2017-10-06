/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var orgId = String($('#currentOrgId').val());

function viewOrg(id) {
// clear errorMessages
// same as "showEditForm in ContactList exercise
    $('#errorMessages').empty();

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/HEROdb/organization/' + id,
        success: function (data, status) {
            $('#profile-org-id').text(data.organizationId);
            $('#profile-org-name').text(data.organizationName);
            $('#profile-description').text(data.description);
            $('#orgStreet').text(data.street);
            $('#orgCity').text(data.city);
            $('#orgState').text(data.state);
            $('#orgZip').text(data.zip);
            $('#orgPhone').text(data.phone);

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
//    viewOrg(id);
    loadSupersPresent(orgId);
});

function loadSupersPresent() {
    var currentSuperRows = $('#currentSuperRows');

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/HEROdb/superHumansByOrg/' + orgId,
        success: function (data, status) {
            $.each(data, function (index, s) {
                var name = s.superHumanName;
                var description = s.description;
                var id = s.superHumanId;
                var row = '<tr>';
                row += '<td class="col-md-3">' + name + '</td>';
                row += '<td class="col-md-9"><h6>' + description + '</h6></td>';
                row += '<tr>';
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


