var ajaxObjekt;
var ajaxObjektGoogleAPI;

function getStudent()
{
	if (window.XMLHttpRequest)
	{
		 ajaxObjekt = new XMLHttpRequest();
		 ajaxObjektGoogleAPI = new XMLHttpRequest();
	}
	else if (window.ActiveXObject)
	{
		 ajaxObjekt = new ActiveXObject('Microsoft.XMLHTTP');
		 ajaxObjektGoogleAPI = new ActiveXObject('Microsoft.XMLHTTP');
	}
  
	ajaxObjekt.onreadystatechange = function()
	{
		if(ajaxObjekt.readyState==4 && ajaxObjekt.status==200)
		{
			vpisna = ajaxObjekt.responseXML.getElementsByTagName('vpisna');
			letnik = ajaxObjekt.responseXML.getElementsByTagName('letnik');
			ime = ajaxObjekt.responseXML.getElementsByTagName('ime');
			priimek = ajaxObjekt.responseXML.getElementsByTagName('priimek');
			ulica = ajaxObjekt.responseXML.getElementsByTagName('ulica');
			kraj = ajaxObjekt.responseXML.getElementsByTagName('kraj');
			
			
			document.getElementById('vpisna').innerHTML = 'Vpisna številka: ' + vpisna[0].firstChild.nodeValue;
			document.getElementById('ime_priimek').innerHTML = 'Ime in Priimek: ' + ime[0].firstChild.nodeValue + ' ' + priimek[0].firstChild.nodeValue;
			document.getElementById('letnik').innerHTML = 'Letnik: ' + letnik[0].firstChild.nodeValue;
			document.getElementById('ulica').innerHTML = 'Ulica: ' + ulica[0].firstChild.nodeValue;
			document.getElementById('kraj').innerHTML = 'Kraj: ' + kraj[0].firstChild.nodeValue;
			
			origins = ulica[0].firstChild.nodeValue + ',' + kraj[0].firstChild.nodeValue;
			url = 'http://maps.googleapis.com/maps/api/distancematrix/xml?origins=' + origins.replace(/ /g, "+") + '&destinations=Trzaska+cesta+25,Ljubljana&mode=walking&sensor=false';
			ajaxObjektGoogleAPI.open('GET', url, true);
			ajaxObjektGoogleAPI.send(null);
		}
	}
	
	ajaxObjektGoogleAPI.onreadystatechange = function()
	{
		if(ajaxObjektGoogleAPI.readyState==4 && ajaxObjektGoogleAPI.status==200)
		{
			text = ajaxObjektGoogleAPI.responseXML.getElementsByTagName('text');
			document.getElementById('razdalja').innerHTML = 'Oddaljenost od faksa: ' + text[1].firstChild.nodeValue;
			document.getElementById('cas').innerHTML = 'Èas hoje do faksa: ' + text[0].firstChild.nodeValue;
		}
	}
	
	vsebinaNiza=document.getElementById('vpisnaStevilka').value;
	ajaxObjekt.open('GET','http://localhost:8080/seminarska/viri/student/'+vsebinaNiza, true);
	ajaxObjekt.send(null);
}