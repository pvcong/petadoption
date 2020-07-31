package com.ck.entitydao;

import com.ck.dao.generic.GenericDAOImpl;
import com.ck.data.PetEntryTypeEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class PetEntryTypeDAOImpl extends GenericDAOImpl<Integer, PetEntryTypeEntity> implements PetEntryTypeDAO{
}
