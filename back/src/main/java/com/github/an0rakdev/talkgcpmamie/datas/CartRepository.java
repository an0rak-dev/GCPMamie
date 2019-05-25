package com.github.an0rakdev.talkgcpmamie.datas;

import java.util.Map;
import java.util.HashMap;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.github.an0rakdev.talkgcpmamie.pojos.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class CartRepository {
	@PersistenceContext
	private EntityManager em;

	//@Query("SELECT quantity FROM Cart WHERE code = ?1 ")
	public Integer findQuantityOf(String code) {
		Query q = em.createQuery("SELECT quantity FROM Cart WHERE code = :c");
		q.setParameter("c", code);
		try {
			Double result = (Double) q.getSingleResult();
			if (null == result) {
				return 0;
			} 
			return result.intValue();
		} catch (NoResultException ex) {
			return 0;
		}
	}

	//@Query("UPDATE Cart SET quantity = ?2 WHERE code = ?1")
	public void updateQuantity(String code, int newQuantity) {
		Query q = em.createQuery("UPDATE Cart SET quantity = :q WHERE code = :c");
		q.setParameter("q", newQuantity);
		q.setParameter("c", code);
		q.executeUpdate();
	}

	@Transactional
	public void insertQuantity(String code, int quantity) {
		this.em.persist(new Cart(code, quantity));
	}

	//@Query("SELECT COALESCE(SUM(quantity), 0) FROM Cart")
	public int countAll() {
		Query q = em.createQuery("SELECT COALESCE(SUM(quantity), 0) FROM Cart");
		Double result =  (Double) q.getSingleResult();
		if (null == result) {
			return 0;
		}
		return result.intValue();
	}
}
