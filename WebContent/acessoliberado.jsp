<jsp:useBean id="calcula" class="beans.BeanCursoJSP"
	type="beans.BeanCursoJSP" scope="page" />
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-type" content="text/html ; charset=ISO-8859-1">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<a href="index.jsp">Sair</a>
	<center>
		<h1>Seja bem vindo ao sistema jsp.</h1>
		<a href="salvarUsuario?acao=listartodos"><img alt="usuario"
			title="Usuário" src="resources/img/usauarios.png" width="80px"
			height="80px"></a> <a href="salvarProduto?acao=listartodos"><img
			alt="produto" title="Produto" src="resources/img/produto.png"
			width="80px" height="80px"></a>
	</center>
</body>
</html>
