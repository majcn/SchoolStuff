var ajaxObjekt;

function isciPosto()
{
	if (window.XMLHttpRequest)  // če obstaja objekt kot lastnost obj. window
	{
		ajaxObjekt = new XMLHttpRequest(); //običajen objekt
	}
	else if (window.ActiveXObject) //IE objekt
	{
		ajaxObjekt = new ActiveXObject("Microsoft.XMLHTTP");	
	}

	ajaxObjekt.onreadystatechange = razcleni;

	vsebinaNiza=document.getElementById('niz').value;
	ajaxObjekt.open('GET','http://localhost:8080/myFirstWeb/posta?isci='+vsebinaNiza, true);
	ajaxObjekt.send(null);
}


function razcleni()
{
	if (ajaxObjekt.readyState==4 && ajaxObjekt.status==200)
	{
		postaStevilka = ajaxObjekt.responseXML.getElementsByTagName("stevilka");
		postaNaziv = ajaxObjekt.responseXML.getElementsByTagName("naziv");
		
		if((postaStevilka.length > 0) && (postaNaziv.length > 0))
		{
			document.getElementById('stevilka').innerHTML=postaStevilka[0].firstChild.nodeValue;
			document.getElementById('naziv').innerHTML=postaNaziv[0].firstChild.nodeValue;
		}
	}
}