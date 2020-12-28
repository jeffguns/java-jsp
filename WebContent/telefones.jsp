<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-type" content="text/html ; charset=ISO-8859-1">
<title>Cadastro de usuário</title>
<link rel="stylesheet" href="resources/css/cadastro.css">


</head>
<body>
	<a href="acessoliberado.jsp">Inicío</a>
	<a href="index.jsp">Sair</a>
	<center>
		<h1>Cadastro de telefones</h1>
	</center>
	<center>
		<h2 style="color: purple;">${msg}</h2>
	</center>
	<form action="salvarTelefones" method="post" id="formUser"
		onsubmit="return validarCampos() ? true : false;">
		<ul class="form-style-1">
			<li>
				<table>
					<tr>
						<td>User:</td>
						<td><input type="text" readonly="readonly" id="user"
							name="user" class="field-long" value="${userEscolhido.id}"></td>

						<td><input type="text" readonly="readonly" id="nome"
							name="nome" class="field-long" value="${userEscolhido.nome}"></td>
					</tr>
					<tr>
						<td>Número:</td>
						<td><input type="text" id="numero" name="numero"
							placeholder="Informe o número"></td>
						<td><select id="tipo" name="tipo" style="width: 185px;">
								<option>Casa</option>
								<option>Recado</option>
								<option>Celular</option>
						</select></td>
					</tr>

					<tr>
						<td></td>
						<td><input type="submit" value="Salvar" style="width: 185px"></td>
						<td><input type="submit" value="Voltar" style="width: 185px"
 							onclick="document.getElementById('formUser').action = 'salvarTelefones?acao=voltar'">
						</td>
					</tr>
				</table>
			</li>
		</ul>
	</form>

	<div class="container">
		<table class="responsive-table">
			<caption>Usuários cadastrados</caption>
			<tr>
				<th>Id</th>
				<th>Número</th>
				<th>Tipo</th>
				<th>Excluir</th>
			</tr>

			<c:forEach items="${telefones}" var="telefones">
				<tr>
					<td style="width: 10px"><c:out value="${telefones.id}"></c:out>
					<td style="width: 10px"><c:out value="${telefones.numero}"></c:out></td>
					<td style="width: 10px"><c:out value="${telefones.tipo}"></c:out></td>

					<td style="width: 10px"><a
						href="salvarTelefones?user=${telefones.usuario}&acao=deleteTelefones&telefonesId=${telefones.id}"><img
							alt="excluir" title="Excluir" src="resources/img/excluir.png"
							width="20px" height="20px"></a></td>

				</tr>
			</c:forEach>
		</table>
	</div>
	<script type="text/javascript">
	/**	function validarCampos() {
			if (document.getElementById("numero").value == '') {
				alert('Informe o Número');
				return false;
			} else if (document.getElementById("tipo").value == '') {
				alert('Informe o Tipo');
				return false;
			}

			return true;
		}*/
	</script>
</body>
</html>