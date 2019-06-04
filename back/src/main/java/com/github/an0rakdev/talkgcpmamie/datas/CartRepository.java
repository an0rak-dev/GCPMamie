package com.github.an0rakdev.talkgcpmamie.datas;

import java.sql.SQLException;
import java.util.Map;
import java.util.HashMap;
import javax.persistence.*;

import com.github.an0rakdev.talkgcpmamie.pojos.Cart;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class CartRepository {
	Log logger = LogFactory.getLog(CartRepository.class);

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
			logger.warn("No result in cart for code " + code);
			return 0;
		}
	}

	@Transactional
	public boolean updateQuantityOf(String code, int newQuantity) {
		Query q = em.createQuery("UPDATE Cart c SET c.quantity = :q WHERE c.code = :c");
		q.setParameter("q", (double) newQuantity);
		q.setParameter("c", code);
		return q.executeUpdate() <= 0;
	}

	@Transactional
	public boolean insertQuantity(String code, int quantity) {
		Query q = em.createNativeQuery("INSERT INTO ssd_prod.cart VALUES (:c,:q)");
		q.setParameter("c", code);
		q.setParameter("q", quantity);
		return q.executeUpdate() <= 0;
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
