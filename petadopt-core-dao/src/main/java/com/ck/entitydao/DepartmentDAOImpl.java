package com.ck.entitydao;

import com.ck.dao.generic.GenericDAOImpl;
import com.ck.data.DepartmentEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class DepartmentDAOImpl extends GenericDAOImpl<Integer, DepartmentEntity> implements DepartmentDAO {
}
