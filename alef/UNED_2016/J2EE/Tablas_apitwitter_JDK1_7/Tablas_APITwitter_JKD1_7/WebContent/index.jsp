<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "org.uned.pruebas.*,java.util.ArrayList,java.util.Iterator" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Página Principal</title>
</head>
<body>
	<h2  align="center"> LISTADO DE APITWITTER</h2>
	
	<h4  align="left"> TABLA TWEETS_INDEX</h4>
	<table  align="left" border="1">
		<tr  align="center">
			<td>TWEET_ID</td>
			<td>TEXT</td>
			<td>USER_ID</td>
			<td>USER_NAME</td>
			<td>FOLLOWERS_COUNT</td>
			<td>FRIENDS_COUNT</td>
			<td>PROFILE_IMAGE_URL_HTTPS</td>
		</tr>
		<% 
		Tweets_index unTweetIndex = new Tweets_index();
		ArrayList<Tweets_index> treeSetHashtags = new Tweets_index().listadoTweetsIndex();
		Iterator it= treeSetHashtags.iterator();
		while (it.hasNext()){
			unTweetIndex = (Tweets_index)it.next();
		%>
			<tr>
				<td align="center"> <%out.println(unTweetIndex.getTweet_id());%> </td>
				<td align="center"> <%out.println(unTweetIndex.getText());%> </td>
				<td align="center"> <%out.println(unTweetIndex.getUser_id());%> </td>
				<td align="center"> <%out.println(unTweetIndex.getUser_name());%> </td>
				<td align="center"> <%out.println(unTweetIndex.getFollowers_count());%> </td>
				<td align="center"> <%out.println(unTweetIndex.getFriends_count());%> </td>
				<td align="center"> <%out.println(unTweetIndex.getProfile_image_url_https());%> </td>
			</tr>
		<%
		}
		%>
	</table>
	<p>Fin tabla</p><br/>
	
	<h4  align="left"> TABLA POLL_INDEX</h4>
	<table  align="left" border="1">
		<tr  align="center">
			<td>FECHA_HORA</td>
			<td>MAX_ID</td>
		</tr>
		<% 
		Poll_index unPollIndex = new Poll_index();
		ArrayList<Poll_index> treeSetHashtags2 = new Poll_index().listadoPollIndex();
		Iterator it2= treeSetHashtags2.iterator();
		while (it2.hasNext()){
			unPollIndex = (Poll_index)it2.next();
		%>
			<tr>
				<td align="center"> <%out.println(unPollIndex.getFecha_hora());%> </td>
				<td align="center"> <%out.println(unPollIndex.getMax_id());%> </td>
			</tr>
		<%
		}
		%>
	</table>
	<br/><br/><br/><br/><br/><br/><br/><br/><p>Fin tabla</p><br/>
	
	<h4  align="left"> TABLA HASHTAGS_ADMIN_INDEX </h4>
	<table  align="left" border="1">
		<tr  align="center">
			<td>HASHTAG_ID</td>
			<td>ORIGEN</td>
			<td>FECHA_ENTRADA</td>
		</tr>
		<% 
		Hashtags_adm_index unHashtagsAdmIndex = new Hashtags_adm_index();
		ArrayList<Hashtags_adm_index> treeSetHashtags3 = new Hashtags_adm_index().listadoHashtagsAdmIndex();
		Iterator it3= treeSetHashtags3.iterator();
		while (it3.hasNext()){
			unHashtagsAdmIndex = (Hashtags_adm_index)it3.next();
		%>
			<tr>
				<td align="center"> <%out.println(unHashtagsAdmIndex.getHashtag_id());%> </td>
				<td align="center"> <%out.println(unHashtagsAdmIndex.getOrigen());%> </td>
				<td align="center"> <%out.println(unHashtagsAdmIndex.getFecha_entrada());%> </td>
			</tr>
		<%
		}
		%>
	</table>
	<br/><br/><br/><br/><br/><br/><br/><br/><p>Fin tabla</p><br/>
	
	<h4  align="left"> TABLA HASHTAGS_TWEET_INDEX </h4>
	<table  align="left" border="1">
		<tr  align="center">
			<td>ID</td>
			<td>TWEET_ID</td>
			<td>HASHTAG_ID</td>
		</tr>
		<% 
		Hashtags_tweet_index unHashtagsTweetIndex = new Hashtags_tweet_index();
		ArrayList<Hashtags_tweet_index> treeSetHashtags4 = new Hashtags_tweet_index().listadoHashtagsTweetIndex();
		Iterator it4= treeSetHashtags4.iterator();
		while (it4.hasNext()){
			unHashtagsTweetIndex = (Hashtags_tweet_index)it4.next();
		%>
			<tr>
				<td align="center"> <%out.println(unHashtagsTweetIndex.getID());%> </td>
				<td align="center"> <%out.println(unHashtagsTweetIndex.getTweet_id());%> </td>
				<td align="center"> <%out.println(unHashtagsTweetIndex.getHashtag_id());%> </td>
			</tr>
		<%
		}
		%>
	</table>
	<br/><br/><br/><br/><br/><br/><br/><br/><p>Fin tabla</p><br/>
	
	<h4  align="left"> TABLA HASHTAG_NEIGHBORS_INDEX </h4>
	<table  align="left" border="1">
		<tr  align="center">
			<td>ID</td>
			<td>HASHTAG_ID</td>
			<td>NEIGHBORS</td>
		</tr>
		<% 
		Hashtag_neighbors_index unHashtagNeighborsIndex = new Hashtag_neighbors_index();
		ArrayList<Hashtag_neighbors_index> treeSetHashtags5 = new Hashtag_neighbors_index().listadoHashtagNeighborsIndex();
		Iterator it5= treeSetHashtags5.iterator();
		while (it5.hasNext()){
			unHashtagNeighborsIndex = (Hashtag_neighbors_index)it5.next();
		%>
			<tr>
				<td align="center"> <%out.println(unHashtagNeighborsIndex.getID());%> </td>
				<td align="center"> <%out.println(unHashtagNeighborsIndex.getHashtag_id());%> </td>
				<td align="center"> <%out.println(unHashtagNeighborsIndex.getNeighbor());%> </td>

			</tr>
		<%
		}
		%>
	</table>
	<br/><br/><br/><br/><br/><br/><br/><br/><p>Fin tabla</p><br/>
	
	<h4  align="left"> TABLA URLS_TWEET_INDEX </h4>
	<table  align="left" border="1">
		<tr  align="center">
			<td>ID</td>
			<td>TWEET_ID</td>
			<td>URL</td>
		</tr>
		<% 
		Urls_tweet_index unUrlsTweetIndex = new Urls_tweet_index();
		ArrayList<Urls_tweet_index> treeSetHashtags6 = new Urls_tweet_index().listadoUrlsTweetIndex();
		Iterator it6= treeSetHashtags6.iterator();
		while (it6.hasNext()){
			unUrlsTweetIndex = (Urls_tweet_index)it6.next();
		%>
			<tr>
				<td align="center"> <%out.println(unUrlsTweetIndex.getID());%> </td>
				<td align="center"> <%out.println(unUrlsTweetIndex.getTweet_id());%> </td>
				<td align="center"> <%out.println(unUrlsTweetIndex.getUrl());%> </td>

			</tr>
		<%
		}
		%>
	</table>
	<br/><br/><br/><br/><br/><br/><br/><br/><p>Fin tabla</p><br/>
	
</body>
</html>