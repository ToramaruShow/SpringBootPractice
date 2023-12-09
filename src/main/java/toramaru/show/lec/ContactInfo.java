package toramaru.show.lec;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="contact")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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