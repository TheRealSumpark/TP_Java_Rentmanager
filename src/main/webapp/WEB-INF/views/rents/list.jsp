<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/rents/create">Ajouter</a>
            </h1>
            <div class="row" >
                <div class="col-md-4">
                        <c:if test="${success == true }">
                            <div class="alert alert-success "  style="margin-top: 5%">
                                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>

                                Modification avec succes.
                            </div>
                        </c:if>
                </div>
            </div>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <div class="box">
                        <div class="box-body no-padding">
                            <table class="table hover" id="myTable">
                                <thead>
                                <tr>
                                    <th style="width: 10px">#</th>
                                    <th>Voiture</th>
                                    <th>Client</th>
                                    <th>Debut</th>
                                    <th>Fin</th>
                                    <th>Action</th>
                                </tr>

                                </thead>
                                <tbody>
                                <c:forEach items="${rents}" var="rent">
                                <tr>
                                    <td>${rent.id}.</td>
                                    <td>${rent.vehicle.constructeur} ${rent.vehicle.modele}</td>
                                    <td>${rent.client.nom} ${rent.client.prenom}</td>
                                   <td>
                                       <fmt:parseDate value="${rent.debut }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                                       <fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${ parsedDateTime }" />
                                    </td>
                                    <td>
                                        <fmt:parseDate value="${rent.fin }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                                        <fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${ parsedDateTime }" />
                                    </td>
                                    <td>

                                        <a class="btn btn-success " href="${pageContext.request.contextPath}/rents/modify?id=${rent.id}">
                                            <i class="fa fa-edit"></i>
                                        </a>
                                        <a class="btn btn-danger" href="${pageContext.request.contextPath}/rents/delete?id=${rent.id}">
                                            <i class="fa fa-trash"></i>
                                        </a>
                                    </td>
                                </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <!-- /.box-body -->
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

<script>
    $(document).ready(function() {
        $('#myTable').DataTable( {
            columnDefs: [ {
                targets: [ 5 ],
                orderable : false
            }, ]
        } );
    } );
</script>
</body>
</html>
