package toramaru.show.lec;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
	private String id;					//id
	private String ordernumber;	//問い合わせ番号
	private String name;			    //名前
	private String email;				//メール
	private String title;				//件名
	private String contents;			//問い合わせ内容
	private String recvDate;			//受け取り日時
}
