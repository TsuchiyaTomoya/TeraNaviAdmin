<%-- 
    Document   : CreateKey
    Created on : 2016/02/01, 11:37:10
    Author     : Masaki
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8">
    <title>登録キー生成のテスト画面</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
</head>
    <body>
        <h1>登録キー生成のテスト画面です</h1>
        <form action="front/keyCreate" method="post" id="keyForm">
            <input type="number" name="count"><br>
            ダウンロードオプション 登録キーをDLする<input type="checkbox" name="writeFlag" value="1" id="target"><br>
            <input type="submit" value="送信">
        </form>
        
        <script type="text/javascript">
            $(function(){
                $("#target").on("change",function(){
                    var target = $("#target");
		    
		    if($(target).prop("checked")){
			console.log("true");
			$("#keyForm").attr("action","front/writeKeyCreate");
		    }else{
			$("#keyForm").attr("action","front/KeyCreate");
		    }
                    
                });
            });
        </script>
    </body>
</html>
