package com.github.an0rakdev.talkgcpmamie.datas;

import com.github.an0rakdev.talkgcpmamie.pojos.Stock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.SQLException;

@Repository
public class StockRepository {

	@PersistenceContext
	private EntityManager em;

	public Stock find(String code) {
		Query q = em.createQuery("SELECT s FROM Stock s WHERE s.code = :c");
		q.setParameter("c", code);
		return (Stock) q.getSingleResult();
	}

	@Transactional
	public void updateQuantity(String code, double newQuantity) throws SQLException {
		Query q = em.createQuery("UPDATE Stock SET s.quantity = :q WHERE s.code = :c");
		q.setParameter("c", code);
		q.setParameter("q", newQuantity);
		if (q.executeUpdate() <= 0) {
			throw new SQLException("No update on Stock made for " + code);
		};
	}
}
