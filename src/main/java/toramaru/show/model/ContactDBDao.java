package toramaru.show.model;

import java.util.List;
import java.util.Objects;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import toramaru.show.db.DBDao;
import toramaru.show.lec.Contact;

@Service
public class ContactDBDao extends JdbcDaoSupport implements DBDao<Contact> {
	
	@Autowired
	private DataSource dataSource;
	
	@PostConstruct
	private void initialize(){
		setDataSource(dataSource);
	}
	
	@Override
	public List<Contact> select() throws DataAccessException {
		String selectSql = "select * from contact";
		return Objects.requireNonNull(getJdbcTemplate()).query(selectSql,new BeanPropertyRowMapper<Contact>(Contact.class));
	}

	@Override
	public List<Contact> select(String[] search) throws DataAccessException {
		return null;
	}

	@Override
	public int insert(Contact type) throws DataAccessException {
		return 0;
	}

	@Override
	public Contact search(Contact type) throws DataAccessException {
		return null;
	}

	@Override
	public int delete(Contact type) throws DataAccessException {
		return 0;
	}

	@Override
	public int delete(String no) throws DataAccessException {
		return 0;
	}

	@Override
	public int update(Contact type) throws DataAccessException {
		return 0;
	}

}
