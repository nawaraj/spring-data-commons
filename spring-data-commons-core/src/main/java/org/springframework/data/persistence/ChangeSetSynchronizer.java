package org.springframework.data.persistence;

import java.util.Map;

import org.springframework.dao.DataAccessException;

/**
 * Interface to be implemented by classes that can synchronize
 * between entities and ChangeSets.
 *
 * @param <E>
 * @author Rod Johnson
 */
public interface ChangeSetSynchronizer<E extends ChangeSetBacked> {

	Map<String, Class<?>> persistentFields(Class<? extends E> entityClassClass);

	/**
	 * Take all entity fields into a changeSet.
	 *
	 * @param entity
	 * @return
	 * @throws DataAccessException
	 */
	void populateChangeSet(ChangeSet changeSet, E entity) throws DataAccessException;

	void populateEntity(ChangeSet changeSet, E entity) throws DataAccessException;

}
