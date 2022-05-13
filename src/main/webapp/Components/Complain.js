$(document).ready(function() {
	$("#alertSuccess").hide();
	$("#alertError").hide();
});

$(document).on("click", "#btnSave", function(event) {
	// Clear status msges---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	// Form validation-------------------
	var status = validateComplaintForm();
	// If not valid
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	} 
	
	//if valid
	var type = ($("#hidcomIDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
	{
		url : "ComplainAPI",
		type : type,
		data : $("#Complaint").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			onComplainSaveComplete(response.responseText, status);
		}
	});
	
	$("#Complaint").submit();
	

});

function onComplainSaveComplete(response, status) 
{ 
 	if(status == "success")
 	{
		var resultSet = JSON.parse(response);
		
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully saved."); 
			$("#alertSuccess").show(); 
			
			$("#comGrid").html(resultSet.data); 
		}else if(resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data); 
 			$("#alertError").show();
		}
	}else if(status == "error")
	{
		 $("#alertError").text("Error while saving."); 
		 $("#alertError").show();
	}else
	{
		 $("#alertError").text("Error while saving."); 
		 $("#alertError").show();
	}
		$("#hidcomIDSave").val(""); 
		$("#Complaint")[0].reset(); 
}

function validateComplaintForm() {
	//Validations 
	//USER ID
	if ($("#Userid").val().trim() == "") {
		return "Insert User ID!";
	}

	//COMPLAINT
	if ($("#combox").val().trim() == "") {
		return "PLease Insert Complaint!";
	}

	return true;

}

