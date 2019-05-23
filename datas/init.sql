CREATE USER ssd_pg CREATEDB PASSWORD 'password_en_clair_pas_bien';

CREATE SCHEMA ssd_prod AUTHORIZATION ssd_pg;

CREATE TABLE IF NOT EXISTS ssd_prod.products (
	p_code VARCHAR(10) NOT NULL,
	p_name VARCHAR(100) NOT NULL,
	p_price DECIMAL NOT NULL,
	CONSTRAINT "pk_product" PRIMARY KEY (p_code)
);

ALTER TABLE ssd_prod.products OWNER TO ssd_pg;

CREATE TABLE IF NOT EXISTS ssd_prod.stocks (
	s_code VARCHAR(10) NOT NULL,
	s_price DECIMAL NOT NULL,
	CONSTRAINT "pk_stock" PRIMARY KEY (s_code),
	CONSTRAINT "fk_stock_product" FOREIGN KEY (s_code) REFERENCES ssd_prod.products(p_code)
);

ALTER TABLE ssd_prod.stocks OWNER TO ssd_pg;

CREATE TABLE IF NOT EXISTS ssd_prod.cart (
	c_code VARCHAR(10) NOT NULL,
	d_quantity DECIMAL NOT NULL,
	CONSTRAINT "pk_cart" PRIMARY KEY (c_code),
	CONSTRAINT "fk_stock_cart" FOREIGN KEY (c_code) REFERENCES ssd_prod.products(p_code)
);

ALTER TABLE ssd_prod.cart OWNER TO ssd_pg;
