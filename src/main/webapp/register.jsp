<jsp:directive.page contentType="text/html; charset=UTF-8" />

<!DOCTYPE html>
<html lang="pt-br">

<head>

    <%@include file="base-head.jsp"%>

</head>

<body style="background-color:#f5f5f5;">

<div class="container">

    <div class="row">

        <div class="col-md-4 col-md-offset-4"
             style="margin-top:80px;">

            <div class="panel panel-danger">

                <div class="panel-heading text-center">

                    <h3 class="panel-title">
                        Workout Diary
                    </h3>

                </div>

                <div class="panel-body">

                    <h4 class="text-center">
                        Criar Conta
                    </h4>

                    <hr>

                    <form action="${pageContext.request.contextPath}/register"
                          method="POST">

                        <div class="form-group">

                            <label>Nome</label>

                            <input type="text"
                                   name="name"
                                   class="form-control"
                                   required>

                        </div>

                        <div class="form-group">

                            <label>Email</label>

                            <input type="email"
                                   name="email"
                                   class="form-control"
                                   required>

                        </div>

                        <div class="form-group">

                            <label>Senha</label>

                            <input type="password"
                                   name="password"
                                   class="form-control"
                                   required>

                        </div>

                        <button type="submit"
                                class="btn btn-danger btn-block">

                            Cadastrar

                        </button>

                    </form>

                    <hr>

                    <div class="text-center">

                        <a href="${pageContext.request.contextPath}/login.jsp"
                           class="btn btn-default">

                            Voltar para Login

                        </a>

                    </div>

                </div>

            </div>

        </div>

    </div>

</div>

</body>
</html>