<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-type" content="text/html ; charset=ISO-8859-1">
<title>Cadastro de produto</title>

<script src="resources/javascript/jquery.min.js" type="text/javascript"></script>

<script src="resources/javascript/jquery.maskMoney.min.js"
	type="text/javascript"></script>

<link rel="stylesheet" href="resources/css/cadastro.css">
</head>
<body>
	<a href="acessoliberado.jsp">Inicío</a>
	<a href="index.jsp">Sair</a>
	<center>
		<h1>Cadastro de produto</h1>
		<h2 style="color: red;">${msg}</h2>
	</center>

	<form action="salvarProduto" method="post" id="formUser"
		onsubmit="return validarCampos() ? true : false;">
		<ul class="form-style-1">
			<li>
				<table>
					<tr>
						<td>Código:</td>
						<td><input type="text" readonly="readonly" id="id" name="id"
							value="${nome.id}" class="field-long"></td>
					</tr>
					<tr>
						<td>Produto:</td>
						<td><input type="text" id="nome" name="nome"
							placeholder="Informe o nome" value="${nome.nome}" maxlength="50"></td>
					</tr>

					<tr>
						<td>Quantidade Unidade:</td>
						<td><input type="number" id="quantidade" name="quantidade"
							placeholder="Informe a quantidade" value="${nome.quantidade}"
							maxlength="10"></td>
					</tr>

					<tr>
						<td>Valor R$:</td>
						<td><input type="text" id="valor" name="valor" data-thousands="."  data-decimal="," data-precision="2"  
							placeholder="Informe o valor" 
							 value="${nome.valorEmTexto}" maxlength="20"></td>
					</tr>


					<tr>
						<td></td>
						<td><input type="submit" style="width: 90px" value="Salvar">
							<input type="submit" style="width: 90px" value="Cancelar"
							onclick="document.getElementById('formUser').action = 'salvarProduto?acao=reset'"></td>
					</tr>


				</table>
			</li>
		</ul>
	</form>



	<div class="container">
		<table class="responsive-table">
			<caption>Produtos cadastrados</caption>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Quantidade</th>
				<th>Valor</th>
				<th>Excluir</th>
				<th>Editar</th>
			</tr>

			<c:forEach items="${produto}" var="nome">
				<tr>
					<td style="width: 150px"><c:out value="${nome.id}"></c:out>
					<td style="width: 150px"><c:out value="${nome.nome}"></c:out></td>
					<td style="width: 150px"><c:out value="${nome.quantidade}"></c:out></td>
					<td style="width: 150px"><fmt:formatNumber type="number" maxFractionDigits="2" value="${nome.valor}" /></td>

					<td style="width: 100px"><a
						href="salvarProduto?acao=delete&nome=${nome.id}"><img
							alt="excluir" title="Excluir" src="resources/img/excluir.png"
							width="32px" height="32px"></a></td>
					<td style="width: 100px"><a
						href="salvarProduto?acao=editar&nome=${nome.id}"><img
							alt="editar" title="Editar" src="resources/img/editar.png"
							width="32px" height="32px"></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<script type="text/javascript">
		function validarCampos() {
			if (document.getElementById("nome").value == '') {
				alert('Informe Nome');
				return false;
			} else if (document.getElementById("quantidade").value == '') {
				alert('Informe Quantida');
				return false;
			} else if (document.getElementById("valor").value == '') {
				alert('Informe Valor');
				return false;
			}

			return true;
		}
	</script>
</body>
<script type="text/javascript">
	$(function() {
		$('#valor').maskMoney();
	})
</script>
</html>