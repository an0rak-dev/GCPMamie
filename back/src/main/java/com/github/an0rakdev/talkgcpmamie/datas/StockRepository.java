package com.github.an0rakdev.talkgcpmamie.datas;

import java.util.Map;
import java.util.HashMap;
import com.github.an0rakdev.talkgcpmamie.pojos.Stock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, String> {
	
	@Query(value="SELECT s FROM Stock s WHERE s.code = ?1")
	public Stock find(String code);

	@Transactional
	@Modifying
	@Query(value="UPDATE Stock SET quantity = ?2 WHERE code = ?1")
	public void updateQuantity(String code, double newQuantity);
}
