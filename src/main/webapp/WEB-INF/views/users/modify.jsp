<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <!-- Left side column. contains the logo and sidebar -->
    <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                Utilisateur
            </h1>
            <div class="row" >
                <div class="col-md-4">


                        <c:if test="${success == false }">
                            <div class="alert alert-danger " style="margin-top: 5%">
                                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>

                                <strong>Erreur :</strong> Veuiller verifier tous les champs.
                            </div>
                        </c:if>

                </div>
            </div>
        </section>



        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <!-- Horizontal Form -->
                    <div class="box">
                        <!-- form start -->
                        <form class="form-horizontal" method="post" >
                            <div class="box-body">
                                <div class="form-group">
                                    <label for="last_name" class="col-sm-2 control-label">Nom</label>

                                    <div class="col-sm">
                                        <input type="text" class="form-control" id="last_name" name="last_name" placeholder="Nom" value="${client.nom}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="first_name" class="col-sm-2 control-label">Prenom</label>

                                    <div class="col-sm">
                                        <input type="text" class="form-control" id="first_name" name="first_name" placeholder="Prenom" value="${client.prenom}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="email" class="col-sm-2 control-label">Email</label>

                                    <div class="col-sm">
                                        <input  type="email" class="form-control" id="email" name="email" placeholder="Email" value="${client.email}">

                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="date_de_naissance" class="col-sm-2 control-label">Date de naissance</label>

                                    <div class="col-sm">
                                        <input type="date"  class="form-control" id="date_de_naissance" name="date_de_naissance" value="${client.naissance}"
                                               data-inputmask="'alias': 'dd/mm/yyyy'" data-mask required>

                                    </div>
                                </div>
                            </div>
                            <!-- /.box-body -->

                            <div class="box-footer">
                                <a   href="${pageContext.request.contextPath}/users" type="button" class="btn btn-danger  col-2 float-left">Annuler</a>
                                <button type="submit" class="btn btn-info col-5 float-right">Modifier</button>
                            </div>
                            <!-- /.box-footer -->
                        </form>
                    </div>
                    <!-- /.box -->
                </div>
                <!-- /.col -->
            </div>
        </section>
        <!-- /.content -->
    </div>

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- ./wrapper -->

<%@ include file="/WEB-INF/views/common/js_imports.jsp" %>
</body>
</html>
