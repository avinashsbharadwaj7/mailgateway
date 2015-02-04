$(document).ready (function(){
	getMailAccounts();
	
	$("#addAcc").click(function () {
		addAccount();
	});
	
	$('#myModal').on('show.bs.modal', function (e){
		updateAccount(e);
	});
	
});


function getMailAccounts(){
	$.getJSON("http://localhost:9999/rest/admin/getConfiguration", function (accList){
		var row = "";
		for (var i=0; i<accList.length; i++){
			var dataTargets = "";
			row += "<tr>\n";
			row += "<td>" + accList[i].accName  + "</td>\n"
			dataTargets += "data-accName = \""+accList[i].accName + "\"" + " "; 
			row += "<td>" + accList[i].accEmailId  + "</td>\n"
			dataTargets += "data-accEmailId = \""+accList[i].accEmailId + "\"" + " "; 
			row += "<td>" + accList[i].smtpHost  + "</td>\n"
			dataTargets += "data-smtpHost = \""+accList[i].smtpHost + "\"" + " "; 
			row += "<td>" + accList[i].smtpPort  + "</td>\n"
			dataTargets += "data-smtpPort = \""+accList[i].smtpPort + "\"" + " "; 
			row += "<td>" + accList[i].tlsEnabled  + "</td>\n"
			dataTargets += "data-tlsEnabled = \""+accList[i].tlsEnabled + "\"" + " "; 
			row += "<td>" + accList[i].accPassword  + "</td>\n"
			dataTargets += "data-accPassword = \""+accList[i].accPassword + "\"" + " "; 
			
			row += "<td> <button type=\"submit\" class=\"btn btn-xs btn-primary\" data-toggle=\"modal\" data-target=\"#myModal\""+ dataTargets +">Update Account</button></td>"
			row += "<tr>\n"
		}
		
		$("#tblBody").html(row);
	});
}

function addAccount (){
	var mailAccount = {};
	mailAccount['accName'] = $("#accName").val();
	mailAccount['accEmailId'] = $("#emailAddr").val();
	mailAccount['smtpHost'] = $("#smtpHost").val();
	mailAccount['smtpPort'] = $("#smtpPort").val();
	
	if ($("#tlsEnabled").val() == "TRUE"){
		mailAccount['tlsEnabled'] = true;
	}
	else {
		mailAccount['tlsEnabled'] = false;
	}
	
	mailAccount['accPassword'] = $("#smtpPassword").val();
	
	var jsonString = JSON.stringify(mailAccount);
	
	//Call Ajax and post the data
	$.ajax ({
		url:"http://localhost:9999/rest/admin/addConfiguration",
		type:"POST",
		data:jsonString,
		contentType:"application/json; charset=utf-8",
		dataType:"json",
		success:function(response){
			getMailAccounts();
			if (response.isError == false){
				$("#successBanner").append(response.successMsg)
				$("#successBanner").show();
			}
			else {
				$("#errorBanner").append(response.errorMsg);
				$("#errorBanner").show();
			}
		}
	});
}

function updateAccount (event){
	var button = $(event.relatedTarget)
	
	$("#modalEmailAddr").val(button.data('accemailid'));
	$("#modalAccName").val(button.data('accname'));
	var tlsoption = button.data('tlsenabled');
	$("#modalTlsEnabled").val(String(tlsoption));
	$("#modalSmtpHost").val(button.data('smtphost'));
	$("#modalSmtpPort").val(button.data('smtpport'));
	$("#modalSmtpPassword").val(button.data('accpassword'));
	
	$("#updateAccountBtn").click (function (){
		updateAccountAction();
	})
}

function updateAccountAction(){
	var updatedAcc = {};
	updatedAcc['accName'] = $("#modalAccName").val();
	updatedAcc['accEmailId'] = $("#modalEmailAddr").val();
	updatedAcc['smtpHost'] = $("#modalSmtpHost").val();
	updatedAcc['smtpPort'] = $("#modalSmtpPort").val();
	
	if ($("#modalTlsEnabled").val() == "TRUE"){
		updatedAcc['tlsEnabled'] = true;
	}
	else {
		updatedAcc['tlsEnabled'] = false;
	}
	
	updatedAcc['accPassword'] = $("#modalSmtpPassword").val();
	
	var jsonString = JSON.stringify(updatedAcc);
	
	$.ajax ({
		url:"http://localhost:9999/rest/admin/updateConfiguration",
		type:"POST",
		data:jsonString,
		contentType:"application/json; charset=utf-8",
		dataType:"json",
		success:function(response){
			if (response.isError == false){
				getMailAccounts();
				$("#successBanner").append(response.successMsg)
				$("#successBanner").show();
			}
			else {
				$("#errorBanner").append(response.errorMsg);
				$("#errorBanner").show();
			}
		}
	});
	
	$("#myModal").modal('hide');
}