<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon.ico">

    <title>People list</title>
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
        }
    </style>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-8 offset-2">
            <div class="panel panel-default user_panel">
                <div class="panel-heading">
                    <h3 class="panel-title">User List</h3>
                </div>
                <div class="panel-body">
                    <div class="table-container">
                        <table class="table-users table" border="0">
                            <tbody>
                            <#list users as user>
                                <tr>
                                    <td width="10">
                                        <a href="/messages/${user.name}">
                                            <div class="avatar-img">
                                                <img class="img-circle" src=${user.photo} alt="photo" />
                                            </div>
                                        </a>
                                    </td>
                                    <td class="align-middle">
                                        <a href="/messages/${user.name}" class="link">${user.name}</a>
                                    </td>
                                    <td class="align-middle">
                                        Builder Sales Agent
                                    </td>
                                    <td  class="align-middle">
                                        Last Login:  6/10/2017<br><small class="text-muted">5 days ago</small>
                                    </td>
                                </tr>
                            <#else>
                                <tr>
                                    <td class="align-middle">
                                        No one has been liked
                                    </td>
                                    <td class="align-middle">
                                        <a href="/users" class="bt link">Try again</a>
                                    </td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>