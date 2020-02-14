<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: woobofett
  Date: 17.12.2019
  Time: 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Zaplanuj Jedzonko</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Charmonman:400,700|Open+Sans:400,600,700&amp;subset=latin-ext"
          rel="stylesheet">
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
          integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
</head>
<body>

<section class="newsletter-section padding-small">
    <div class="container">
        <div class="row">
            <div class="col">
                <h1>Szukasz przepisu?</h1>
            </div>
            <div class="col-5">
                <div class="input-group mb-3">
                    <form method="post" action="/searchrecipebyname" id="searchrecipebyname">
                        <input type="text" name="searchedname" class="form-control border-0 rounded-0" placeholder=""
                        >
                    </form>
                    <div class="input-group-append">
                        <input type="submit" value="Szukaj" form="searchrecipebyname"
                               class="input-group-text btn-color border-0 rounded-0"/>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<section class="padding-medium story bg-light" id="about">
    <div class="container d-flex justify-content-center align-items-center">
        <c:if test="${not empty recipes}">
            <table class="table border-bottom schedules-content">
                <thead>
                <tr class="d-flex text-color-darker">
                    <th scope="col" class="col-1">ID</th>
                    <th scope="col" class="col-2">NAZWA</th>
                    <th scope="col" class="col-7">OPIS</th>
                    <th scope="col" class="col-2 center">AKCJE</th>
                </tr>
                </thead>
                <tbody class="text-color-lighter">
                <c:forEach items="${recipes}" var="recipe">
                    <tr class="d-flex">
                        <th scope="row" class="col-1">${recipe.id}</th>
                        <td class="col-2">${recipe.name}</td>
                        <td class="col-7">${recipe.description}</td>
                        <td class="col-2 d-flex align-items-center justify-content-center flex-wrap">
                            <a href="/recipe/details?id=${recipe.id}" class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
    </div>
</section>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
        integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
</body>
</html>
