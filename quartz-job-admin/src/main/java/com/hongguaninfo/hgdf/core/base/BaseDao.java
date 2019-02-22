package com.hongguaninfo.hgdf.core.base;

import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;
import com.hongguaninfo.hgdf.core.utils.page.Page;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.support.DaoSupport;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @ClassName: BaseDao
 * @Description: 基础Dao
 * @author henry
 * @date 2014-2-19 上午11:25:27
 * 
 * @param <E>
 * @param <M>
 * @param <PK>
 */
public abstract class BaseDao<E, M, P extends Serializable> extends SqlSessionDaoSupport {

  protected static final Log LOG = LogFactory.getLog(BaseDao.class);

  @Override
  @Autowired
  public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
    super.setSqlSessionTemplate(sqlSessionTemplate);
  }

  /**
   * 根据主键查询
   * 
   * @param primaryKey
   * @return
   */
  public E getById(P primaryKey) {
    E object = (E) getSqlSession().selectOne(
        getMapperNamesapce() + ".getById", primaryKey);
    return object;
  }

  /**
   * 根据主键删除
   * 
   * @param primaryKey
   * @return
   */
  public int delete(P primaryKey) {
    int affectCount = getSqlSession().delete(
        getMapperNamesapce() + ".deleteById", primaryKey);
    return affectCount;
  }

  /**
   * 根据实体删除 注意可能会造成批量删除
   * 
   * @param entity
   */
  public void delete(E entity) {
    getSqlSession().delete(getMapperNamesapce() + ".delete", entity);
  }

  /**
   * 删除多条
   * 
   * @param ids
   */
  public void multiDelete(String ids) {
    getSqlSession().delete(getMapperNamesapce() + ".multiDelete", ids);
  }

  /**
   * 查询distinct
   * 
   * @param disName
   * @return
   */
  public List<E> getDistinct(E entity) {
    return getSqlSession().selectList(
        getMapperNamesapce() + ".getDistinct", entity);
  }

  /**
   * 保存
   * 
   * @param entity
   * @return
   */
  public int save(E entity) {
    prepareObjectForSaveOrUpdate(entity);
    return getSqlSession().insert(getMapperNamesapce() + ".insert", entity);
  }

  /**
   * 修改主键修改
   * 
   * @param entity
   */
  public int update(E entity) {
    prepareObjectForSaveOrUpdate(entity);
    int affectCount = getSqlSession().update(getMapperNamesapce() + ".update", entity);
    return affectCount;
  }

  /**
   * 获取list
   * 
   * @param entity
   * @return
   */
  public List<E> getList(E entity) {
    List<E> list = getSqlSession().selectList(
        getMapperNamesapce() + ".getList", entity);
    return list;
  }

  /**
   * 获取list数量
   * 
   * @param entity
   * @return
   */
  public int getListCount(E entity) {
    Number totalCount = (Number) getSqlSession().selectOne(
        getMapperNamesapce() + ".getListCount", entity);
    return totalCount.intValue();
  }

  /**
   * 获取分页list
   * 
   * @param entity
   * @param 开始位置
   * @param 数量
   * @return
   */
  public List<E> getList(E entity, int bPos, int count) {
    RowBounds rowBounds = new RowBounds(bPos, count);
    List<E> list = getSqlSession().selectList(
        getMapperNamesapce() + ".getList", entity, rowBounds);
    return list;
  }

  /**
   * 批量保存(外部循环)
   * 
   * @param list
   */
  public void saveBatch(List<E> list) {
    for (E entity : list) {
      prepareObjectForSaveOrUpdate(entity);
      getSqlSession().insert(getMapperNamesapce() + ".insert", entity);
    }
  }

  /**
   * 批量保存(内部循环)
   * 
   * @param list
   */
  public void saveBatchInn(List<E> list) {
    getSqlSession().insert(getMapperNamesapce() + ".insertBatch", list);
  }

  /**
   * 用于子类覆盖,在insert,update之前调用
   * 
   * @param o
   */
  protected void prepareObjectForSaveOrUpdate(E entity) {

  }

  /**
   * 获取Mapper
   * 
   * @param <M>
   * @param type
   * @return
   */
  public <M> M getMapperByType(Class<M> type) {
    return this.getSqlSession().getMapper(type);
  }

  /**
   * 获取Mapper命名空间 用于被子类覆盖
   * 
   * @return
   */
  public String getMapperNamesapce() {
    throw new RuntimeException("not yet implement");
  }

  /**
   * rapid分页查询
   * 
   * @param statementName
   * @param countStatementName
   * @param pageRequest
   * @return
   */
  protected Page pageQuery(String statementName, String countStatementName,
      BasePage<E> pageRequest) {
    LOG.info(getSqlSession().toString());
    Number totalCount = (Number) getSqlSession().selectOne(
        countStatementName, pageRequest.getFilters());

    if (totalCount == null || totalCount.longValue() <= 0) {
      return new Page(pageRequest, 0);
    }
    Page page = new Page(pageRequest, totalCount.intValue());

    List list = getSqlSession()
        .selectList(
            statementName,
            pageRequest.getFilters(),
            new RowBounds(pageRequest.getPosStart(), pageRequest
                .getCount()));

    page.setResult(list);

    return page;
  }

  /**
   * 默认使用 语句+“Count” 作为查询数量语句
   * 
   * @param statementName
   * @param pageRequest
   * @return
   */
  public Page pageQuery(String statementName, BasePage pageRequest) {
    return pageQuery(getMapperNamesapce() + "." + statementName,
        getMapperNamesapce() + "." + statementName + "Count",
        pageRequest);
  }

  /**
   * 默认使用 getList + getListCount 作为查询数量语句
   * 
   * @param pageRequest
   * @return
   */
  public Page pageQuery(BasePage pageRequest) {
    return pageQuery(getMapperNamesapce() + ".getList",
        getMapperNamesapce() + ".getListCount", pageRequest);
  }

  public Page pageInventoryQuery(BasePage pageRequest) {
    return pageQuery(getMapperNamesapce() + ".getInventoryList",
        getMapperNamesapce() + ".getInventoryListCount", pageRequest);
  }

  public List findAll() {
    throw new UnsupportedOperationException();
  }

  public boolean isUnique(E entity, String uniquePropertyNames) {
    throw new UnsupportedOperationException();
  }

  public static interface SqlSessionCallback {
    public Object doInSession(SqlSession session);
  }

}
