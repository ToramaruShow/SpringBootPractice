package toramaru.show.service;

import java.util.List;

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
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public List<Contact> select() throws DataAccessException {
        String selectSql = "select * from contact";
        return getJdbcTemplate().query(selectSql, new BeanPropertyRowMapper<>(Contact.class));
    }

    @Override
    public List<Contact> select(String[] search) throws DataAccessException {
        return null;
    }

    @Override
    public int insert(Contact contact) throws DataAccessException {
        String insertSql = "insert into contact(order_number, name, email, title, contents, recv_date)";
        insertSql += "\nvalues(?,?,?,?,?,?)";//[\n]はスペースの代わり += は上の行の続き
        Object[] params = {
                contact.getOrderNumber(),
                contact.getName(),
                contact.getEmail(),
                contact.getTitle(),
                contact.getContents(),
                contact.getRecvDate()
        };
        return getJdbcTemplate().update(insertSql, params);
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
    }//update

    public int todayCount() throws DataAccessException {
        String countSql = "select count(*) from contact";
        countSql += "\nwhere DATE_FORMAT(recv_date,'%Y-%m-%d')";//recvDateは時間まで入っている
        countSql += "=DATE_FORMAT(now(),'%Y-%m-%d')";//[\n]はスペースの代わり += は上の行の続き
        return getJdbcTemplate().queryForObject(countSql, Integer.class);
    }
}
