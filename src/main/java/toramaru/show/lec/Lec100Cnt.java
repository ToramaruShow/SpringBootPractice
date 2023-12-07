package toramaru.show.lec;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
public class Lec100Cnt {
	@RequestMapping("/top")
	public String home(Model model) {
		String[] urlData = {
				"/lec1",
				"/lec1/input"
		};
		model.addAttribute("urlData", urlData);
		return "top";

	}//home

	@GetMapping("/lec1/input")
	public String input(@ModelAttribute Contact contact) {
		return "/lec1/lec102_input";
	}

	@GetMapping("/lec1/result")
	public String result(@Valid Contact contact, BindingResult result) {
		if(result.hasErrors()) {
			return "/lec1/lec102_input";
		}
		return "/lec1/lec102_result";
	}

	@RequestMapping("/lec1")
	public String showLec1(Model model) {
		//htmlにデータを渡す
		model.addAttribute("title1", "SpringBoot");
		model.addAttribute("title2", "タイムリーフ");

		//SendObject
		var con = new Contact();
		con.setId("ID");
		con.setName("SETNAME");
		con.setEmail("Address");
		con.setTitle("Title");
		con.setContents("Message");
		con.setRecvDate("Date");
		model.addAttribute("contact", con);
		//List
		model.addAttribute("intArray", new int[] { 1, 2, 3, -4, 0 });
		//ListClass
		var con1 = new Contact("1", "", "NAME-1", "Address1@com", "Title1", "Message~1", "1111/11/1");
		var con2 = new Contact("2", "", "NAME-1", "Address1@com", "Title2", "Message~2", "2222/2/22");
		List<Contact> cl = Arrays.asList(con, con1, con2);
		model.addAttribute("clist", cl);
		//IF
		model.addAttribute("str", "こんちゃ");
		return "/lec1/lec101";
	}//show rec1 end
}
