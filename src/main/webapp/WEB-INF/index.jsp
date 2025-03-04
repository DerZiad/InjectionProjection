<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="author" content="colorlib.com">
<link href="https://fonts.googleapis.com/css?family=Poppins"
	rel="stylesheet" />
<link href="<c:url value="/css/mainhome.css"/>" rel="stylesheet" />
</head>
<body>
	<div class="s01">
		<form action="home" method="GET">
			<fieldset>
				<legend>D�couvrer ZABank</legend>
			</fieldset>

			<p style="color:white;font-size:19px;"> Bienvenue sur le forum du droit bancaire. Ici vous pourrez
				discuter de vos probl�mes d'argent, des vos cr�dits et
				remboursements ainsi que de vos probl�mes avec votre banque. La
				communaut� de ZABank, constitu�e de personnes qui ont eu le m�me
				genre de probl�mes que vous, est l� pour vous aider, et nos avocats
				et juristes pourront aussi vous pr�ter main forte, alors allez-y,
				cliquez et posez votre question !</p>
			<div class="inner-form">
				<div class="input-field third-wrap">
					<a class="btn-search" type="button" href="login">Se connecter</a>
				</div>

				<div class="input-field third-wrap">
					<a class="btn-search" type="button" href="signup">Cr�er un
						compte</a>
				</div>

			</div>
			<div class="inner-form">
				<div class="input-field first-wrap">

					<!-- XSS -->

					<!-- <input id="search" type="text" name="search" value="${search}"
						placeholder="Chercher � propos de ca" />
						-->
					<input id="search" type="text" name="search" value="<c:out value="${search}"/>"
						placeholder="Chercher � propos de ca" />
				</div>
				<div class="input-field third-wrap">
					<input type="submit" class="btn-search" type="button"
						value="search" />
				</div>
			</div>
		</form>
	</div>
</body>
<!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>
