<jsp:directive.page contentType="text/html; charset=UTF-8" />

<!DOCTYPE html>
<html lang="pt-br">

<head>

    <meta charset="UTF-8">

    <title>Workout Diary - Cadastro</title>

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
                        Criar Conta
                    </h4>

                    <form action="${pageContext.request.contextPath}/register"
                          method="POST">

                        <div class="mb-3">

                            <label class="form-label">
                                Nome
                            </label>

                            <input type="text"
                                   name="name"
                                   class="form-control"
                                   required>

                        </div>

                        <div class="mb-3">

                            <label class="form-label">
                                Email
                            </label>

                            <input type="email"
                                   name="email"
                                   class="form-control"
                                   required>

                        </div>

                        <div class="mb-3">

                            <label class="form-label">
                                Senha
                            </label>

                            <input type="password"
                                   name="password"
                                   class="form-control"
                                   required>

                        </div>

                        <button type="submit"
                                class="btn btn-danger w-100">

                            Cadastrar

                        </button>

                    </form>

                    <div class="text-center mt-3">

                        <a href="${pageContext.request.contextPath}/login.jsp"
                           class="text-danger text-decoration-none">

                            Voltar para Login

                        </a>

                    </div>

                    <%
                    String erro = request.getParameter("erro");

                    if ("true".equals(erro)) {
                    %>

                    <div class="alert alert-danger mt-3">

                        Erro ao cadastrar usuário.

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