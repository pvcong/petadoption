package com.ck.entitydao;

import com.ck.dao.generic.GenericDAOImpl;
import com.ck.data.DepartmentEntity;
import com.ck.data.EmployeeEntity;
import com.ck.exceptionhandler.NotFoundObjectException;
import com.ck.utils.JpaUtils;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Repository
public class EmployeeDAOImpl extends GenericDAOImpl<Integer, EmployeeEntity> implements EmployeeDAO {
    @Override
    public void update(EmployeeEntity entity) {
        try{
            EmployeeEntity employeeEntityPersis = entityManager.find(persistenClass,entity.getEmployeeId());
            //entity.setDepartmentEntity(employeeEntityPersis.getDepartmentEntity());
            entity.setCreatedDate(employeeEntityPersis.getCreatedDate());
            entityManager.merge(entity);
        }catch (HibernateException e){

        }finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(List<EmployeeEntity> entities) {
        try{
            for(int i = 0; i < entities.size(); i++){
                EmployeeEntity employeeEntity = entityManager.find(persistenClass,entities.get(i).getEmployeeId());
                entityManager.remove(entities.get(i));
            }
        } catch (HibernateException e){
            throw e;
        }
        finally {
            entityManager.close();
        }
    }


    @Override
    public Object[] findEmployeeByProperties(EmployeeEntity employeeEntity, DepartmentEntity departmentEntity, String sortProperty, String sortValue, Integer limit, Integer offset) {
        List<EmployeeEntity> employeeEntities = new ArrayList<>();
        int count = 0;

        StringBuilder stringQuery = new StringBuilder("SELECT DISTINCT e FROM EmployeeEntity e " +
                " JOIN FETCH e.departmentEntity d WHERE 1=1");
        if(employeeEntity != null){
            if(employeeEntity.getEmployeeId() != null){
                stringQuery.append(" AND e.employeeId = :employeeId");
            }
            if(employeeEntity.getFullName() != null){
                stringQuery.append(" AND e.fullName = :fullName ");
            }
        }
        if(departmentEntity != null){
            if(departmentEntity.getDepartmentId() != null)
            stringQuery.append(" AND d.departmentId = :departmentId");
        }

        JpaUtils.buildOrderBy(stringQuery,"e.createdDate","DESC");

        Query query = entityManager.createQuery(stringQuery.toString());

        if(employeeEntity != null){
            if(employeeEntity.getEmployeeId() != null){
                query.setParameter("employeeId",employeeEntity.getEmployeeId());
            }
            if(employeeEntity.getFullName() != null){
                query.setParameter("fullName",employeeEntity.getFullName());
            }
        }
        if(departmentEntity != null){
            if(departmentEntity.getDepartmentId() != null)
                query.setParameter("departmentId",departmentEntity.getDepartmentId());
        }
        JpaUtils.buildLimit2Offset(query,limit,offset);

        StringBuilder stringQueryCount = new StringBuilder("SELECT COUNT(DISTINCT e) FROM EmployeeEntity e " +
                " INNER JOIN  e.departmentEntity d WHERE 1=1");
        if(employeeEntity != null){
            if(employeeEntity.getEmployeeId() != null){
                stringQueryCount.append(" AND e.employeeId = :employeeId");
            }
            if(employeeEntity.getFullName() != null){
                stringQueryCount.append(" AND e.fullName = :fullName");
            }
        }
        if(departmentEntity != null){
            if(departmentEntity.getDepartmentId() != null)
                stringQueryCount.append(" AND d.departmentId = :departmentId");
        }


        Query queryCount = entityManager.createQuery(stringQueryCount.toString());

        if(employeeEntity != null){
            if(employeeEntity.getEmployeeId() != null){
                queryCount.setParameter("employeeId",employeeEntity.getEmployeeId());
            }
            if(employeeEntity.getFullName() != null){
                queryCount.setParameter("fullName",employeeEntity.getFullName());
            }
        }
        if(departmentEntity != null){
            if(departmentEntity.getDepartmentId() != null)
                queryCount.setParameter("departmentId",departmentEntity.getDepartmentId());
        }

        try {
            employeeEntities = query.getResultList();
            count = Integer.parseInt(String.format("%d",queryCount.getSingleResult()));
        }catch (HibernateException e){
            throw e;
        }finally {
            entityManager.close();
        }

        return new Object[]{count,employeeEntities};
    }

    @Override
    public EmployeeEntity findById(Integer integer) {
            EmployeeEntity entity = null;
            try{
                entity =  entityManager.find(persistenClass,integer);
                if(entity == null){
                    throw new NotFoundObjectException();
                }
                entity.getDepartmentEntity();

            } catch (HibernateException e){
                throw e;
            }
            finally {
                entityManager.close();
            }
            return entity;
    }

}
