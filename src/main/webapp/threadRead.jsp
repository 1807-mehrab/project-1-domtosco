	<!-- JSTL includes -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Discussion</title>
		<style>
			h1{
				font-family:"Arial";
				font-size:40px;
				text-align:center;
			}
			h2{
				font-family:"Arial";
				font-size:"20px";
				vertical-align:super;
			}
			h3{
				font-family:"Arial";
				font-size:"15px";
				vertical-align:sub;
				display:inline;
			}
			.boxyBox{
				border: 1px solid black;
				overflow: auto;
			}
			.formy2{
				display:inline;
			}
			.pages{
				margin: 15px;
				margin-right:0;
				background-color:lightblue;
				padding:5px 0;
				width:90px;
			}
			.prof{
				float: right;
				margin: 15px;
				margin-left:0;
				background-color:lightblue;
				padding:5px 0;
				width:90px;
			}
		</style>
	</head>
	
	<body>
		<div style="background-color:lightblue">
			<h1>Film Forums</h1>
		</div>
		
		<div style="background-color:lightblue">
			<form class="formy2" action="ShowTopics" method="get">
				<button class="pages">Topics</button>
			</form>
			<form class="formy2" action="ShowPosts" method="get">
				<input type="hidden" name="flagged" value="false">
				<input type="hidden" name="userName" value="${userPoster.userID}">
				<button class="pages">Your Posts</button>
			</form>
			<form class="formy2" action="ShowUsers" method="get">
				<button class="pages">User List</button>
			</form>
			<form class="formy2" action="ShowPosts" method="get">
				<input type="hidden" name="flagged" value="true">
				<input type="hidden" name="userName" value="${userPoster.userID}">
				<button class="pages">Moderate</button>
			</form>
			<form class="formy2" action="LogoutServlet" method="get">
				<button class="prof">Log out</button>
			</form>
			<form class="formy2" action="LoginServlet" method="post">
				<button class="prof">Profile</button>
			</form>
		</div>
		
		<div style="background-color:lightblue">
			<h2><c:out value="Topic: ${frmThread.threadTopic}" /></h2>
			<form class="formy2" action="MakeReply" method="get">
				<button class="pages">Reply</button>
				<input type="hidden" name="threadCode" value="${frmThread.threadID}">
			</form>
		</div>
		
		<c:forEach var="postIt" items="${postSet}">
			<div class="boxyBox" style="background-color:lightblue">
				<h3><c:out value="posted by ${postIt.userID}" /></h3>
				<h2><c:out value="${postIt.txtCnt}" /></h2>
				<form class="formy2" action="FlagPost" method=get>
					<input type="hidden" name="postID" value="${postIt.postID}">
					<input type="hidden" name="threadID" value="${postIt.threadID}">
					<button class="pages">Flag</button>
				</form>
			</div>
			<br />
		</c:forEach>
	</body>
</html>