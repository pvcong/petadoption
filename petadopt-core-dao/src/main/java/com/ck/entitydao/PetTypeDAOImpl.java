package com.ck.entitydao;

import com.ck.dao.generic.GenericDAOImpl;
import com.ck.data.PetTypeEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class PetTypeDAOImpl extends GenericDAOImpl<Integer, PetTypeEntity> implements PetTypeDAO {
}
