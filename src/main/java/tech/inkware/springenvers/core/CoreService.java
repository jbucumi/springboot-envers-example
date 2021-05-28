/**
 * 
 */
package tech.inkware.springenvers.core;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;

//@formatter:off
/**
 * @author jeanclaude.bucumi
 * jeanclaude.bucumi@outlook.com
 */
//@formatter:on

public abstract class CoreService<T extends CoreEntity<String>, V extends CoreRepository<T>> {

	private V dao;

	@Autowired
	public final void setDao(V dao) {
		this.dao = dao;
	}

	public T save(T entity) {
		return dao.saveAndFlush(entity);
	}

	public void save(Collection<T> entities) {
		entities.forEach(e -> save(e));
	}

	public List<T> getAll(Boolean includeDeleted) {
		if (includeDeleted)
			return dao.findAll();
		return dao.findAll().parallelStream().filter(o -> o.getDataStatus().equals(DATA_STATUS.ACTIVE))
				.collect(Collectors.toList());
	}

	public List<T> getAll() {
		return getAll(false);
	}

	public List<T> getAll(Pageable pg) {
		return dao.findAll(pg).getContent().parallelStream().filter(o -> o.getDataStatus().equals(DATA_STATUS.ACTIVE))
				.collect(Collectors.toList());
	}

	public T getOne(String id) {
		return dao.getById(id);
	}

	public void cancel(T entity) {
		entity.setDataStatus(DATA_STATUS.CANCELLED);
		dao.save(entity);
	}

	public void delete(T entity) {
		entity.setDataStatus(DATA_STATUS.DELETED);
		dao.save(entity);
	}

	public List<T> getFullHistory(String id) {
		Revisions<Integer, T> revisions = dao.findRevisions(id);
		return revisions.stream().map(rv -> rv.getEntity()).collect(Collectors.toList());
	}

	public List<T> getPageableHistory(String id, Pageable pg) {
		Page<Revision<Integer, T>> revisions = dao.findRevisions(id, pg);
		return revisions.stream().map(rv -> rv.getEntity()).collect(Collectors.toList());
	}

	public T getRevision(String id, Integer nbr) {
		Optional<Revision<Integer, T>> rv = dao.findRevision(id, nbr);
		if (rv.isPresent())
			return rv.get().getEntity();
		return null;
	}

	public T getLastVersion(String id) {
		Optional<Revision<Integer, T>> rv = dao.findLastChangeRevision(id);
		if (rv.isPresent())
			return rv.get().getEntity();
		return null;
	}
}
