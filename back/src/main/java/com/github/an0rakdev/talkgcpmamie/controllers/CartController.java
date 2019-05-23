package com.github.an0rakdev.talkgcpmamie.controllers;

import java.util.ArrayList;
import java.util.List;
import com.github.an0rakdev.talkgcpmamie.pojos.Cart;
import com.github.an0rakdev.talkgcpmamie.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/cart")
public class CartController {
	@Autowired 
	private CartService cartService;

	@GetMapping("/count")
	public ResponseEntity<Integer> countCartElements() {
		return ResponseEntity.ok().body(cartService.getNbOfProducts());
	}

	@PostMapping("")
	public ResponseEntity<Void> addToCart(@RequestBody AddBody body) {
		cartService.addProduct(body.code, (null == body.qty) ? 0 : body.qty);
		return ResponseEntity.ok().build();
	}

	private static class AddBody {
		public String code;
		public Integer qty;

		public AddBody() {
			this("", 0);
		}

		public AddBody(String code, Integer qty) {
			this.code = code;
			this.qty = qty;
		}
	}
}
