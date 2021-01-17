<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@page import="javax.swing.text.DateFormatter"%>
<%@page import="java.text.DateFormat"%>
<%@page import="uned.java2016.apitwitter.dao.ClinicalStudy"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.util.ArrayList"%>
<%@ page import = "java.util.ArrayList"%>

<%@ page import = "uned.java2016.apitwitter.dao.Tweet"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%	
	Tweet tweetInfo = (Tweet) request.getAttribute("tweetInfo");
	ClinicalStudy studioInfo = (ClinicalStudy) request.getAttribute("studioInfo"); 
%>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
		<link href="css/ViewIndex.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/ViewIndex.js"></script>
		<%if(tweetInfo == null && studioInfo == null ){ %> 
		<title>Error No tweet, No Study</title>
		<% } %>
		<%if(tweetInfo != null){ %> 
		<title>Vista Tweet</title>
		<% } %>
		<%if(tweetInfo == null && studioInfo != null){ %> 
		<title>Vista Studio</title>
		<% } %>
	</head>
	<body>
	<%
	if(tweetInfo == null && studioInfo == null ){ 
			//<script type='text/javascript'>window.location='APITwitterOBJ';</script>
	%>
			<h2> No se encuentra el tweet, o el estudio</h2>
			
	<% }
		else{ //Si ya los tenemos empezamos a crear la página
	%>
			<div id="faja">
				<header>
				</header>
				<div id="centro">
					<div id="capa_buscar" class="misZonas">
						<div id="zonaApiTwitter">
							<img src="imagenes/zona_buscar/ApiTwitter.png" alt="ApiTwitter" />
						</div>					
						<!-- <div id="zonaUsuario">
							<h5>Usuario: &#160;</h5>
							<p>Nombre del Usuario</p>
						</div>
						 -->
					</div>							
					<% 							
					if (tweetInfo != null) {
					out.println("<div class=SingleTweetView'>");
						out.println("<div class='TweetInfo'>");
						out.println("<div class='TweetInfoHeader'>");
							out.println("<h1><b>Tweet ID:</b>" + tweetInfo.getId() + "</h1>");
						out.println("</div>");
								out.println("<ul><li><b> Tweet Text:</b> "+ tweetInfo.getText()  +  "</li>");
								out.println("<div class='SingleTweetInfoHastags'>");
									out.println("<h2> Hastag:</h2>");
									
									out.println("<ul>");
									for (int i =0 ; i < tweetInfo.getEntities().getHashtags().length; ++i)
									{										
										out.println("<li>"+tweetInfo.getEntities().getHashtags()[i].getText()+"</li>");									
									
									}																					
								out.println("</ul></div>");						
								out.println("<div class='SingleTweetInfoUrls'>");
									out.println("<h2> URL: </h2><ul>");
									for (int i =0 ; i < tweetInfo.getEntities().getUrls().size(); ++i)
									{
										out.println("<li>"+tweetInfo.getEntities().getUrls().get(i).getUrl()+"</li>");									
									}
								out.println("</ul></div>");						
							out.println("</div>");						
							out.println("<div class='userInfoTweet'>");
								out.println("<h1> User  Info</h1>");
								out.println("<ul><li><b> User Name:</b>"+ tweetInfo.getUser().getName()  +  "</li>");
								out.println("<li><b> User Id:</b>"+ tweetInfo.getUser().getId()  +  "</li>");
								out.println("<li><b> Follower Counts:</b>"+ tweetInfo.getUser().getFollowersCount() +  "</li>");
								out.println("<li><b> Friends Count:</b>"+ tweetInfo.getUser().getFriendsCount() +  "</li>");
									
								if (tweetInfo.getUser().getProfuleImageUrl() != null)
									{
										out.println("<li><img src=" + tweetInfo.getUser().getProfuleImageUrl() +   " alt='UserImage' /></li>");	
									}
								else
									{
										out.println("<li>No user Image</li>");
									}
								out.println("<li><b> Screen Name:</b>"+ tweetInfo.getUser().getScreenName() +  "</li></ul>");
							out.println("</div>");
						out.println("</div>");						
					}
					if (studioInfo != null)
					{
						out.println("<div class=SingleClinicalStudyView'>");
						out.println("<div class='ClinicalStudyInfo'>");
						out.println("<div class='ClinicalStudyInfoHeader'>");
							out.println("<h1><b> ID:</b>" +studioInfo.getNctId()+ "</h1>");
							out.println("<h1><b> Brief Title :</b>" +studioInfo.getBriefTitle()+ "</h1>");
							out.println("<h1><b> Official Title :</b>" +studioInfo.getOfficialTitle()+ "</h1>");
						out.println("</div>");
								out.println("<ul><li><b>Summary:</b> "+ studioInfo.getBriefSummary() +  "</li>");
								out.println("<li><b>Detailed Description:</b> "+ studioInfo.getDetailedDescription() +  "</li>");
								out.println("<li><b>Study Design:</b> "+ studioInfo.getStudyDesign() +  "</li>");
								out.println("<li><b>Primary outcomeMeasure:</b> "+ studioInfo.getPrimaryOutcomeMeasure() +  "</li>");
								out.println("<li><b>Overall Status:</b> "+ studioInfo.getOverallStatus() +  "</li>");								
								out.println("<li><b>Verification Date:</b> "+ studioInfo.getVerificationDate().toString() +  "</li>");
								out.println("<li><b>Last Changed Date:</b> "+ studioInfo.getLastChangedDate().toString() +  "</li>");
								out.println("<li><b>First Received Date:</b> "+ studioInfo.getFirstReceivedDate().toString() +  "</li>");
								out.println("<li><b>Location Facility Name:</b> "+ studioInfo.getLocationFacilityFame() +  "</li>");
								out.println("<li><b>Organization:</b> "+ studioInfo.getOrganization() +  "</li>");
								out.println("<li><b>Oversight info Authority:</b> "+ studioInfo.getOversightInfoAuthority() +  "</li>");
								out.println("<li><b>HashTags:</b> "+ studioInfo.getHashtags() +  "</li>");
								
							out.println("<div class='SingleClinicalStudioSecondaryOutComes'>");
									out.println("<h2> SecondaryOutComes:</h2>");
									
									out.println("<ul>");
									for (int i =0 ; i < studioInfo.getSecondaryOutcomes().size(); ++i)
									{										
										out.println("<li>"+studioInfo.getSecondaryOutcomes().get(i) +"</li>");
									}																					
							out.println("</ul></div>");		
								
								
							out.println("<div class='SingleClinicalConditions'>");
								out.println("<h2>Conditions:</h2>");
								
								out.println("<ul>");
								for (int i =0 ; i < studioInfo.getConditions().size(); ++i)
								{										
									out.println("<li>"+studioInfo.getConditions().get(i) +"</li>");
								}																					
							out.println("</ul></div>");
							
							out.println("<div class='SingleClinicalKeyWords'>");
								out.println("<h2>KeyWords:</h2>");
								
								out.println("<ul>");
								for (int i =0 ; i < studioInfo.getKeywords().size(); ++i)
								{										
									out.println("<li>"+studioInfo.getKeywords().get(i) +"</li>");
								}																					
							out.println("</ul></div>");
							
							out.println("<li><b>Responsible Party:</b> "); 
									if (studioInfo.getResponsibleParty() !=null)
									{
										out.println(" Party Type: "
										+studioInfo.getResponsibleParty().getResponsiblePartyType()
										 +", Affilation: " + studioInfo.getResponsibleParty().getInvestigatorAffilation()
										 +", FullName: "+ studioInfo.getResponsibleParty().getInvestigatorFullName()
										 +", Title: "+ studioInfo.getResponsibleParty().getInvestigatorTitle()
										 +"</li>");
									}
									else 
									{
										out.println(" Empty");
									}						
							out.println("<div class='SingleClinicalInterventions'>");
							out.println("<h2>Interventions:</h2>");							
							
							if(studioInfo.getIntervention()!= null)
							{
								out.println("<ul>");
								for (int i =0 ; i < studioInfo.getIntervention().size(); ++i)
								{										
									out.println("<li>Type: " 
								+ studioInfo.getIntervention().get(i).getType() 
								+ ", Name: "+studioInfo.getIntervention().get(i).getName() +"</li>");
								}																					
								out.println("</ul></div>");
							}
					out.println("</div>");		
					}
					%>
						
				</div>
				<footer>
				</footer>
			</div>
		<%} %>
	
	
	</body>
</html>