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
                Reservations
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
                                    <label for="car" class="col-sm-2 control-label">Voiture</label>

                                    <div class="col-sm">
                                        <select class="form-control" id="car" name="car">

                                            <c:forEach items="${vehicles}" var="vehicle">
                                                <c:choose>
                                                <c:when test="${rent.vehicle_id == vehicle.id}">
                                                    <option selected value="${vehicle.id}">${vehicle.constructeur} ${vehicle.modele}</option>

                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${vehicle.id}">${vehicle.constructeur} ${vehicle.modele}</option>
                                                </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="client" class="col-sm-2 control-label">Client</label>

                                    <div class="col-sm">
                                        <select class="form-control" id="client" name="client">
                                            <c:forEach items="${clients}" var="client">
                                            <c:choose>
                                                <c:when test="${client.id == rent.client_id}">
                                                <option selected value="${client.id}">${client.prenom} ${client.nom}</option>
                                            </c:when>
                                                <c:otherwise>
                                                <option value="${client.id}">${client.prenom} ${client.nom}</option>
                                                </c:otherwise>
                                            </c:choose>

                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="begin" class="col-sm-2 control-label">Date de debut</label>

                                    <div class="col-sm">
                                        <input type="datetime-local" class="form-control" id="begin" name="begin" required value="${rent.debut}"
                                           data-mask>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="end" class="col-sm-2 control-label">Date de fin</label>

                                    <div class="col-sm">
                                       <input type="datetime-local" class="form-control" id="end" name="end" required value="${rent.fin}"
                                               data-mask>

                                    </div>

                                </div>
                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <a   href="${pageContext.request.contextPath}/rents" type="button" class="btn btn-danger col-2 float-left">Annuler</a>
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
<script src="${pageContext.request.contextPath}/resources/plugins/input-mask/jquery.inputmask.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/input-mask/jquery.inputmask.extensions.js"></script>
<script>
    $(function () {
        $('[data-mask]').inputmask()
    });
</script>
</body>
</html>
