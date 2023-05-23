<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon.ico">

    <title>Chat</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
          integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <!-- Bootstrap core CSS -->
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="/static/css/style.css">
</head>
<body>

<div class="container">
    <div class="row">
        <div class="chat-main col-6 offset-3">
            <div class="col-md-12 chat-header">
                <div class="row header-one text-white p-1">
                    <div class="col-md-6 name pl-2">
                        <i class="fa fa-comment"></i>
                        <h6 class="ml-1 mb-0">${guestName}</h6>
                    </div>
                </div>
                <div class="row header-two w-100">
                    <div class="col-md-6 options-left pl-1">
                    </div>
                    <div class="col-md-6 options-right text-right pr-2">
                        <i class="fa fa-cog"></i>
                    </div>
                </div>
            </div>
            <div class="chat-content" >
                <div class="col-md-12 chats pt-3 pl-2 pr-3 pb-3" style="height: 600px; overflow: auto">
                </div>

                <#--               `SEND MESSAGE FIELD block start`-->
                <form method="POST" action="/messages">
<#--                <form method="POST" action="/messages/${guestId}">-->
                    <div class="col-md-12 p-2 msg-box border border-primary">
                        <div class="row">
                            <div class="col-md-2 options-left">
                                <i class="fa fa-smile-o"></i>
                            </div>
                            <div class="col-md-7 pl-0">
                                <input type="hidden" name="idSender" value=${hostId}>
                                <input type="hidden" name="idReceiver" value=${guestId}>
                                <input type="text" name="body" class="border-0" placeholder=" Send message">
                            </div>
                            <div class="col-md-3 text-right options-right">
                                <i class="fa fa-picture-o mr-2"></i>
                            </div>
                        </div>
                    </div>
<#--                    <div class="col-md-12 p-2 msg-box border border-primary">-->
                        <div class="row">
                            <div class="col-md-6 options-left">
                                <button type="submit" name="submit" value="submit" class="btn btn-outline-info btn-block">SUBMIT</button>
                            </div>
                        </div>
<#--                    </div>-->
                </form>
                <#--               `SEND MESSAGE FIELD block end`-->

            </div>
        </div>
    </div>
</div>

</body>
</html>