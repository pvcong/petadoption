package com.ck.entitydao;

import com.ck.dao.generic.GenericDAOImpl;
import com.ck.data.PetAboutEntity;
import com.ck.data.PetTypeEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class PetAboutDAOImpl extends GenericDAOImpl<Integer, PetAboutEntity> implements PetAboutDAO {
}
