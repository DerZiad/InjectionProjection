<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Forum</title>
<link rel="stylesheet" href="style.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Titillium+Web:ital@1&display=swap"
	rel="stylesheet">
</head>

<body>
	<header>
		<!--NavBar Section-->
		<div class="navbar">
			<nav class="navigation hide" id="navigation">
				<span class="close-icon" id="close-icon" onclick="showIconBar()"><i
					class="fa fa-close"></i></span>
				<ul class="nav-list">
					<li class="nav-item"><a href="forums.html">Forums</a></li>
					<li class="nav-item"><a href="posts.html">Posts</a></li>
					<li class="nav-item"><a href="detail.html">Detail</a></li>
				</ul>
			</nav>
			<a class="bar-icon" id="iconBar" onclick="hideIconBar()"><i
				class="fa fa-bars"></i></a>
			<div class="brand">My Forum</div>
		</div>
		<!--SearchBox Section-->
		<div class="search-box">
			<div>
				<select name="" id="">
					<option value="Everything">Everything</option>
					<option value="Titles">Titles</option>
					<option value="Descriptions">Descriptions</option>
				</select> <input type="text" name="q" placeholder="search ...">
				<button>
					<i class="fa fa-search"></i>
				</button>
			</div>
		</div>
		<div class="container">
			<!--Navigation-->
			<div class="navigate">
				<span><a href="">MyForum - Forums</a> >> <a href="">random
						subforum</a> >> <a href="">random topic</a></span>
			</div>

			<!--Topic Section-->
			<div class="topic-container">
				<!--Original thread-->
				<div class="head">
					<div class="authors">Author</div>
					<div class="content">Topic: random topic (Read 1325 Times)</div>
				</div>
				<c:forEach items="${messages}" var="message">
					<div class="body">
						<div class="authors">
							<div class="username">${message.user.username }</div>
						</div>
						<div class="content">
							<div class="username">${message.message }</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<div class="bodis">
				<form action="formulaire" method="POST">
					<input type="text" name="message" value=""/>
					<input type="submit" value="Send" />
				</form>
			</div>


		</div>
		<footer>
			<span>&copy; Selmi Abderrahim | All Rights Reserved</span>
		</footer>
</body>
</html>