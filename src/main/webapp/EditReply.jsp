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
				display:inline;
			}
			h3{
				font-family:"Arial";
				font-size:"15px";
				display:inline;
			}
			.boxyBox{
				border: 1px solid black;
				overflow: auto;
			}
			.disPlay2{
				font-size:20px;
				font-family:"Arial";
			}
			.formy{
				padding-right: 15px;
				padding-top: 15px;
				overflow: auto;
			}
			.formy2{
				display:inline;
			}
			.inPut{
				font-family:"Arial";
				font-size:20px;
				padding-left:15px;
			}
			.inPut2{
				font-family:"Arial";
				font-size:20px;
				width:80%;
				padding-left:15px;
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
		
		<br />
		
		<form class="formy" action="EditReply" method="post" style="background-color:lightblue">
			<div class="disPlay2">Thread: ${threadTop}</div>
			<br />
			<div class="disPlay2">Posted by ${editPost.userID}</div>
			<br />
			<label class="inPut" for="txtContent">Post: </label>
			<input class="inPut2" type="text" id="txtContent" name="txtContent" value="${editPost.txtCnt}">
			<input type="hidden" id="postID" name="postID" value="${editPost.postID}">
			<br />
			<button class="pages">Edit</button>
		</form>
		
		<form class="formy2" action="DeleteReply" method="post">
			<input type="hidden" id="postID" name="postID" value="${editPost.postID}">
			<button class="pages">Delete</button>
		</form>
		
	</body>
</html>