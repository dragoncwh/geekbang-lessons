<head>
<jsp:directive.include
	file="/WEB-INF/jsp/prelude/include-head-meta.jspf" />
<title>My Home Page</title>
</head>
<body>
	<div class="container-lg">
  		<!-- Content here -->
  		<!-- Hello, <%= request.getAttribute("login_name") %> 2021 -->
  		<%
  		  String username = "World";
  		  Cookie[] cookies = request.getCookies();
  		  if (request.getAttribute("login_name") != null) {
  		    username = request.getAttribute("login_name").toString();
  		  } else if (cookies != null) {
  		    for (int i = 0; i < cookies.length; i++) {
             if (cookies[i].getName().equals("username")) {
               username = cookies[i].getValue();
             }
          }
  		  }
  		  out.print("Hello, " + username + " 2021");
  		%>
  	</div>
</body>