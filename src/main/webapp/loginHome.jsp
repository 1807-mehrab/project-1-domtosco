	<!-- JSTL includes -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Login</title>
		<style>
			h1{
				font-family:"Arial";
				font-size:40px;
				text-align:center;
			}
			input{
				font-family:"Arial";
				font-size:20px;
				margin-top:15px;
				margin-left:15px;
			}
			button{
				margin-left: 15px;
				margin-bottom: 15px;
				margin-top: 15px;
			}
			.pages{
				margin: 15px;
				margin-right:0;
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
		
		<form action="LoginServlet" method="get" style="background-color:lightblue">
			<div>
				<input type="text" id="userID" name="userID" placeholder="Username" />
				<br />
				<input type="text" id="userPass" name="userPass" placeholder="Password" />
			</div>
			<button class="pages">Log in</button>
		</form>
	</body>
</html>