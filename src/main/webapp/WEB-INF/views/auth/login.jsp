<%--
  Created by IntelliJ IDEA.
  User: Sumpark
  Date: 03/03/2022
  Time: 17:18
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/common/head.jsp"%>


<body class="hold-transition login-page">
<div class="login-box">
    <div class="login-logo">
         <b>Admin</b>LTE
    </div>
    <div class="login-box-msg">



        <c:if  test="${param.error  == true }">
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                Coordonnees invalides
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            </c:if>


    </div>
    <!-- /.login-logo -->
    <div class="card">
        <div class="card-body login-card-body">
            <p class="login-box-msg">Sign in to start your session</p>

            <form  method="post">

                <div class="input-group mb-3">
                    <input type="text" class="form-control" placeholder="Username" name="username">
                    <div class="input-group-append">
                        <div class="input-group-text">
                            <span class="fas fa-user"></span>
                        </div>
                    </div>
                </div>
                <div class="input-group mb-3">
                    <input type="password" class="form-control" placeholder="Password" name="password">
                    <div class="input-group-append">
                        <div class="input-group-text">
                            <span class="fas fa-lock"></span>
                        </div>
                    </div>
                </div>
                <div class="row">

                    <!-- /.col -->
                    <div class="col-4 offset-4">
                        <button type="submit" class="btn btn-primary btn-block ">Sign In</button>
                    </div>
                    <!-- /.col -->
                </div>
            </form>

        </div>
        <!-- /.login-card-body -->
    </div>
</div>
<!-- /.login-box -->
<%@ include file="/WEB-INF/views/common/js_imports.jsp" %>

</body>
</html>
