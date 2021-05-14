$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	{
		$("#alertSuccess").hide();
	}
	
	$("#userCont").focusout(function() {
		checkContact();
	});
	
	$("#userEmail").focusout(function() {
		checkEmail();
	});
	
	$("#alertError").hide();
});


//variables 
let vContactno = true;
let vEmail = true;

//SAVE 
$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	// Form validation-------------------
	var status = validateUserForm();
	
	if (status != true)
	{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	
	// If valid
	var method = ($("#hidUserIDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
	{
		url : "UsersAPI",
		type : method,
		data : $("#formUser").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			onUserSaveComplete(response.responseText, status);
		}
	});
});

//UPDATE
$(document).on("click", ".btnUpdate", function(event)
{
	$("#hidUserIDSave").val($(this).closest("tr").find('#hidUserIDUpdate').val());
	$("#userCode").val($(this).closest("tr").find('td:eq(0)').text());
	$("#userName").val($(this).closest("tr").find('td:eq(1)').text());
	$("#fName").val($(this).closest("tr").find('td:eq(2)').text());
	$("#userCont").val($(this).closest("tr").find('td:eq(3)').text());
	$("#userAdd").val($(this).closest("tr").find('td:eq(4)').text());
	$("#userEmail").val($(this).closest("tr").find('td:eq(5)').text());
	$("#userPwd").val($(this).closest("tr").find('td:eq(6)').text());
});

function onUserSaveComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("User Successfully saved.");
			$("#alertSuccess").show();
			$("#divUsersGrid").html(resultSet.data);
		} 
		else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} 
	else if (status == "error")
	{
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} 
	else
	{
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	
	$("#hidUserIDSave").val("");
	$("#formUser")[0].reset();
}

$(document).on("click", ".btnRemove", function(event)
{
	$.ajax(
	{
		url : "UsersAPI",
		type : "DELETE",
		data : "userID=" + $(this).data("userid"),
		dataType : "text",
		complete : function(response, status)
		{
			onUserDeleteComplete(response.responseText, status);
		}
	});
});

function onUserDeleteComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divUsersGrid").html(resultSet.data);
		} 
		else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} 
	else if (status == "error")
	{
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} 
	else
	{
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}




// email validation
function checkEmail() {
	let email = $("#userEmail").val().trim();
	let regex = /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;

	if (regex.test(email)) {
		$("#email_error").hide();
		return true;
	} else {
		$("#email_error").html("Enter valid email");
		$("#email_error").show();
		return false;
	}
}


function validateUserForm()
{
	// User ID
	if ($("#userCode").val().trim() == "")
	{
		return "Please Insert User ID.";
	}
	
	//Username
	if ($("#userName").val().trim() == "")
	{
		return "Please Insert  Usename.";
	}
	
	//First Name
	if ($("#fName").val().trim() == "")
	{
		return "Please Insert First Name.";
	}
	
	// Contact No validation
	var tmpPrice = $("#userCont").val().trim();
	
	if (!$.isNumeric(tmpPrice))
	{
		return "Please Insert Valid Contact No.";
	}
	
	//User Address
	if ($("#userAdd").val().trim() == "")
	{
		return "Please Insert Address.";
	}
	
	//User Email
	if ($("#userEmail").val().trim() == "")
	{
		return "Please Insert Email.";
	}

	
	// Password
	if ($("#userPwd").val().trim() == "")
	{
		return "Please Insert Password.";
	}
	
	return true;
}

function checkContact() {
	var contact = $("#userCont").val().length;
	
	if(contact == 10) {
		$("#error_contact").hide();
	} else {
		$("#error_contact").html("This field is required");
		$("#error_contact").show();
	}
}

//email validation
function checkEmail() {
	let email = $("#userEmail").val().trim();
	let regex = /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;

	if (regex.test(email)) {
		$("#error_email").hide();
	} else {
		$("#error_email").html("Please Enter valid email");
		$("#error_email").show();
	}
}