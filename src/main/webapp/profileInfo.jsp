	<!-- JSTL includes -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Dashboard</title>
		<style>
			h1{
				font-family:"Arial";
				font-size:40px;
				text-align:center;
			}
			.disPlay{
				font-size:28px;
				font-family:"Arial";
				float: right;
			}
			.disPlay2{
				font-size:20px;
				font-family:"Arial";
				float: right;
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
				float: right;
			}
			.prof{
				float: right;
				margin: 15px;
				margin-left:0;
				background-color:lightblue;
				padding:5px 0;
				width:90px;
			}
			.pages{
				margin: 15px;
				margin-right:0;
				background-color:lightblue;
				padding:5px 0;
				width:90px;
			}
			.pages2{
				float: right;
				margin: 15px;
				margin-left:0;
				background-color:lightblue;
				border:inset;
				padding:5px 0;
				width:90px;
			}
			button{
				margin-left: 15px;
				margin-bottom: 15px;
				margin-top: 15px;
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
		
		<form class="formy" action="UpdateUser" method="post" style="background-color:lightblue">
			<div class="disPlay">
				User: ${userPoster.userID}
			</div>
			<br /> <br /> <br />
			<div class="disPlay2" id="isAdmin">
				Account Type: ${userPoster.isAdmin}
			</div>
			<br /> <br />
			<input class="inPut" type="text" id="userEmail" name="userEmail" placeholder="Add an email" value="${userPoster.userEmail}" />
			<label class="inPut" for="userEmail">Email:</label>
			<br /> <br />
			<input class="inPut" type="password" id="userPass" name="userPass" value="${userPoster.userPass}" />
			<label class="inPut" for="userPass">Password:</label>
			<br /> <br />
			<input type="hidden" id="userID" name="userID" value="${userPoster.userID}">
			<button class=prof>Update Info</button>
		</form>
	</body>
</html>