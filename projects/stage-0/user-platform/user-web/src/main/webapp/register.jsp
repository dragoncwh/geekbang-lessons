<head>
<jsp:directive.include file="/WEB-INF/jsp/prelude/include-head-meta.jspf" />
	<title>My Register Page</title>
    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>
</head>
<body>
	<div class="container">
		<form class="form-signup" action="/register" method="post">
			<h1 class="h3 mb-3 font-weight-normal">用户注册</h1>

			<label for="email" class="sr-only">请输入电子邮件</label>
      <input type="email" name="email" id="email" class="form-control"
        placeholder="请输入电子邮件" required autofocus>

			<label for="username" class="sr-only">请输入用户名</label>
			<input type="text" id="username" name="username" class="form-control"
			  placeholder="请输入用户名" required autofocus>

			<label for="password" class="sr-only">请输入密码</label>
			<input type="password" id="password" name="password" class="form-control"
				placeholder="请输入密码" required>

			<button class="btn btn-lg btn-primary btn-block" type="submit">注册</button>

			<p class="mt-5 mb-3 text-muted">&copy; 2017-2021</p>
		</form>
	</div>
</body>