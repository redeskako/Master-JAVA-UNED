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
    <title>Tabla de Canales Thinspo</title>
    <script type="text/javascript">
        //En caso de detectar un error en la carga de la tabla(Ej Perdida de Conexion con la BBDD) saca mensaje y redirige al login
	    $.subscribe('errorCarga', function(event,data) {
    	       alert('Ha ocurrido un error durante la carga de la tabla de Channels.Consulte con el Administrador.Mas detalle en el log');  	
    	       top.location='login.jsp';
   		 });
    </script>
</head>
<body class="thinspo">
<s:if test="hasActionMessages()">
   <div class="errors">
      <s:actionmessage/>
   </div>
</s:if>
 <s:url id="obtenerCanalesurl" action="listarChannels"/>
    <sjg:grid
        id="gridtable"
        caption="Tabla de Canales de Thinspo"
        dataType="json"
        href="%{obtenerCanalesurl}"
        pager="true"
        gridModel="modeloTabla"
        rowList="10,15,20,25,30,50"
        rowNum="10"
        rownumbers="true"
        onErrorTopics="errorCarga"
    >
        <sjg:gridColumn name="username" index="username" title="Username"/>
        <sjg:gridColumn name="channelviews" index="channelviews" title="ChannelViews"/>
        <sjg:gridColumn name="datefavoritevideosearched" index="datefavoritevideosearched" title="DateFavoriteVideoSearched"/>
        <sjg:gridColumn name="datejoined" index="datejoined" title="DateJoined"/>
        <sjg:gridColumn name="datefriendssearch" index="datefriendssearch" title="DateFriendsSearch"/>
        <sjg:gridColumn name="datesubscribedtosearched" index="datesubscribedtosearched" title="DateSubscribedToSearched"/>
        <sjg:gridColumn name="location" index="location" title="Location"/>
        <sjg:gridColumn name="scoresna" index="scoresna" title="ScoresNA"/>
        <sjg:gridColumn name="scoresnanormalized" index="scoresnanormalized" title="ScoresNANormalized"/>
        <sjg:gridColumn name="subscriberscount" index="subscriberscount" title="SubscriberScount"/>
        <sjg:gridColumn name="timecaptured" index="timecaptured" title="TimeCaptured"/>
        <sjg:gridColumn name="timefavcaptured" index="timefavcaptured" title="TimeFavCaptured"/>
        <sjg:gridColumn name="timescorecalculated" index="timescorecalculated" title="TimeScoreCalculated"/>
        <sjg:gridColumn name="timesubcaptured" index="timesubcaptured" title="TimeSubcaptured"/>
        <sjg:gridColumn name="uploadviews" index="uploadviews" title="UpLoadViews"/>
    </sjg:grid>
</body>
</html>