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
                Voiture
            </h1>


        <div class="row" >
        <div class="col-md-4">

                <c:if test="${success == false }">
                    <div class="alert alert-danger ">
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
                        <!-- Le  type de methode http qui sera appel� lors de action submit du formulaire -->
                        <!-- est d�crit an l'attribut "method" de la balise forme -->
                        <!-- action indique � quel "cible" sera envoyr la requ�te, ici notre Servlet qui sera bind sur -->
                        <!-- /vehicles/create -->
                        <form class="form-horizontal" method="post">
                            <div class="box-body align-items-center">
                                <div class="form-group">
                                    <label for="manufacturer" class="col-sm-2 control-label">Marque</label>

									<!-- Pour r�up�rer la valeur rentr�e dans un champ input de cette jsp au niveau de votre servlet -->
									<!-- vous devez passer par les methodes getParameter de l'objet request, est sp�cifiant la valeur -->
									<!-- de l'attribut "name" de l'input -->
                                    <div class="col-sm">
                                        <input type="text" class="form-control" id="manufacturer" name="manufacturer" placeholder="Marque" required value="${vehicle.constructeur}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="modele" class="col-sm-2 control-label">Modele</label>

                                    <div class="col-sm">
                                        <input type="text" class="form-control" id="modele" name="modele" placeholder="Modele" required value="${vehicle.modele}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="seats" class="col-sm-2 control-label">Nombre de places</label>

                                    <div class="col-sm">
                                        <input type="text" class="form-control" id="seats" name="seats" placeholder="Nombre de places" required value="${vehicle.nb_places}">
                                    </div>
                                </div>

                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <a   href="${pageContext.request.contextPath}/cars" type="button" class="btn btn-danger col-2 float-left">Annuler</a>
                                <button type="submit" class="btn btn-info  col-5 float-right">Modifier</button>
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
