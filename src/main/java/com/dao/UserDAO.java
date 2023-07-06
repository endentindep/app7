package com.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.models.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Component
public class UserDAO {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void saveUser(User user) {
		em.persist(user);
	}

	@Transactional
	public User findUser(Long id) {
		return em.find(User.class, id);
	}

	@Transactional
	public void removeUser(Long id) {
		User user = em.find(User.class, id);
		em.remove(user);
	}

	@Transactional
	public void updateUser(User user, String name, String status) {
		em.detach(user);
		user.setName(name);
		user.setStatus(status);
		em.merge(user);
	}

	@Transactional
	public List<? extends User> getUsers() {
		return em.createQuery("from User").getResultList();
	}
}
