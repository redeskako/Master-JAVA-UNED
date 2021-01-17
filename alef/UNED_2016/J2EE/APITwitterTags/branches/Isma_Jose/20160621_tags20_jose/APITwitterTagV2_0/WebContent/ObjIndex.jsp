<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="uned.java2016.apitwitter.dao.ClinicalStudy"%>
<%@ page import = "java.util.ArrayList"%>
<%@ page import = "uned.java2016.apitwitter.dao.Tweet"%>
<%@ taglib uri="WEB-INF/objIndexTag.tld" prefix="ot" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

    <ot:initializer/>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
		<link href="css/ViewIndex.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/ViewIndex.js"></script>
		
		<c:choose>
		  <c:when test="${tweetInfo==null && studioInfo==null}">
		    <title>Error No tweet, No Study</title>
		  </c:when>
		  <c:when test="${tweetInfo!=null}">
		    <title>Vista Tweet</title>
		  </c:when>
		  <c:when test="${tweetInfo == null && studioInfo != null}">
		    <title>Vista Studio</title>
		  </c:when>		  
		</c:choose>
		
	</head>
	<body>
	
	<c:choose>
	   <c:when test="${tweetInfo == null && studioInfo == null}">
	     <h2> No se encuentra el tweet, o el estudio</h2>
	   </c:when>
	   <c:otherwise>
	     <!-- Si ya los tenemos empezamos a crear la página -->
			<!-- ****** -->	     
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
					<c:choose>
					  <c:when test="${tweetInfo != null}">
							<div class=SingleTweetView'>
								<div class='TweetInfo'>
									<div class='TweetInfoHeader'>
										<h1><b>Tweet ID:</b><c:out value="${tweetInfo.getId()}"/></h1>
									</div>
									<ul><li><b> Tweet Text:</b><c:out value="${tweetInfo.getText()}"/></li>
									<div class='SingleTweetInfoHastags'>
										<h2> Hastag:</h2>											
										<ul>
											<c:forEach var="i" begin="0" end="${tweetInfo.getEntities().getHashtags().length-1}">
											  <li> <c:out value="${tweetInfo.getEntities().getHashtags()[i].getText()}"/></li>	
											</c:forEach>																			
										</ul>
									</div>						
									<div class='SingleTweetInfoUrls'>
										<h2> URL: </h2>
										<ul>
											<c:forEach var="i" begin="0" end="${tweetInfo.getEntities().getUrls().size()-1}">
											  <li> <c:out value="${tweetInfo.getEntities().getUrls().get(i).getUrl()}"/></li>	
											</c:forEach>
										</ul>
									</div>						
								</div>						
							<div class='userInfoTweet'>
								<h1> User  Info</h1>");
								<ul>
								    <li><b> User Name:</b><c:out value="${tweetInfo.getUser().getName()}"/></li>
									<li><b> User Id:</b><c:out value="${tweetInfo.getUser().getId()}"/></li>
									<li><b> Follower Counts:</b><c:out value="${tweetInfo.getUser().getFollowersCount()}"/></li>
									<li><b> Friends Count:</b><c:out value="${tweetInfo.getUser().getFriendsCount()}"/></li>
										<c:choose>
											<c:when test="${tweetInfo.getUser().getProfuleImageUrl() != null}">
											    <c:set var="userImageProfileUrl" scope="page" value="${tweetInfo.getUser().getProfuleImageUrl()}"/>
												<li><c:out value="${'<img src=\"'}"/><c:out value="${userImageProfileUrl}"/><c:out value="${'\" alt=\"UserImage\" />'}"/>
											</c:when>
											<c:otherwise>
												<li>No user Image</li>
											</c:otherwise>
										</c:choose>
									<li><b> Screen Name:</b><c:out value="${tweetInfo.getUser().getScreenName()}"/></li></ul>
							</div>
						</div>						  
					  </c:when>
					  <c:when test="${studioInfo != null}">
						<div class=SingleClinicalStudyView'>						
							<div class='ClinicalStudyInfo'>
								<div class='ClinicalStudyInfoHeader'>
									<h1><b> ID:</b><c:out value="${studioInfo.getNctId()}"/></h1>
									<h1><b> Brief Title :</b><c:out value="${studioInfo.getBriefTitle()}"/></h1>
									<h1><b>  Official Title :</b><c:out value="${studioInfo.getOfficialTitle()}"/></h1>									
								</div>
								<ul>
									<li><b>Summary:</b><c:out value="${studioInfo.getBriefSummary()}"/></li>
									<li><b>Detailed Description:</b><c:out value="${studioInfo.getDetailedDescription()}"/></li>
									<li><b>Study Design:</b><c:out value="${studioInfo.getStudyDesign()}"/></li>
									<li><b>Primary outcomeMeasure:</b><c:out value="${studioInfo.getPrimaryOutcomeMeasure()}"/></li>		
									<li><b>Overall Status:</b><c:out value="${studioInfo.getOverallStatus()}"/></li>
									<li><b>Verification Date:</b><c:out value="${studioInfo.getVerificationDate().toString()}"/></li>
									<li><b>Last Changed Date:</b><c:out value="${studioInfo.getLastChangedDate().toString()}"/></li>
									<li><b>First Received Date:</b><c:out value="${studioInfo.getFirstReceivedDate().toString()}"/></li>
									<li><b>Location Facility Name:</b><c:out value="${studioInfo.getLocationFacilityFame()}"/></li>
									<li><b>Organization:</b><c:out value="${studioInfo.getOrganization()}"/></li>
									<li><b>Oversight info Authority:</b><c:out value="${studioInfo.getOversightInfoAuthority()}"/></li>
									<li><b>HashTags:</b><c:out value="${studioInfo.getHashtags()}"/></li>
									
										<div class='SingleClinicalStudioSecondaryOutComes'>
											<h2> SecondaryOutComes:</h2>
											<c:if test="${studioInfo.getSecondaryOutcomes().size()>0}">
												<ul>
													<c:forEach var="i" begin="0" end="${studioInfo.getSecondaryOutcomes().size()-1}">
														<li><c:out value="${studioInfo.getSecondaryOutcomes().get(i)}"/></li>
													</c:forEach>			
										        </ul>
										    </c:if>	
										</div>									
																	
																														
									<div class='SingleClinicalConditions'>
										<h2>Conditions:</h2>
										<c:if test="${studioInfo.getConditions().size()>0}">										
											<ul>
												<c:forEach var="i" begin="0" end="${studioInfo.getConditions().size()-1}">
													<li><c:out value="${studioInfo.getConditions().get(i)}"/></li>
												</c:forEach>
											</ul>
										</c:if>
									</div>
									
									<div class='SingleClinicalKeyWords'>
										<h2>KeyWords:</h2>
										<c:if test="${studioInfo.getKeywords().size()>0}">
											<ul>
												<c:forEach var="i" begin="0" end="${studioInfo.getKeywords().size()-1}">
													<li><c:out value="${studioInfo.getKeywords().get(i)}"/></li>
												</c:forEach>																			
											</ul>
										</c:if>
									</div>									
									<li><b>Responsible Party:</b>
										<c:choose>
											<c:when test="${studioInfo.getResponsibleParty() !=null}">
												Party Type:<c:out value="${studioInfo.getResponsibleParty().getResponsiblePartyType()}"/>, Affilation: <c:out value="${studioInfo.getResponsibleParty().getInvestigatorAffilation()}"/>, FullName: <c:out value="${studioInfo.getResponsibleParty().getInvestigatorFullName()}"/>, Title: <c:out value="${studioInfo.getResponsibleParty().getInvestigatorTitle()}"/>
											</c:when>
											<c:otherwise>
											   Empty
											</c:otherwise>
										</c:choose> 
									</li>
									<div class='SingleClinicalInterventions'>
										<h2>Interventions:</h2>
										<c:if test="${studioInfo.getIntervention()!= null && studioInfo.getIntervention().size()>0}">										 
											<ul>
											<c:forEach var="i" begin="0" end="${studioInfo.getIntervention().size()-1}">
												<li>Type: <c:out value="${studioInfo.getIntervention().get(i).getType()}"/>, Name: <c:out value="${studioInfo.getIntervention().get(i).getName()}"/></li>
											</c:forEach>
											</ul>									
										</c:if>							
									</div>
									
						</div>						  
					  </c:when>
					</c:choose>
												
				
						
				</div>
				<footer>
				</footer>
			</div>
			
			<!-- ****** -->	     
	   </c:otherwise>
	</c:choose>

	
	</body>
</html>