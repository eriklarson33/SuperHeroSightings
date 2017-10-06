/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var shId = String($('#currentSuperId').val());

function viewProfile(id) {
// clear errorMessages
// same as "showEditForm in ContactList exercise
    $('#errorMessages').empty();

    $('#listOfSuperPowers').empty();

    $('#profileOrganizations').empty();

    $('#profileSightings').empty();
    //declare the listOfSuperPowers variable
    var listOfSuperPowers = $('#listOfSuperPowers');
    var profileOrganizations = $('#profileOrganizations');
    var profileSightings = $('#profileSightings');

    // get the SuperHuman details from the server and then fill and show the
    // form on success
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/HEROdb/superHuman/' + id,
        success: function (data, status) {
            $('#profile-super-id').val(data.superHumanId);
            $('#profile-super-name').val(data.superHumanName);
            $('#profile-description').val(data.description);
            //$('#profile-sightings').html(data.sightings);
            //$('#profile-organizations').html(data.organizations);
            //$('#profile-superPowers').html(data.superPowers);
            // Retrieve list of Super Powers & display w. Checkboxes
            var superPowers = data.superPowers;
            $.each(superPowers, function (index, superPower) {
                //alert(key + ": " + superPowers);
                var powerId = superPower.powerTypeId;
                var name = superPower.name;
                var description = superPower.description;
                var superList = '<label class="checkbox-inline">';
                superList += '<input type="checkbox" id="powerType' + powerId + '" value="' + powerId + '" checked>' + name + '</input>';
                superList += '</label>';
                listOfSuperPowers.append(superList);
            });
            var organizations = data.organizations;
            $.each(organizations, function (index, organization) {
                var orgId = organization.organizationId;
                var orgName = organization.organizationName;
                var orgDescription = organization.description;
                var orgList = '<label class="checkbox-inline">';
                orgList += '<input type="checkbox" id="orgList' + orgId + '" value="' + orgId + '" checked>' + orgName + '</input>';
                orgList += '</label>';
                profileOrganizations.append(orgList);
            });
            var sightings = data.sightings;
            $.each(sightings, function (index, site) {
                var siteId = site.sightingId;
                var siteName = site.name;
                var siteDescription = site.description;
                var siteDate = site.sightingDate;
                var siteLat = site.latitude;
                var siteLong = site.longitude;
                var siteList = '<label class="checkbox-inline">';
                siteList += '<input type="checkbox" id="siteList' + siteId + '" value="' + siteId + '" checked>' + siteName + ': ' + siteLat + ', ' + siteLong + '</input>';
                siteList += '</label>';
                profileSightings.append(siteList);
            });

            var chosenSuper = data.superHumanId;
            //function getSuperPowers(chosenSuper)

            //var requestedId = $('#profileId')
            //$('#profile-super-id').append('<input name="superId" value="' + requestedId + '")/>')
        },
        error: function () {
            $('#errorMessages')
                    .append($('<li>')
                            .attr({class: 'list-group-item list-group-item-danger'})
                            .text('Error calling web service.  Please try again later.'));
        }
    });

    //row += '<td><a role="button" id="profileButton" class="btn btn-warning" data-toggle="modal" data-target="#myModal"onclick="viewProfile(' + id + ')"><span class="glyphicon glyphicon-expand"></span> View Profile!</a></td>';


    $('#contactTableDiv').hide();
    $('#editFormDiv').show();
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
        url: 'http://localhost:8080/HEROdb/superHumansBySite/' + shId,
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

