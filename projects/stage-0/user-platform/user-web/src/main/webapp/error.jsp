<head>
<jsp:directive.include
	file="/WEB-INF/jsp/prelude/include-head-meta.jspf" />
<title>发生错误</title>
</head>
<body>
<div class="container">
  <h2 class="h3 mb-3 font-weight-normal">发生错误</h2>

  <%
    String message = "";
    if (request.getAttribute("error_msg") != null) {
      message = " : " + request.getAttribute("error_msg").toString();
    }
    out.print("发生错误" + message);
  %>
</div>
</body>