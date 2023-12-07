package toramaru.show.lec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import toramaru.show.model.ContactDBDao;

@Controller
@RequestMapping("/lec2")
public class Lec200Cnt {
	@Autowired
	private ContactDBDao dbDao;
	@GetMapping("/select")
	public String elect(Model model) {
		model.addAttribute("list",dbDao.select());
		return "/lec2/lec201_select";
	}
}
