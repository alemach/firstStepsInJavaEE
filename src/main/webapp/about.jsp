<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Zaplanuj Jedzonko</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Charmonman:400,700|Open+Sans:400,600,700&amp;subset=latin-ext"
          rel="stylesheet">
    <link href='<c:url value="/css/style.css"/>' rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
</head>
<body>

<jsp:include page="header.jsp"/>

<div class="mt-4 ml-4 mr-4">
    <div class="row border-bottom border-3">
        <div class="col"><h3 class="color-header text-uppercase">O aplikacji</h3></div>
    </div>

    <section class="padding-large bg-light">

        <div id="carouselExampleControls" class="carousel slide main-slider" data-ride="carousel">
            <div class="carousel-inner container">
                <div class="carousel-item active">
                    <div class="container w-75 d-flex">
                        <div class="carousel-caption d-block">
                            <div class="stats">
                            <div class="dashboard-alerts">
                                <div class="alert-item alert-info">
                                    <i class="fas icon-circle fa-info-circle"></i>
                                    <span class="font-weight-bold">Liczba przepisów: ${allRecipes}</span>
                                </div>
                                <div class="alert-item alert-light">
                                    <i class="far icon-calendar fa-calendar-alt"></i>
                                    <span class="font-weight-bold">Liczba planów: ${allPlans}</span>
                                </div>
                                <div class="alert-item alert-light">
                                    <i class="fas fa-user-alt"></i>
                                    <span class="font-weight-bold">Liczba użytkowników: ${allUsers}</span>
                                </div>
                            </div>
                            </div>
                            <br>
                            <br>
                            <div class="onas">
                            <div class="col-13 text-left"><h5>Kuba Wasilewski <a href="https://github.com/k-wasilewski">@k-wasilewski</a> <a class="nav-link color-header" href="mailto:k.k.wasilewski@gmail.com">
                                k.k.wasilewski@gmail.com</a></h5></div>
                            <div class="col-13 text-left"><h5>Kuba Wziątka <a href="https://github.com/Firestart3rr">@Firestart3rr</a> <a class="nav-link color-header" href="mailto:kuba.wziatka@gmail.com">
                                kuba.wziatka@gmail.com</a></h5></div>
                            <div class="col-13 text-left"><h5>Aleksander Machaj <a href="https://github.com/alemach">@alemach</a> <a class="nav-link color-header" href="mailto:aleksander.machaj@gmail.com">
                                aleksander.machaj@gmail.com</a></h5></div>
                            <div class="col-13 text-left"><h5>Oskar Burdyna <a href="https://github.com/BACONDISPENSER">@BACONDISPENSER</a> <a class="nav-link color-header" href="mailto:mazaraudio@gmail.com">
                                mazaraudio@gmail.com</a></h5></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <jsp:include page="footer.jsp"/>

</body>
</html>
