package com.ck.entitydao;

import com.ck.dao.generic.GenericDAOImpl;
import com.ck.data.PetEntryStatusEntity;
import com.ck.data.PetEntryTypeEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class PetEntryStatusDAOImpl extends GenericDAOImpl<Integer, PetEntryStatusEntity> implements PetEntryStatusDAO{
}
