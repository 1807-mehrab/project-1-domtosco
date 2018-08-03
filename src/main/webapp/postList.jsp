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
		<script type="text/javascript">
			function doFlag(theID) {
				System.out.println("starting");
				try {
					System.out.println("trying");
					var myJSONObject = {"postID" : theID};
					var toServer = myJSONObject.toString();
					var xhttp = new XMLHttpRequest();
					xhttp.open("GET", "FlagPost", true);
					xhttp.send(toServer);
					System.out.println("tried");
				} catch(err) {
					System.out.println("failed");
					alert(err.message);
				}
			}
		</script>
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
			<h2><c:out value="${pageHead}" /></h2>
		</div>
		
		<c:forEach var="postIt" items="${postSet}" varStatus="status">
			<div class="boxyBox" style="background-color:lightblue">
				<h3><c:out value="posted by ${postIt.userID}" /></h3>
				<h2><c:out value="${postIt.txtCnt}" /></h2>
				<h3><c:out value="Topic: ${threadSet[status.index].threadTopic}" /></h3>
				<br />
				<form class="formy2" action="ViewThread" method="get">
					<input type="hidden" name="threadID" value="${threadSet[status.index].threadID}">
					<button class="pages">View</button>
				</form>
				
				<form class="formy2" action="FlagPost" method="post">
					<input type="hidden" name="postID" value="${postIt.postID}">
					<input type="hidden" name="flagged" value="${flagged}">
					<input type="hidden" name="userName" value="${postIt.userID}">
					<button class="pages">Flag</button>
				</form>
				
				<form class="formy2" action="EditReply" method="get">
					<input type="hidden" name="postID" value="${postIt.postID}">
					<button class="pages">Edit</button>
				</form>
			</div>
			<br />
		</c:forEach>
	</body>
</html>