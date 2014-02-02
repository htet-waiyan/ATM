
function validate(){
	var name=document.getElementByName("cusname").value;
	var userID=document.getElementByName("userID").value;
	var nrc=document.getElementByName("NRIC").value;
	var passwd=document.getElementByName("passwd").value;
	var nationality=document.getElementByName("nationality").value;
	var dob=document.getElementByName("dob").value;
	var doj=document.getElementByName("doj").value;
	var gender=document.getElementByName("gender").value;
	var adds=document.getElementByName("address").value;
	
	alert(name);
	console.log(name);
	return false;
}