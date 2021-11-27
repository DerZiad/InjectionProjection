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
					<li class="nav-item"><a href="formulaire">HOME</a></li>
					<li class="nav-item"><a href="posts.html">Posts</a></li>
					<li class="nav-item"><a href="logout">Logout</a></li>
				</ul>
			</nav>
			<a class="bar-icon" id="iconBar" onclick="hideIconBar()"><i
				class="fa fa-bars"></i></a>
			<div class="brand">Mes Messages</div>
		</div>
		<!--SearchBox Section-->
		<div class="search-box">
			<div>
				<select name="" id="">
					<option value="Everything">BANQUE</option>
				</select>
				<form action="forumprive" method="post">
					<input type="text" name="fprv" placeholder="mes messages ...">
					<input type="submit" value="Submit" />
				</form>
			</div>
		</div>
	</header>
	<div class="container">
		<!--Navigation-->
		<div class="navigate">
			<span><a href="">FORUM _ MES MESSAGES</a> >> <a href="">Financiers</a>
				>> <a href="">Forum banque et argent</a></span>
		</div>

		<div class="topic-container">
			<!--Original thread-->

			<c:set var="bool" value="${bool}" />
			<c:if test="${bool}">
			<c:out value="${bool}"></c:out>
				<div class="head">
					<div class="authors">Author</div>
					<div class="content">Topic: Compte Bancaire (Read 1325 Times)</div>
				</div>
				<c:forEach items="${user}" var="u">
					<div class="body">
						<div class="authors">
							<div class="username">${u.username}</div>
						</div>
						<div class="content">
							<div class="username">
								<c:out value="BANQUE : " />
								${u.nomBanque }
							</div>
						</div>
						<div class="content">
							<div class="text">
								<c:out value="BIN : " />
								${u.bin }
							</div>
						</div>
						<div class="content">
							<div class="text">
								<c:out value="CVV : " />
								${u.cvv }
							</div>
						</div>
						<div class="content">
							<div class="text">
								<c:out value="Date d'Exp : " />
								${u.date }
							</div>
						</div>
					</div>
				</c:forEach>
			</c:if>
		</div>
		<c:if test="${ bool eq false}">
			<div class="limiter">
				<div class="container-table100">
					<div class="wrap-table100">
						<div class="table100 ver1 m-b-110">
							<div class="table100-head">
								<table>
									<thead>
										<tr class="row100 head">
											<th class="cell100 column1">Username</th>
											<th class="cell100 column2">Password</th>
											<th class="cell100 column3">Bin</th>
											<th class="cell100 column4">CVV</th>
											<th class="cell100 column5">Date</th>
										</tr>
									</thead>
								</table>
							</div>

							<div class="table100-body js-pscroll">
								<table>
									<tbody>
										<tr class="row100 body">
											<td class="cell100 column1">${user.username}</td>
											<td class="cell100 column2">${user.password }</td>
											<td class="cell100 column3">${user.bin }</td>
											<td class="cell100 column4">${user.cvv }</td>
											<td class="cell100 column5">${user.date }</td>
										</tr>

									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</c:if>


		<!--Topic Section-->
		<div class="topic-container">
			<!--Original thread-->
			<div class="head">
				<div class="authors">Message</div>
				<div class="content">Topic: Compte Bancaire (Read 1325 Times)</div>
			</div>
			<c:forEach items="${message}" var="m">
				<div class="body">
					<div class="authors">
						<div class="username">${m.getSujetMessage()}</div>
					</div>
					<div class="content">
						<div class="username">
							<c:out value="Message : " />
							${m.message }
						</div>
					</div>

				</div>
			</c:forEach>
		</div>
		<div class="bodis">
			<form action="formulaire" method="POST">
				<input type="text" name="message" value="" /> <input type="submit"
					value="Send" />
			</form>
		</div>


	</div>

	<footer>
		<span>&copy; M O U T C H O U | All Rights Reserved</span>
	</footer>
			<script src="main.js"></script>
	
</body>
</html>