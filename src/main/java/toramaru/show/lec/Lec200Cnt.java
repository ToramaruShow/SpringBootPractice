package toramaru.show.lec;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import toramaru.show.service.ContactDBDao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/lec2")
public class Lec200Cnt {
	@Value("${lec200.title}")
	private String[] title;

	@Autowired
	private ContactDBDao dbDao;

	@GetMapping("/select")
	public String elect(Model model) {
		model.addAttribute("title", title[0]);
		model.addAttribute("list", dbDao.select());
		return "/lec2/lec201_select";
	}

	@GetMapping("/input")
	public String input(Model model) {
		model.addAttribute("title", title[1]);
		model.addAttribute(new Contact());
		return "/lec2/lec201_input";
	}

	@GetMapping("/confirm")
	public String confirm(@ModelAttribute Contact contact, Model model) {
		model.addAttribute("title", title[2]);
		return "/lec2/lec201_confirm";
	}//end confirm

	@GetMapping("/result")
	public String result(@Valid Contact contact, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "lec2/lec201_input";
		}
		//日時の取得
		LocalDateTime nowTime = LocalDateTime.now();
		//OrderNumber
		String orderNumber = String.format("%s%03d", nowTime.format(
				DateTimeFormatter.ofPattern("yyyyMMdd")),dbDao.todayCount() + 1);
		//問い合わせに日時
		String date = nowTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		contact.setOrderNumber(orderNumber);

		contact.setRecvDate(date);

		model.addAttribute("title", title[3]);
		//DB Register (登録)
		String msg = "Register Succes!";
		try {
			int dbresult = dbDao.insert(contact);
			if (dbresult == 0) {
				msg = "Register Faild!";
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		model.addAttribute("msg", String.format("[%s] %s", contact.getTitle(), msg));
		return "/lec2/lec201_result";
	}//end result

	@GetMapping("/test")
	public String test(Model model) {
//		LocalDateTime nowTime = LocalDateTime.now();
//		String orderNumber = String.format("%s%03d", nowTime.format(DateTimeFormatter.ofPattern("yyyyMMdd")),dbDao.todayCount() + 1);
//		model.addAttribute("test", orderNumber);

		model.addAttribute("test", dbDao.todayCount());
		return "/test";
	}
}
//エラー「テンプレート [/lec2/lec2_confirm] の解決中に、テンプレートが存在しないか」
//のエラーは、returnエラーを疑うor送られるデータや、その型も疑う