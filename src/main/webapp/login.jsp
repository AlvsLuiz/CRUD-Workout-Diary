<jsp:directive.page contentType="text/html; charset=UTF-8" />

<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Workout Diary - Login</title>

<link rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">

</head>

<body class="bg-light">

<div class="container">

    <div class="row justify-content-center mt-5">

        <div class="col-md-4">

            <div class="card shadow">

                <div class="card-header bg-secondary text-white text-center">

                    <h3 class="mb-0">
                        Workout Diary
                    </h3>

                </div>

                <div class="card-body">

					<h4 class="text-center mb-4">
                        Login
                    </h4>
                    
                    <form action="${pageContext.request.contextPath}/login"
                          method="post">

                        <div class="mb-3">
                            <label>Email</label>

                            <input type="email"
                                   name="user_login"
                                   class="form-control"
                                   required>
                        </div>

                        <div class="mb-3">
                            <label>Senha</label>

                            <input type="password"
                                   name="user_pw"
                                   class="form-control"
                                   required>
                        </div>

                        <button class="btn btn-danger w-100">
                            Entrar
                        </button>

                    </form>

                    <div class="text-center mt-3">

                        <a href="${pageContext.request.contextPath}/register.jsp" class="text-danger">
                            Criar conta
                        </a>

                    </div>

                    <%
                    String erro = request.getParameter("erro");

                    if ("true".equals(erro)) {
                    %>

                    <div class="alert alert-danger mt-3">
                        Email ou senha inválidos.
                    </div>

                    <%
                    }
                    %>

                </div>

            </div>

        </div>

    </div>

</div>

</body>
</html>