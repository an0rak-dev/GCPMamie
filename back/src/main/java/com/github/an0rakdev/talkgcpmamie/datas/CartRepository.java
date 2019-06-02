package com.github.an0rakdev.talkgcpmamie.datas;

import java.util.Map;
import java.util.HashMap;
import javax.persistence.*;

import com.github.an0rakdev.talkgcpmamie.pojos.Cart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class CartRepository {
	Logger logger = LoggerFactory.getLogger(CartRepository.class);

	@PersistenceContext
	private EntityManager em;

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

	@Transactional
	public void updateQuantity(String code, int newQuantity) {
		Query q = em.createQuery("UPDATE Cart SET quantity = :q WHERE code = :c");
		q.setParameter("q", (double) newQuantity);
		q.setParameter("c", code);
		if (q.executeUpdate() <= 0) {
			throw new EntityNotFoundException(code);
		};
	}

	@Transactional
	public void insertQuantity(String code, int quantity) {
		this.em.persist(new Cart(code, quantity));
	}

	public int countAll() {
		Query q = em.createQuery("SELECT COALESCE(SUM(quantity), 0) FROM Cart");
		Double result =  (Double) q.getSingleResult();
		if (null == result) {
			return 0;
		}
		return result.intValue();
	}
}
