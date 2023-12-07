package toramaru.show.db;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface DBDao<T> {
	public List<T> select() throws DataAccessException;
	public List<T> select(String[] search) throws DataAccessException;
	public int insert(T type) throws DataAccessException;
	public T search(T type) throws DataAccessException;
	public int delete(T type) throws DataAccessException;
	public int delete(String no) throws DataAccessException;// int(番号)で消すならおすすめ
	public int update(T type) throws DataAccessException;
}