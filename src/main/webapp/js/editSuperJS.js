/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var superHumanId = String($('#add-id').val());
//alert("The current Site Id is: " + superHumanId);
var chkArray = [];

var currentPowers = [];
var currentIds = [];

var listOfAllPowers = [];

var supersPresent = $('#supersPresent');
var supersAbsent = $('#supersAbsent');

$(document).ready(function () {


    loadSupersPresent();

    function loadSupersPresent() {
        var currentSuperRows = $('#currentSuperRows');

        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/HEROdb/powersBySuperHuman/' + superHumanId,
            success: function (data, status) {
                currentPowers = [];
                currentIds = [];
                $.each(data, function (index, power) {
                    var name = power.name;
                    //alert("The Super's name is " + name);
                    var description = power.description;
                    //alert("The super's description: " + description)
                    var id = power.powerTypeId;
                    //alert("The Super's Id is " + id);
                    currentPowers.push(power);
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

    function populateSuperPowerChoices() {

        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/HEROdb/showSuperPowers',
            success: function (data, status) {
                listOfAllPowers = [];
                $.each(data, function (index, s) {
                    var superName = s.name;
                    //alert("The super's name from listOfAllPowers is " + superName);
                    var superDescription = s.description;
                    var superHumanId = s.powerTypeId;
                    listOfAllPowers.push(s);
                    finishUp();
                });
            }
        });
    }

    $('#showEditSupersPresent').on("click", function () {
        $('#supersPresent').empty();

        $('#supersAbsent').empty();

        populateSuperPowerChoices();

    });
});

function diff(listOfAllPowers, currentIds) {
    var holderArray = [];
    // iterate through 1st array:
    for (var i = 0; i < listOfAllPowers.length; i++) {
        //check the 2nd array, currentIds.
        //While the 2nd array doesn't conatain any values in the first array(=-1)
        if (currentIds.indexOf(listOfAllPowers[i].powerTypeId) === -1) {
            //if true, add to holderArray
            holderArray.push(listOfAllPowers[i]);
            //alert(listOfAllPowers[i].superHumanName);
        }
    }
    return holderArray;
}

function finishUp() {
    var absentSupers = diff(listOfAllPowers, currentIds);
    supersPresent.empty();
    supersAbsent.empty();

    currentPowers.forEach(function (superPower, index) {
        var name = superPower.name;
        var id = superPower.powerTypeId;
        var superList = '<label class="checkbox-inline">';
        superList += '<input class="superCheckbox" type="checkbox" id="superPower' + id + '" value="' + id + '" checked>' + name + '</input>';
        superList += '</label>';
        supersPresent.append(superList);
    });

    absentSupers.forEach(function (superPower, index) {
        var name = superPower.name;
        var id = superPower.powerTypeId;
        var supList = '<label class="checkbox-inline">';
        supList += '<input class="superCheckbox" type="checkbox" id="superPower' + id + '" value="' + id + '">' + name + '</input>';
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
            url: 'http://localhost:8080/HEROdb/superHumanXsuperPowers/',
            data: JSON.stringify({
                superHumanId: superHumanId,
                powers: chkArray
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
        clearCurrentSuperPowers();
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

function clearCurrentSuperPowers() {
    $.ajax({
        type: 'DELETE',
        url: 'http://localhost:8080/HEROdb/superPower/' + superHumanId,
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
