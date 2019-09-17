package mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller)
			throws Exception {
		String uri = request.getRequestURI();
		if (uri.endsWith("entrar") || uri.endsWith("cadastrar") || uri.endsWith("efetuaCadastro")
				|| uri.endsWith("efetuaLogin") || uri.endsWith("home.css") || uri.endsWith("reset_style.css")
				|| uri.endsWith("note_style.css")) {
			return true;
		}
		if (request.getSession().getAttribute("name") != null) {
			return true;
		}
		response.sendRedirect("entrar");
		return false;
	}
}