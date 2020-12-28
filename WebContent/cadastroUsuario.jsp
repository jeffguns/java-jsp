<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-type" content="text/html ; charset=ISO-8859-1">
<title>Cadastro de usuário</title>
<link rel="stylesheet" href="resources/css/cadastro.css">

<!-- Adicionando JQuery -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous"></script>
</head>
<body>
	<a href="acessoliberado.jsp">Inicío</a>
	<a href="index.jsp">Sair</a>
	<center>
		<h1>Cadastro de usuário</h1>
		<h2 style="color: red;">${msg}</h2>
	</center>

	<form action="salvarUsuario" method="post" id="formUser"
		onsubmit="return validarCampos() ? true : false;"
		enctype="multipart/form-data">
		<ul class="form-style-1">
			<li>
				<table>
					<tr>
						<td>Código:</td>
						<td><input type="text" readonly="readonly" id="id" name="id"
							value="${user.id}" class="field-long" style="width: 185px"></td>

						<td>Cep:</td>
						<td><input type="text" id="cep" name="cep"
							placeholder="Informe um cep válido" onblur="consultaCep();"
							value="${user.cep}" maxlength="9"></td>
					</tr>
					<tr>
						<td>Login:</td>
						<td><input type="text" id="login" name="login"
							placeholder="Informe login" value="${user.login}" maxlength="10"></td>

						<td>Rua:</td>
						<td><input type="text" id="rua" name="rua"
							placeholder="Informe nome da rua" value="${user.rua}"
							maxlength="50"></td>
					</tr>

					<tr>
						<td>Senha:</td>
						<td><input type="password" id="senha" name="senha"
							placeholder="Informe a senha" value="${user.senha}"
							maxlength="16"></td>

						<td>Bairro:</td>
						<td><input type="text" id="bairro" name="bairro"
							placeholder="Informe o bairro" value="${user.bairro}"
							maxlength="50"></td>
					</tr>
					<tr>
						<td>Nome:</td>
						<td><input type="text" id="nome" name="nome"
							value="${user.nome}" placeholder="Informe nome de usuário"
							maxlength="50"></td>

						<td>Cidade:</td>
						<td><input type="text" id="cidade" name="cidade"
							placeholder="Informe a cidade" value="${user.cidade}"
							maxlength="50"></td>
					</tr>

					<tr>
						<td>Estado:</td>
						<td><input type="text" id="estado" name="estado"
							placeholder="Informe a estado" value="${user.estado}"
							maxlength="50"></td>

						<td>IBGE:</td>
						<td><input type="text" id="ibge" name="ibge"
							placeholder="Informe o ibge" value="${user.ibge}" maxlength="50"></td>
					</tr>

					<tr>
						<td>Foto:</td>
						<td><input type="file" name="foto"> <input
							type="text" style="display: none;" name="fotoTemp"
							readonly="readonly" value="${user.fotoBase64}" /> <input
							type="text" style="display: none;" name="contentTytpeTemp"
							readonly="readonly" value="${user.contentType}" /></td>
					</tr>

					<tr>
						<td>Curriculo:</td>
						<td><input type="file" name="curriculo" value="curriculo">

							<input type="text" style="display: none;" name="fotoTempPDF"
							readonly="readonly" value="${user.curriculoBase64}" /> <input
							type="text" style="display: none;" name="contentTytpeTempPDF"
							readonly="readonly" value="${user.contenttypeCurriculo}" /></td>
					</tr>

					<tr>
						<td></td>
						<td><input type="submit" value="Salvar" style="width: 185px"></td>
						<td></td>
						<td><input type="submit" value="Cancelar"
							style="width: 185px"
							onclick="document.getElementById('formUser').action = 'salvarUsuario?acao=reset'"></td>
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
				<th>Foto</th>
				<th>Curriculo</th>
				<th>Nome</th>
				<th>Telefones</th>
				<th>Editar</th>
				<th>Excluir</th>
			</tr>

			<c:forEach items="${usuarios}" var="user">
				<tr>
					<td><c:out value="${user.id}"/>
					 <c:if test="${user.fotoBase64Miniatura.isEmpty() == false}">
							<td><a href="salvarUsuario?acao=download&tipo=imagem&user=${user.id}"><img src='<c:out value="${user.fotoBase64Miniatura}"/>' alt="Imagem User" title="Imagem User" width="32px" height="32px"></a></td>
						</c:if> 
						<c:if test="${user.fotoBase64Miniatura.isEmpty() == true}">

							<td><img alt="Imagem User" src="resources/img/iconUser.png"
								width="32px" height="32px" onclick="alert('Não possui imagem')"></td>
						</c:if> 
						
						<c:if test="${user.curriculoBase64.isEmpty() == false}">
							<td><a href="salvarUsuario?acao=download&tipo=curriculo&user=${user.id}"><img
									alt="Curriculo" src="resources/img/pdf.png" width="32px"
									height="32px"></a></td>
						</c:if> 
						
						<c:if test="${user.curriculoBase64.isEmpty() == true}">
							<td><img alt="Curriculo" src="resources/img/pdf.png"
								width="32px" height="32px"
								onclick="alert('Não possui curriculo')"></td>
						</c:if>
						
					<td><c:out value="${user.nome}"></c:out></td>

					<td><a href="salvarTelefones?acao=addTelefones&user=${user.id}"><img
							alt="telefones" title="Telefones" src="resources/img/fone.png"
							width="32px" height="32px"></a></td>

					<td><a href="salvarUsuario?acao=editar&user=${user.id}"><img
							alt="editar" title="Editar" src="resources/img/editar.png"
							width="32px" height="32px"></a></td>

					<td><a href="salvarUsuario?acao=delete&user=${user.id}"><img
							alt="excluir" title="Excluir" src="resources/img/excluir.png"
							width="32px" height="32px"></a></td>


				</tr>
			</c:forEach>
		</table>
	</div>
	<script type="text/javascript">
		function validarCampos() {
			if (document.getElementById("login").value == '') {
				alert('Informe o Login');
				return false;
			}

			else if (document.getElementById("senha").value == '') {
				alert('Informe a Senha');
				return false;
			}

			else if (document.getElementById("nome").value == '') {
				alert('Informe o Nome');
				return false;
			}

			return true;
		}

		function consultaCep() {
			var cep = $("#cep").val();
			alert(cep);

			//Consulta o webservice viacep.com.br/
			$.getJSON("https://viacep.com.br/ws/" + cep + "/json/?callback=?",
					function(dados) {

						if (!("erro" in dados)) {

							$("#rua").val(dados.logradouro);
							$("#bairro").val(dados.bairro);
							$("#cidade").val(dados.localidade);
							$("#estado").val(dados.uf);
							$("#ibge").val(dados.ibge);

						} //end if.
						else {
							$("#cep").val('');
							$("#rua").val('');
							$("#bairro").val('');
							$("#cidade").val('');
							$("#estado").val('');
							$("#ibge").val('');

							//CEP pesquisado não foi encontrado.
							limpa_formulário_cep();
							alert("CEP não encontrado.");
						}
					});
		}
	</script>
</body>
</html>