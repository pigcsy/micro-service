package com.core.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Transactional
public abstract class AbstractService<T, ID extends Serializable> implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public Logger logger = LoggerFactory.getLogger(this.getClass());

    protected abstract BaseRepository getRepository();

    @Transactional
    public <S extends T> S save(S s) {
        return (S) getRepository().save(s);
    }

    public T findOne(ID id) {
        return (T) getRepository().findOne(id);
    }

    public boolean exists(ID id) {
        return getRepository().exists(id);
    }

    public long count() {
        return getRepository().count();
    }

    public void delete(ID id) {
        getRepository().delete(id);
    }


    public void delete(T t) {
        getRepository().delete(t);
    }

    public void delete(Iterable<? extends T> iterable) {
        getRepository().delete(iterable);
    }

    public void deleteAll() {
        getRepository().deleteAll();
    }


    public Page<T> findAll(Pageable pageable) {
        return getRepository().findAll(pageable);
    }


    public <S extends T> S findOne(S entity) {
        Example<S> example = Example.of(entity);
        return findOne(example);
    }

    public <S extends T> S findOne(Example<S> example) {
        return (S) getRepository().findOne(example);
    }

    public <S extends T> Page<S> findAll(S entity, Pageable pageable) {
        Example<S> example = Example.of(entity);
        return findAll(example, pageable);
    }

    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
        return getRepository().findAll(example, pageable);
    }

    public <S extends T> long count(S entity) {
        Example<S> example = Example.of(entity);
        return count(example);
    }

    public <S extends T> long count(Example<S> example) {
        return getRepository().count(example);
    }

    public <S extends T> boolean exists(S entity) {
        Example<S> example = Example.of(entity);
        return exists(example);
    }


    public <S extends T> boolean exists(Example<S> example) {
        return getRepository().exists(example);
    }


    public List<T> findAll() {
        return getRepository().findAll();
    }

    public List<T> findAll(Sort sort) {
        return getRepository().findAll(sort);
    }

    public List<T> findAll(Iterable<ID> ids) {
        return getRepository().findAll(ids);
    }

    public <S extends T> List<S> save(Iterable<S> entities) {
        return getRepository().save(entities);
    }

    /**
     * Flushes all pending changes to the database.
     */
    public void flush() {
        getRepository().flush();
    }

    /**
     * Saves an entity and flushes changes instantly.
     *
     * @param entity
     * @return the saved entity
     */
    public <S extends T> S saveAndFlush(S entity) {
        return (S) getRepository().saveAndFlush(entity);
    }

    /**
     * Deletes the given entities in a batch which means it will create a single {@link Query}. Assume that we will clear
     * the {@link EntityManager} after the call.
     *
     * @param entities
     */
    public void deleteInBatch(Iterable<T> entities) {
        getRepository().deleteInBatch(entities);
    }

    /**
     * Deletes all entities in a batch call.
     */
    public void deleteAllInBatch() {
        getRepository().deleteAllInBatch();
    }

    /**
     * Returns a reference to the entity with the given identifier.
     *
     * @param id must not be {@literal null}.
     * @return a reference to the entity with the given identifier.
     * @see EntityManager#getReference(Class, Object)
     */
    public T getOne(ID id) {
        return (T) getRepository().getOne(id);
    }

    public <S extends T> List<S> findAll(S entity) {
        Example<S> example = Example.of(entity);
        return findAll(example);
    }

    /* (non-Javadoc)
     * @see org.springframework.data.repository.query.QueryByExampleExecutor#findAll(org.springframework.data.domain.Example)
     */
    public <S extends T> List<S> findAll(Example<S> example) {
        return getRepository().findAll(example);
    }

    public <S extends T> List<S> findAll(S entity, Sort sort) {
        Example<S> example = Example.of(entity);
        return findAll(example, sort);
    }

    /* (non-Javadoc)
     * @see org.springframework.data.repository.query.QueryByExampleExecutor#findAll(org.springframework.data.domain.Example, org.springframework.data.domain.Sort)
     */
    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
        return getRepository().findAll(example, sort);
    }

    public int update(T entity) {
        return getRepository().update(entity);
    }

    public T getOneByUpdate(ID id) {
        return (T) getRepository().getOneByUpdate(id);
    }

    /**
     * hibernate 分页
     *
     * @param hql
     * @param countHql
     * @param params
     * @param pageable
     * @param <X>
     * @return
     */
    public <X> Page<X> find(final String hql, final String countHql, final Map<String, ?> params, Pageable pageable, Class<X> clazz) {
        return getRepository().find(hql, countHql, params, pageable, clazz);
    }

    public <X> List<X> find(String hql, Map<String, ?> params, Class<X> clazz) {
        return getRepository().find(hql, params, clazz);
    }

    public <X> List<X> find(final String hql, final Map<String, ?> params, Pageable pageable, Class<X> clazz) {
        return getRepository().find(hql, params, pageable, clazz);
    }

    /**
     * 查询count记录
     *
     * @param hql
     * @param params
     * @return
     */
    public long count(final String hql, final Map<String, ?> params) {
        return getRepository().count(hql, params);
    }

    /**
     * 原生的分页
     *
     * @param sql
     * @param countSql
     * @param params
     * @param pageable
     * @param <X>
     * @return
     */
    public <X> Page<X> findByNative(final String sql, final String countSql, final List<?> params, Pageable pageable, Class<X> clazz) {
        return getRepository().findByNative(sql, countSql, params, pageable, clazz);
    }

    public <X> List<X> findByNative(String sql, List<?> params, Class<X> clazz) {
        return getRepository().findByNative(sql, params, clazz);
    }

    public <X> List<X> findByNative(final String sql, final List<?> params, Pageable pageable, Class<X> clazz) {
        return getRepository().findByNative(sql, params, pageable, clazz);
    }

    /**
     * 查询原生的记录count数
     *
     * @param sql
     * @param params
     * @return
     */
    public long countByNative(final String sql, final List<?> params) {
        return getRepository().countByNative(sql, params);
    }
}
