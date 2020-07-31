package com.ck.entitydao;

import com.ck.dao.generic.GenericDAOImpl;
import com.ck.data.PetStatusEntity;
import com.ck.data.PetStatusTypeEntity;
import com.ck.data.PetTypeEntity;
import com.ck.utils.JpaUtils;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Transactional
@Repository
public class PetStatusTypeDAOImpl extends GenericDAOImpl<Integer, PetStatusTypeEntity> implements PetStatusTypeDAO {


}
