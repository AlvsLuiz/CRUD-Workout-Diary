<jsp:directive.page contentType="text/html; charset=UTF-8" />

<%@ taglib prefix="c"
    uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="pt-br">

<head>

    <%@include file="base-head.jsp"%>

</head>

<body>

    <%@include file="nav-menu.jsp"%>

    <div id="container"
         class="container-fluid">

        <div class="page-header">

            <h3>

                <c:choose>

                    <c:when test="${not empty workout}">
                        Editar Treino
                    </c:when>

                    <c:otherwise>
                        Novo Treino
                    </c:otherwise>

                </c:choose>

            </h3>

        </div>

        <form action="${pageContext.request.contextPath}
            <c:choose>
                <c:when test='${not empty workout}'>
                    /workout/update
                </c:when>
                <c:otherwise>
                    /workout/insert
                </c:otherwise>
            </c:choose>"
            method="post">

            <c:if test="${not empty workout}">

                <input type="hidden"
                       name="id"
                       value="${workout.id}" />

            </c:if>

            <div class="row">

                <div class="form-group col-md-6">

                    <label>
                        Nome do treino
                    </label>

                    <input type="text"
                           class="form-control"
                           name="workoutName"
                           value="${workout.workoutName}"
                           required />

                </div>

                <div class="form-group col-md-3">

                    <label>
                        Duração (min)
                    </label>

                    <input type="number"
                           class="form-control"
                           name="durationMinutes"
                           value="${workout.durationMinutes}"
                           required />

                </div>

                <div class="form-group col-md-3">

                    <label>
                        Intensidade
                    </label>

                    <select class="form-control"
                            name="intensity"
                            required>

                        <option value="">
                            Selecione
                        </option>

                        <option value="Leve">
                            Leve
                        </option>

                        <option value="Média">
                            Média
                        </option>

                        <option value="Pesada">
                            Pesada
                        </option>

                    </select>

                </div>

            </div>

            <div class="row">

                <div class="form-group col-md-6">

                    <label>
                        Trainer
                    </label>

                    <input type="text"
                           class="form-control"
                           name="trainer"
                           value="${workout.trainer}"
                           required />

                </div>

                <div class="form-group col-md-6">

                    <label>
                        Usuário
                    </label>

                    <select class="form-control"
                            name="userId"
                            required>

                        <option value="">
                            Selecione um usuário
                        </option>

                        <c:forEach var="user"
                                   items="${users}">

                            <option value="${user.id}">

                                ${user.name}

                            </option>

                        </c:forEach>

                    </select>

                </div>

            </div>

            <hr/>

            <button type="submit"
                    class="btn btn-primary">

                Salvar

            </button>

            <a href="${pageContext.request.contextPath}/workouts"
               class="btn btn-default">

                Cancelar

            </a>

        </form>

    </div>

</body>

</html>