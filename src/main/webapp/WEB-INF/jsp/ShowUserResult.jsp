
<%@ page
   contentType="text/html ; charset=UTF-8"
   pageEncoding="UTF-8"
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>検索結果</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>

	<script type="text/javascript">
        $(function(){
            $("#want").on("change",function(){
                var target = $("#want option:selected").attr("value");
				var text = $("#want option:selected").text();
                $("#lock").attr("action","/TeraNaviAdmin/front/"+target);
				$("#lock #sub").attr("value","対象のユーザアカウントを"+text);
            });
        });
    </script>

</head>
<body>
    <%-- ヘッダー部分のHTMLを読み込み --%>
    <jsp:include page="/WEB-INF/jsp/header.jsp"/>

    <div class="container">
	

		<div class="row">
			
			<table class="table table-striped">
				<thead>
					<tr>
						<th> </th>
						<th>ユーザ名</th>
						<th>DM本文</th>
						<th>DM送信リンク</th>
						<th>終了日時</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${result}">
						<tr>
							<td><input type="checkbox" name="target" value="${item.id}" form="lock"></td>
							<td><c:out value="${item.userName}" /></td>
							<form action="dmsend" method="post">
								<td><input type="text" name="messageBody"></td>>
								<input type="hidden" name="receiveUserId" value="${item.id}">
								<td><input type="submit" value="送信"></td>
							</form>
							<input type="hidden" name="status" value="1" form="lock">
							<td><input type="date" name="lockEnd" form="lock"></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
	 
			<form action="/TeraNaviAdmin/front/acLock" method="post" id="lock">
				<select id="want">
                   <option value="acLock" selected>ロック</option>
                   <option value="acDelete">削除</option>
				   <option value="acAuth">権限付与</option>
				</select>
				<input type="submit" id="sub" value="対象のユーザアカウントをロック">
			</form>
		</div><!--end row-->
    </div><!--end container-->
    <jsp:include page="/WEB-INF/jsp/footer.jsp"/>



</body>
</html>
