package toramaru.show.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import toramaru.show.lec.ContactInfo;

@Repository
public interface ContactInfoRepository extends JpaRepository<ContactInfo, Integer>{
	@Query(value="select count(*) from contact"
			+ "\nwhere DATE_FORMAT(recv_date,'%Y-%m-%d')"
			+ "=DATE_FORMAT(:date ,'%Y-%m-%d')"
			,nativeQuery = true)
	int todayCount(@Param("date") String today);//dateは適当な名前の変数
}
