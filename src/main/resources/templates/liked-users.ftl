<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon.ico">

    <title>Chosen individuals</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
          integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
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
            border-radius: 8px;
            padding: 4px;
        }

        .bt:hover {
            background-color: #1d2124;
            color: #fff;
            transition: background-color .5s;
        }

        .bt:hover {
            fill: #fff;
        }

        .pan {
            padding: 24px 0;
            position: fixed;
            top: 0;
            left: 50%;
            transform: translateX(-50%);
            width: 1024px;
            z-index: 10;
            display: flex;
            justify-content: flex-end;
            align-items: center;
        }

        .pan-nav {
            display: flex;
            align-items: center;
            gap: 10px;
        }

        .user-icon:hover, .logout-icon:hover {
            fill: #fff;
            transition: background-color .5s;
        }

    </style>
</head>
<body>

<header class="pan">
    <nav class="pan-nav">
        <a href="/users" class="bt link" style="display: flex; align-items: center;">
            <svg class="user-icon" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
                <path d="M12 0c-6.627 0-12 5.373-12 12s5.373 12 12 12 12-5.373 12-12-5.373-12-12-12zm7.753 18.305c-.261-.586-.789-.991-1.871-1.241-2.293-.529-4.428-.993-3.393-2.945 3.145-5.942.833-9.119-2.489-9.119-3.388 0-5.644 3.299-2.489 9.119 1.066 1.964-1.148 2.427-3.393 2.945-1.084.25-1.608.658-1.867 1.246-1.405-1.723-2.251-3.919-2.251-6.31 0-5.514 4.486-10 10-10s10 4.486 10 10c0 2.389-.845 4.583-2.247 6.305z"/>
            </svg>
        </a>
        <a href="/logout" class="bt link" style="display: flex; align-items: center;">
            <svg class="logout-icon" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
                <path d="M14 19v-.083c-1.178.685-2.542 1.083-4 1.083-4.411 0-8-3.589-8-8s3.589-8 8-8c1.458 0 2.822.398 4 1.083v-2.245c-1.226-.536-2.576-.838-4-.838-5.522 0-10 4.477-10 10s4.478 10 10 10c1.424 0 2.774-.302 4-.838v-2.162zm4-9.592l2.963 2.592-2.963 2.592v-1.592h-8v-2h8v-1.592zm-2-4.408v4h-8v6h8v4l8-7-8-7z"/>
            </svg>
        </a>
    </nav>
</header>

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
                                        <a href="/messages/${user.getId()}">
                                            <div class="avatar-img">
                                                <img class="img-circle" src=${user.photo} alt="photo"/>
                                            </div>
                                        </a>
                                    </td>
                                    <td class="align-middle">
                                        <a href="/messages/${user.getId()}" class="link">${user.name}</a>
                                    </td>
                                    <td class="align-middle">
                                        Builder Sales Agent
                                    </td>
                                    <td class="align-middle">
                                        Last Login: 6/10/2017<br><small class="text-muted">5 days ago</small>
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