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
        <div id="top" class="row">
             
            <div class="col-md-3">
                <h3>Treinos</h3>
            </div>
            
            <div class="col-md-3">
                <a href="${pageContext.request.contextPath}/workout/form"
                   class="btn btn-primary pull-right h2">
                    <span class="glyphicon glyphicon-plus"></span>
                    Novo Treino</a>
            </div>
        </div>
        
        <hr/>
        <div id="list" class="row">
            <div class="table-responsive col-md-12">
                <table class="table table-striped" cellspacing="0" cellpadding="0">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Treino</th>
                            <th>Duração</th>
                            <th>Intensidade</th>
                            <th>Trainer</th>
                            <th>Usuário</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="workout" items="${workouts}">
                            <tr>
                                <td>${workout.id}</td>
                                <td>${workout.workoutName}</td>
                                <td>${workout.durationMinutes} min</td>
                                <td>${workout.intensity}</td>
                                <td>${workout.trainer}</td>
                                <td>${workout.user.name}</td>
                                
                                <td class="actions">
                                    <a class="btn btn-warning btn-xs"
                                       href="${pageContext.request.contextPath}/workout/update?id=${workout.id}">
                                        <span class="glyphicon glyphicon-edit"></span>
                                    </a>
                                    <form action="${pageContext.request.contextPath}/workout/delete" method="post"
                                          style="display:inline;">
                                        <input type="hidden" name="id" value="${workout.id}" />
                                        <button type="submit" class="btn btn-danger btn-xs">
                                            <span class="glyphicon glyphicon-trash"></span>
                                        </button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                
            </div>
        </div>
    </div>
</body>
</html>
