<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon.ico">

    <title>Like page</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <!-- Bootstrap core CSS -->
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="/static/css/style.css">
    <style>
        .link, .link:hover {
            text-decoration: none;
            color: #1d2124;
        }
        .bt {
            box-sizing: border-box;
            color: #1d2124;
            border: 1px solid #1d2124;
            border-radius: 8px; padding: 4px;
        }
        .bt:hover {
            background-color: #1d2124;
            color: #fff;
            transition: background-color .5s;
        }
        .pan {
            padding: 32px 0;
            position: fixed;
            top: 0;
            left: 50%;
            transform: translateX(-50%);
            width: 768px;
            display: flex;
            justify-content: flex-end;
            align-items: center;
        }
        .pan-nav {
            display: flex;
            align-items: center;
            gap: 10px;
        }
        .like-icon:hover {
            fill: #fff;
            transition: background-color .5s;
        }
    </style>
</head>
<body style="background-color: #f5f5f5;">

<header class="pan">
    <nav class="pan-nav">
        <a href="/liked" class="bt link" style="display: flex; align-items: center;">
            <svg class="like-icon" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path d="M17.516 3c2.382 0 4.487 1.564 4.487 4.712 0 4.963-6.528 8.297-10.003 11.935-3.475-3.638-10.002-6.971-10.002-11.934 0-3.055 2.008-4.713 4.487-4.713 3.18 0 4.846 3.644 5.515 5.312.667-1.666 2.333-5.312 5.516-5.312zm0-2c-2.174 0-4.346 1.062-5.516 3.419-1.17-2.357-3.342-3.419-5.515-3.419-3.403 0-6.484 2.39-6.484 6.689 0 7.27 9.903 10.938 11.999 15.311 2.096-4.373 12-8.041 12-15.311 0-4.586-3.414-6.689-6.484-6.689z"/></svg>
        </a>
        <a href="/logout" class="bt link">Logout</a>
    </nav>
</header>

<div class="col-4 offset-4">
    <div class="card">
        <div class="card-body">
            <div class="row">
                <div class="col-12 col-lg-12 col-md-12 text-center">
                    <img src=${photo} alt="" class="mx-auto rounded-circle img-fluid">
                    <h3 class="mb-0 text-truncated">${name}</h3>
                    <br>
                </div>
                <form method="POST" action="/users" class="liked-page__form">
                    <div class="col-12 col-lg-6" >
                        <input type="hidden" name="id" value=${id}>
                        <button type="submit" name="answer" value="no" class="btn btn-outline-danger btn-block"><span class="fa fa-times"></span> Dislike</button>
                    </div>
                    <div class="col-12 col-lg-6">
                        <input type="hidden" name="id" value=${id}>
                        <button type="submit" name="answer" value="yes" class="btn btn-outline-success btn-block"><span class="fa fa-heart"></span> Like</button>
                    </div>
                </form>
                <!--/col-->
            </div>
            <!--/row-->
        </div>
        <!--/card-block-->
    </div>
</div>

</body>
</html>