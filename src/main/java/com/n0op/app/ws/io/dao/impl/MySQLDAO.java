package com.n0op.app.ws.io.dao.impl;

import com.n0op.app.ws.io.dao.DAO;
import com.n0op.app.ws.io.entity.RunEntity;
import com.n0op.app.ws.io.entity.UserEntity;
import com.n0op.app.ws.shared.dto.RunDTO;
import com.n0op.app.ws.shared.dto.UserDTO;
import com.n0op.app.ws.utils.HibernateUtils;
import com.n0op.app.ws.utils.UserUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeanUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author DanM
 */
public class MySQLDAO implements DAO {
    Session session;
    UserUtils userUtils = new UserUtils();

    @Override
    public void openConnection() {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        session = sessionFactory.openSession();
    }

    @Override
    public UserDTO getUserByName(String name) {
        return null;
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        UserDTO returnValue;
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDTO, userEntity);

        String hash = userUtils.hashPassword(userDTO.getPassword(), userDTO.getSalt()).toString();
        userEntity.setHash(hash);

        session.beginTransaction();
        session.save(userEntity);
        session.getTransaction().commit();

        returnValue = new UserDTO();
        BeanUtils.copyProperties(userEntity, returnValue);

        return returnValue;
    }

    @Override
    public UserDTO getUser(String id) {
        return null;
    }

    @Override
    public List<UserDTO> getUsers(int start, int limit) {
        return null;
    }

    @Override
    public void updateUser(UserDTO userDTO) {

    }


    @Override
    public void deleteUser(UserDTO userDTO) {

    }

    @Override
    public RunDTO getRunByName(String runName) {
        return null;
    }

    @Override
    public RunDTO saveRun(RunDTO runDTO) {

        RunDTO returnValue = null;
        RunEntity runEntity = new RunEntity();
        BeanUtils.copyProperties(runDTO, runEntity);

        session.beginTransaction();
        session.save(runEntity);
        session.getTransaction().commit();

        returnValue = new RunDTO();
        BeanUtils.copyProperties(runEntity, returnValue);

        return returnValue;
    }

    @Override
    public RunDTO getRun(String id) {
        CriteriaBuilder cb = session.getCriteriaBuilder();

        // Create Criteria against a particular persistent class
        CriteriaQuery<RunEntity> criteria = cb.createQuery(RunEntity.class);

        //Query roots always reference entity
        Root<RunEntity> profileRoot = criteria.from(RunEntity.class);
        criteria.select(profileRoot);
        criteria.where(cb.equal(profileRoot.get("runId"), id));

        // Fetch single result
        RunEntity runEntity = session.createQuery(criteria).getSingleResult();

        RunDTO runDTO = new RunDTO();
        BeanUtils.copyProperties(runEntity, runDTO);


        return runDTO;
    }

    @Override
    public List<RunDTO> getRuns(int start, int limit) {
        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery<RunEntity> criteria = cb.createQuery(RunEntity.class);

        Root<RunEntity> runEntityRoot = criteria.from(RunEntity.class);
        criteria.select(runEntityRoot);

        List<RunEntity> searchResults = session.createQuery(criteria)
                .setFirstResult(start)
                .setMaxResults(limit)
                .getResultList();

        List<RunDTO> returnValue = new ArrayList<>();

        for (RunEntity runEntity : searchResults) {
            RunDTO runDTO = new RunDTO();
            BeanUtils.copyProperties(runEntity, runDTO);
            returnValue.add(runDTO);
        }

        return returnValue;
    }

    @Override
    public void updateRun(RunDTO runDTO) {
        RunEntity runEntity = new RunEntity();
        BeanUtils.copyProperties(runDTO, runEntity);

        session.beginTransaction();
        session.update(runEntity);
        session.getTransaction().commit();
    }

    @Override
    public void deleteRun(RunDTO runDTO) {
        RunEntity runEntity = new RunEntity();
        BeanUtils.copyProperties(runDTO, runEntity);

        session.beginTransaction();
        session.delete(runEntity);
        session.getTransaction().commit();
    }

    @Override
    public void closeConnection() {
        if (session != null) {
            session.close();
        }
    }
}

