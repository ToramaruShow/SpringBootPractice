package toramaru.show.lec;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
	private String id; //id
	private String orderNumber; //問い合わせ番号
	@NotEmpty(message="名前を入れてください")
	private String name; //名前
	@NotEmpty
	@Email
	private String email; //メール
	@NotEmpty
	@Length(min=3,max=30)
	private String title; //件名
	private String contents; //問い合わせ内容
	private String recvDate; //受け取り日時
}
