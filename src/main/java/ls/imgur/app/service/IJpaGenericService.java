package ls.imgur.app.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface IJpaGenericService<T, ID extends Serializable> {

	public T save(T entity) ;
	List<T> saveAll(List<T> entities) ;
	public void persist(T entity) ;
	
	public T update(T entity) ;
	public Collection<T> updateAll(Collection<T> entities) ;
	
	public void delete(T entity) ;
	public void deleteAll(List<T> entities) ;
	public void deleteById(ID entityId);
	public void deleteByIds(List<ID> ids) ;

	public Optional<T> findById(ID id);
	public List<T> findByIds(List<ID> ids) ;
	public List<T> findAll();

}
