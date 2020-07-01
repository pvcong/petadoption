package com.ck.entitydao;

import com.ck.dao.generic.GenericDAO;
import com.ck.dao.generic.GenericDAOImpl;
import com.ck.data.RoleEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class RoleDAOImpl extends GenericDAOImpl<Integer, RoleEntity> implements RoleDAO {
}
