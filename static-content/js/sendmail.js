$(document).ready (function(){
	getMailAccounts();	
	
	$("#sendMail").click(function (){
		sendMail();
	});
});

function getMailAccounts(){
	$.getJSON("http://localhost:9999/rest/admin/getConfiguration", function(accList){
		var option = "<option> Select an Account </option>";
		for (var i=0; i<accList.length; i++){
			option += "<option> " + accList[i].accName + " &lt;" + accList[i].accEmailId +"&gt;" + "</option>\n";
		}
		
		$("#accSelector").html(option);
	});
}

function sendMail(){
	var mailToSend = {};
	
	var accName = $("#accSelector").val().split("<");
	mailToSend['accName'] = accName[0].trim();
	
	mailToSend['bodyText'] = $("#bodyText").val();
	mailToSend['bodyHtml'] = $("#bodyHtml").val();
	mailToSend['subject'] = $("#subject").val();
	
	mailToSend['toAddr'] = generateAddrList($("#toAddr").val());
	mailToSend['replyTo'] = generateAddrList($("#replyToAddr").val());
	mailToSend['ccAddr'] = generateAddrList($("#ccAddr").val());
	mailToSend['bccAddr'] = generateAddrList($("#bccAddr").val());
	
	var fromAddrObj = generateAddrList($("#accSelector").val())
	var fromAddr = {};
	fromAddr['name'] = fromAddrObj[0]['name'];
	fromAddr['email'] = fromAddrObj[0]['email'];
	mailToSend['fromAddr'] = fromAddr;
	
	var jsonString = JSON.stringify(mailToSend);
	
	$.ajax ({
		url:"http://localhost:9999/rest/mail/sendMail",
		type:"POST",
		data:jsonString,
		contentType:"application/json; charset=utf-8",
		dataType:"json",
		success:function(response){
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

function generateAddrList(addrListText){
	
	var addrSet = addrListText.split(",");
	var mailToAddrs = [];
	if (addrListText){
		for (var i=0; i<addrSet.length; i++){
			var addrContent = addrSet[i].split("<");
			var addrObj = {};
			addrObj['name'] = addrContent[0].trim();
			emailId = addrContent[1].replace("<","");
			emailId = emailId.replace(">","");
			addrObj['email'] = emailId;
			
			mailToAddrs.push(addrObj);
		}
	}
	return mailToAddrs;
}

