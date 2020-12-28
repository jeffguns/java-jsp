package servelt;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanCursoJSP;
import beans.BeanTelefonesJSP;
import dao.DaoTelefones;
import dao.DaoUsuario;

/**
 * @author jeff_
 */
@WebServlet("/salvarTelefones")
public class TelefonesServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoUsuario daoUsuario = new DaoUsuario();

	private DaoTelefones daoTelefones = new DaoTelefones();

	public TelefonesServlets() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String acao = request.getParameter("acao");
			String user = request.getParameter("user");
			if (acao.endsWith("addTelefones")) {


				BeanCursoJSP usuario = daoUsuario.consultar(user);

				request.getSession().setAttribute("userEscolhido", usuario);
				request.setAttribute("userEscolhido", usuario);

				RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
				request.setAttribute("telefones", daoTelefones.listar(usuario.getId()));
				view.forward(request, response);

			} else if (acao.endsWith("deleteTelefones")) {
				String telefonesId = request.getParameter("telefonesId");
				daoTelefones.delete(telefonesId);

				BeanCursoJSP beanCursoJSP = (BeanCursoJSP) request.getSession().getAttribute("userEscolhido");

				RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
				request.setAttribute("telefones", daoTelefones.listar(beanCursoJSP.getId()));
				request.setAttribute("msg", "Removido com sucesso!");
				view.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			BeanCursoJSP beanCursoJSP = (BeanCursoJSP) request.getSession().getAttribute("userEscolhido");

			String numero = request.getParameter("numero");
			String tipo = request.getParameter("tipo");
			String acao = request.getParameter("acao");

			if (acao == null || (acao != null && !acao.equalsIgnoreCase("voltar"))) {

				if (numero == null || (numero != null && numero.isEmpty())) {

					RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
					request.setAttribute("telefones", daoTelefones.listar(beanCursoJSP.getId()));
					request.setAttribute("msg", "Informe um número de telefone!");
					view.forward(request, response);
				} else {

					BeanTelefonesJSP beanTelefonesJSP = new BeanTelefonesJSP();
					beanTelefonesJSP.setNumero(numero);
					beanTelefonesJSP.setTipo(tipo);
					beanTelefonesJSP.setUsuario(beanCursoJSP.getId());
					daoTelefones.salvar(beanTelefonesJSP);
					request.getSession().setAttribute("userEscolhido", beanCursoJSP);
					request.setAttribute("userEscolhido", beanCursoJSP);
					
					RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
					request.setAttribute("telefones", daoTelefones.listar(beanCursoJSP.getId()));
					request.setAttribute("msg", "Salvo com sucesso!");
					view.forward(request, response);
				}
			}else {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
			}

		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}

}
