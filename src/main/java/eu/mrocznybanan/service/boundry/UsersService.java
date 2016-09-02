package eu.mrocznybanan.service.boundry;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import eu.mrocznybanan.service.entity.User;

@Stateless
public class UsersService {

    @PersistenceContext
    private EntityManager em;

    public List<User> findAll() {
        List<User> users = em.createQuery("SELECT u FROM User u", User.class).getResultList();
        if (users == null) {
            users = new ArrayList<User>();
        }
        return users;
    }

}
