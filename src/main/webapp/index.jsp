<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="author" content="colorlib.com">
    <link href="https://fonts.googleapis.com/css?family=Poppins" rel="stylesheet" />
    <link href="css/mainhome.css" rel="stylesheet" />
  </head>
  <body>
    <div class="s01">
      <form>
        <fieldset>
          <legend>D�couvrer ZABank</legend>
        </fieldset>
        <div class="inner-form">
          <div class="input-field third-wrap">
            <a class="btn-search" type="button" href="login">Se connecter</a>
          </div>
      
          <div class="input-field third-wrap">
            <a class="btn-search" type="button" href="SignUp">Cr�er un compte</a>
          </div>
          
        </div>
        <div class="inner-form">
          <div class="input-field first-wrap">
            <input id="search" type="text" placeholder="What are you looking for?" />
          </div>
          <div class="input-field second-wrap">
            <input id="location" type="text" placeholder="location" />
          </div>
          <div class="input-field third-wrap">
            <button class="btn-search" type="button">Search</button>
          </div>
        </div>
      </form>
    </div>
  </body><!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>
