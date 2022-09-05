<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <sj:head jqueryui="true" jquerytheme="redmond" />
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
    <link rel="stylesheet" href="/J2EEThinspo/css/estilos.css" type="text/css" />
    <title>Tabla de Videos Thinspo</title>
        <script type="text/javascript">
        //En caso de detectar un error en la carga de la tabla(Ej Perdida de Conexion con la BBDD) saca mensaje y redirige al login
	    $.subscribe('errorCarga', function(event,data) {
    	       alert('Ha ocurrido un error durante la carga de la tabla de Videos.Consulte con el Administrador.Mas detalle en el log');  	
    	       top.location='login.jsp';
   		 });
    </script>
</head>
<body class="thinspo">
 <s:url id="remoteurl" action="listarVideos"/>
    <sjg:grid
        id="gridtable"
        caption="Tabla de Videos de Thinspo"
        dataType="json"
        href="%{remoteurl}"
        pager="true"
        gridModel="modeloTabla"
        rowList="10,15,20,25,30,50,100,200"
        rowNum="25"
        rownumbers="true"
        onErrorTopics="errorCarga"
    >
        <sjg:gridColumn name="videoid" index="videoid" title="VideoId"/>
        <sjg:gridColumn name="title" index="title" title="Title"/>
        <sjg:gridColumn name="description" index="description" title="Description"/>
        <sjg:gridColumn name="uoloader_username_oid" index="uoloader_username_oid" title="UpLoader_UserName_Id"/>
        <sjg:gridColumn name="viewcount" index="viewcount" title="ViewCount"/>
        <sjg:gridColumn name="averageold" index="averageold" title="AverageOld"/>
        <sjg:gridColumn name="categories" index="categories" title="Categories"/>
        <sjg:gridColumn name="datecaptured" index="datecaptured" title="DateCaptured"/>
        <sjg:gridColumn name="duration" index="duration" title="Duration"/>
        <sjg:gridColumn name="favoritecount" index="favoritecount" title="FavoriteCount"/>
        <sjg:gridColumn name="numlikes" index="numlikes" title="NumLikes"/>
        <sjg:gridColumn name="numdislikes" index="numdislikes" title="NumDislikes"/>
        <sjg:gridColumn name="numratersold" index="numratersold" title="NumRaterSold"/>
        <sjg:gridColumn name="published" index="published" title="published"/>
        <sjg:gridColumn name="scoresnanormalized" index="scoresnanormalized" title="ScoresNANormalized"/>
        <sjg:gridColumn name="scoresnatotal" index="scoresnatotal" title="ScoresNATotal"/>
        <sjg:gridColumn name="scoresnavideo" index="scoresnavideo" title="ScoresNAVideo"/>
        <sjg:gridColumn name="timecaptured" index="timecaptured" title="TimeCaptured"/>
        <sjg:gridColumn name="timecommentssearched" index="timecommentssearched" title="TimeCommentsSearched"/>
        <sjg:gridColumn name="timescorecalculated" index="timescorecalculated" title="timescorecalculated"/>
      </sjg:grid>
</body>
</html>