<%@ page
   contentType="text/html ; charset=UTF-8"
   pageEncoding="UTF-8"
   import="ttc.bean.ArticleBean"
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>警告されたユーザー一覧</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
</head>
<body>
    <%-- ヘッダー部分のHTMLを読み込み --%>
    <jsp:include page="/WEB-INF/jsp/header.jsp"/>

    <div class="container">
        <div class="row">
            <h1>警告されたユーザー一覧</h1>
            <div id="users">
                <table class="table">
                    <thead>
                        <tr>
                            <th>
                                タイトル
                            </th>
                            <th>
                                URL
                            </th>
                            <th>
                                日時
                            </th>
                            <th>
                                内容
                            </th>
                            <th>
                                警告されたユーザ
                            </th>
                            <th>
                                警告したユーザ
                            </th>
                            <th>

                            </th>
                        </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>

            </div>
            <div id="caution">
            </div>

       </div><!--end row-->
    </div><!--end container-->
    <jsp:include page="/WEB-INF/jsp/footer.jsp"/>

    <script type="text/javascript">
    var ajaxSettings;
    var ajax;

    var nowId;

    $(function(){
        ajaxSettings = {
            type:'post',
            url:'/TeraNaviAdmin/front/showcaution',
            dataType:'json',
            data:null
        };

        loadCaution();
    });
    function loadCaution(){
        ajaxSettings.data = {
            ajax:"true",
        };

        ajaxSettings.success = function(data){

                var users = $("#users table tbody");

                users.empty();

                for(var i = 0;i < data.length;i++){
                    console.log(data[i].userId.mailAddress);

                    users.append("<tr>");

                    users.append("<td>"+data[i].title+"</td>");
                    users.append("<td>"+data[i].reportPageUrl+"</td>");
                    users.append("<td>"+data[i].date+"</td>");
                    users.append("<td>"+data[i].cautionBody+"</td>");
                    users.append("<td>"+data[i].userId.userName+"</td>");
                    users.append("<td>"+data[i].cautionUserId.userName+"</td>");
                    users.append("<td><button onclick='caution(\""+data[i].userId.mailAddress+"\");'>警告</button></td>");

                    users.append("</tr>");
                }

            }
        ajax = $.ajax(ajaxSettings);

    }

    function caution(address){
        console.log(address);
        $("#caution").html("<form action='/TeraNaviAdmin/front/replyContact' method='post' id='sendForm'>");
        $("#caution").append("<h1>警告メールフォーム</h1>");
        $("#caution").append("<input type='hidden' form='sendForm' name='toAddress' value="+address+">");
        $("#caution").append("件名：<input type='text' name='title' form='sendForm'>><br>");
        $("#caution").append("本文：<input type='text' name='mess' form='sendForm'>><br>");
        $("#caution").append("<input type='submit' value='送信' form='sendForm'>");
        $("#caution").append("</form>");
    }

    </script>

</body>
</html>
