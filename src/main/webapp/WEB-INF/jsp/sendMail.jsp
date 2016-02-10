<%@ page
   contentType="text/html ; charset=UTF-8"
   pageEncoding="UTF-8"
%>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>Maillerのテストページ</title>
    <!-- Latest compiled and minified CSS -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
    <script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="/TeraNavi/css/policy.css" rel="stylesheet" type="text/css">

</head>
<body class="hidden-md" data-spy="scroll">
    <%-- ヘッダー部分のHTMLを読み込み --%>
    <jsp:include page="/WEB-INF/jsp/header.jsp"/>

	<div id="mail">
		自分のアドレス<input type="text" id="address"><br>
		パスワード<input type="password" id="pass"><br>
		相手のアドレス<input type="text" id="toAddress"><br>
		件名<input type="text" id="title"><br>
		本文<br>
		<textarea id="mess"></textarea><br>
		<button onclick="send();">送信</button>
	</div>


    <jsp:include page="/WEB-INF/jsp/footer.jsp"/>

	<script>
		var ajaxSettings;
		var ajax;

		var nowId;

		$(function(){

			ajaxSettings = {
				type:'post',
				url:'/TeraNaviAdmin/front/mailSend',
				dataType:'json',
				data:null,
				success:function(data){

					var mail = $("#mail");
					mail.empty();

					mail.append("<p>"+data+"</p>");
				}
			};


		});

		function send(){

			ajaxSettings.data = {
				ajax:"true",
				address:$("#address").val(),
				toAddress:$("#toAddress").val(),
				pass:$("#pass").val(),
				title:$("#title").val(),
				mess:$("#mess").val()
			};

			ajax = $.ajax(ajaxSettings);

		}


	</script>
    

</body>
</html>
