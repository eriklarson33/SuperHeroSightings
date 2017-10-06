/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var siteId = String($('#add-id').val());
//alert("The current Site Id is: " + siteId);
var chkArray = [];

var currentSupers = [];
var currentIds = [];

var listOfAllSupers = [];

var supersPresent = $('#supersPresent');
var supersAbsent = $('#supersAbsent');

$(document).ready(function () {


    loadSupersPresent();

    function loadSupersPresent() {
        var currentSuperRows = $('#currentSuperRows');

        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/HEROdb/superHumansBySite/' + siteId,
            success: function (data, status) {
                currentSupers = [];
                currentIds = [];
                $.each(data, function (index, superHuman) {
                    var name = superHuman.superHumanName;
                    //alert("The Super's name is " + name);
                    var description = superHuman.description;
                    //alert("The super's description: " + description)
                    var id = superHuman.superHumanId;
                    //alert("The Super's Id is " + id);
                    currentSupers.push(superHuman);
                    currentIds.push(id);
                    var row = '<tr>';
                    row += '<td>' + name + '</td>';
                    row += '<td>' + description + '</td>';
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

    function populateSuperHumanChoices() {

        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/HEROdb/superHumans',
            success: function (data, status) {
                listOfAllSupers = [];
                $.each(data, function (index, s) {
                    var superName = s.superHumanName;
                    //alert("The super's name from listOfAllSupers is " + superName);
                    var superDescription = s.description;
                    var superId = s.superHumanId;
                    listOfAllSupers.push(s);
                    finishUp();
                });
            }
        });
    }

    $('#showEditSupersPresent').on("click", function () {
        $('#supersPresent').empty();

        $('#supersAbsent').empty();

        populateSuperHumanChoices();

    });
});

function diff(listOfAllSupers, currentIds) {
    var holderArray = [];
    // iterate through 1st array:
    for (var i = 0; i < listOfAllSupers.length; i++) {
        //check the 2nd array, currentIds.
        //While the 2nd array doesn't conatain any values in the first array(=-1)
        if (currentIds.indexOf(listOfAllSupers[i].superHumanId) === -1) {
            //if true, add to holderArray
            holderArray.push(listOfAllSupers[i]);
            //alert(listOfAllSupers[i].superHumanName);
        }
    }
    return holderArray;
}

function finishUp() {
    var absentSupers = diff(listOfAllSupers, currentIds);
    supersPresent.empty();
    supersAbsent.empty();

    currentSupers.forEach(function (superHuman, index) {
        var name = superHuman.superHumanName;
        var id = superHuman.superHumanId;
        var superList = '<label class="checkbox-inline">';
        superList += '<input class="superCheckbox" type="checkbox" id="superHuman' + id + '" value="' + id + '" checked>' + name + '</input>';
        superList += '</label>';
        supersPresent.append(superList);
    });

    absentSupers.forEach(function (superHuman, index) {
        var name = superHuman.superHumanName;
        var id = superHuman.superHumanId;
        var supList = '<label class="checkbox-inline">';
        supList += '<input class="superCheckbox" type="checkbox" id="superHuman' + id + '" value="' + id + '">' + name + '</input>';
        supList += '</label>';
        supersAbsent.append(supList);
    });

    showEditForm();
}

function showEditForm() {
    $('#errorMessages').empty();

    $('#editFormDiv').show();
}

function cancelEditForm() {
    $('#errorMessages').empty();

    $('#supersPresent').empty();

    $('#supersAbsent').empty();

    $('#editFormDiv').hide();
}

$('#updateSupersPresent').on("click", function () {
    // Clear the old array values
    chkArray = [];
    /* Get the checkbox values based on the class attached to each check box */
    getValueUsingClass();
    console.log(chkArray);

    if (typeof chkArray !== 'undefined' && chkArray.length > 0) {

        $.ajax({
            type: 'PUT',
            url: 'http://localhost:8080/HEROdb/superHumanXsightings/',
            data: JSON.stringify({
                sightingId: siteId,
                supers: chkArray
            }),
            headers: {
                'Content-Type': 'application/json'
            },
            success: function () {
                // clear errorMessages
                $('#errorMessages').empty();
                succesfulAJAX();
            },
            error: function () {
                $('#errorMessages')
                        .append($('<li>')
                                .attr({class: 'list-group-item list-group-item-danger'})
                                .text('Error calling web service.  Please try again later.'));
            }
        });

    } else {
        //alert("The array is empty.");
        clearCurrentSuperHumans();
    }
});

function getValueUsingClass() {
    $(".superCheckbox:checked").each(function () {
        chkArray.push($(this).val());
    });
}

function succesfulAJAX() {
    location.reload();
}

function clearCurrentSuperHumans() {
    $.ajax({
        type: 'DELETE',
        url: 'http://localhost:8080/HEROdb/sighting/' + siteId,

        success: function () {
            // clear errorMessages
            $('#errorMessages').empty();
            succesfulAJAX();
        },
        error: function () {
            $('#errorMessages')
                    .append($('<li>')
                            .attr({class: 'list-group-item list-group-item-danger'})
                            .text('Error calling web service.  Please try again later.'));
        }
    });
}