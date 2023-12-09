package toramaru.show.lec;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import toramaru.show.service.ContactInfoService;

@Controller
@RequestMapping("/lec2/jpa")
public class lec210Cnt {
	
	@Value("${lec210.jpa.title}")
	private String[] title;
	@Autowired
	private ContactInfoService service;
	
	//Lec 2 1 0 Cnt
	@GetMapping("/select")
	public String elect(Model model) {
		model.addAttribute("title", title[0]);
		model.addAttribute("list", service.select());
		return "/lec2/lec201_select";
	}
	
	@GetMapping("/input")
	public String input(Model model) {
		model.addAttribute("title", title[1]);
		model.addAttribute("contact", new ContactInfo());
		return "/lec2/lec210_input";
	}
	
	@GetMapping("/confirm")
	public String confirm(@ModelAttribute ContactInfo contactInfo, Model model) {
		model.addAttribute("title", title[2]);
		return "/lec2/lec210_confirm";
	}
	
	@GetMapping("/result")
	public String result(@Valid ContactInfo contactInfo, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "lec2/lec210_input";
		}
		model.addAttribute("title", title[3]);
		//DB Register (登録)
		String msg = "Register Faild!";
		try {
			ContactInfo info = service.insert(contactInfo);
			if(Objects.nonNull(info)) {
				msg = "Register Succes!";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("msg", String.format("[%s] %s", contactInfo.getTitle(), msg));
		return "/lec2/lec201_result";
	}//end result
	
	@GetMapping("/test")
	public String test(Model model) {
		LocalDateTime nowTime = LocalDateTime.now();
		model.addAttribute("test",service.makeOrderNumber(nowTime));
		return "/test";
	}
}
