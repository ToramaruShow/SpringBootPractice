package toramaru.show.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import toramaru.show.lec.ContactInfo;

@Service
public class ContactInfoService {
	@Autowired //<- 名前の統一は不可
	private ContactInfoRepository repository;

	/**
	 * 一覧表示
	 */
	public List<ContactInfo> select() {
		return repository.findAll();
	}

	public ContactInfo insert(ContactInfo contactInfo) {
		LocalDateTime nowTime = LocalDateTime.now();
		String date = nowTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		contactInfo.setRecvDate(date);
		contactInfo.setOrderNumber(makeOrderNumber(nowTime));
		return repository.save(contactInfo);
	}

	public String makeOrderNumber(LocalDateTime nowTime) {
		String today = nowTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		int count = repository.todayCount(today);
		return String.format("%s%03d", nowTime.format(
				DateTimeFormatter.ofPattern("yyyyMMdd")),count + 1);
	}
}

//public ContactInfo insert(ContactInfo contactInfo) {
//	return repository.save(contactInfo);セーブするだけなら
//}