function showHint(hashtag,cadena)

{
    if ( cadena.length > 6){
	   GetNCTIDScript.getNCTsByHashtag(hashtag, cadena, updateData);
    }
}

function updateData(data)

{
	var ncts = data;
	var nctsString = "";
	for (var i = 0; i < ncts.length; i++) {
	   nctsString += ncts[i] + " ";
	}
    dwr.util.setValue("txtHint", nctsString);
}