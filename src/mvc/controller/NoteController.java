package mvc.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mvc.model.Notas;
import mvc.model.NoteDAO;

@Controller
public class NoteController {
	
	@RequestMapping("cria")
	public String criar(Notas note) {
		NoteDAO dao = new NoteDAO();
		dao.adicionaNota(note);
		return "notas";
	}
	
	@RequestMapping("remove")
	public String remove(Notas note) {
		NoteDAO dao = new NoteDAO();
		dao.removeNote(note.getId());
		return "notas";
	}

	@RequestMapping(value="edita", method = RequestMethod.GET)
	public String editaGet(Notas note, HttpSession session) throws ParseException {
		session.setAttribute("name", note.getUser());
		String date = new NoteDAO().getCurrentDate(note);
		Date data = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		Calendar dataEntrega = Calendar.getInstance();
		dataEntrega.setTime(data);
		session.setAttribute("date", dataEntrega);
		session.setAttribute("priority", note.getPriority());
		session.setAttribute("msg", note.getMsg());
		session.setAttribute("id", note.getId());
		
		return "editar";
	}
	
	@RequestMapping(value="edita", method = RequestMethod.POST)
	public String editaPost(Notas note) throws ParseException {
		NoteDAO dao = new NoteDAO();
		dao.updateNote(note);
		return "notas";
	}
	
	@RequestMapping("ordernar")
	public String ordernar(Notas note, HttpSession session, @ModelAttribute("variable") String variable) {
		String filtro = null;
		if (variable.contentEquals("Data")) {
			filtro = "date";
		} else if (variable.contentEquals("Usuário")) {
			filtro = "user";
		} else if (variable.contentEquals("Prioridade")){
			filtro = "priority";
		} else {
			filtro = "";
		}
		session.setAttribute("filtro", filtro);
//		session.setAttribute("name", note.getUser());
		return "notas";
	}
}
