package com.github.an0rakdev.talkgcpmamie.datas;

import java.util.ArrayList;
import java.util.List;
import com.github.an0rakdev.talkgcpmamie.datas.ProductRepository;
import com.github.an0rakdev.talkgcpmamie.pojos.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
	@Query("SELECT p FROM Product p ORDER BY p.code ASC")
	public List<Product> list();

	@Query("SELECT p FROM Product p WHERE p.code = ?1")
	public Product find(String code);
}
