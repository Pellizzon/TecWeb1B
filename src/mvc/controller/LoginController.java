package mvc.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mvc.model.User;
import mvc.model.UserDAO;

@Controller
public class LoginController {
	@RequestMapping("entrar")
	public String registro() {
		return "login";
	}

	@RequestMapping("cadastrar")
	public String signup() {
		return "signup";
	}

	@RequestMapping(value = "efetuaCadastro", method = RequestMethod.POST)
	public String upload(User usuario, @ModelAttribute("password") String password,
			@ModelAttribute("password_check") String check) throws IOException {
		UserDAO dao = new UserDAO();
		if (dao.verificaJaCadastrado(usuario)) {
			return "ja_cadastrado";
		} else {
			if (password.contentEquals(check)) {
				dao.adicionaLogin(usuario);
				return "redirect:entrar";
			} else {
				return "erroSignup";
			}

		}
	}

	@RequestMapping("efetuaLogin")
	public String efetuaLogin(User usuario, HttpSession session) {
		if (session == null) {
			return "redirect:entrar";
		}
		if (new UserDAO().verificaLogin(usuario)) {
			session.setAttribute("name", new UserDAO().getCurrentUser(usuario));
			session.setAttribute("filtro", "");
			return "notas";
		}
		return "erroLogin";
	}

	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:entrar";
	}
}
