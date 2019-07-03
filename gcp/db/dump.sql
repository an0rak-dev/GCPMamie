--
-- PostgreSQL database dump
--

-- Dumped from database version 11.1
-- Dumped by pg_dump version 11.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: ssd_prod; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA ssd_prod;


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: cart; Type: TABLE; Schema: ssd_prod; Owner: -
--

CREATE TABLE ssd_prod.cart (
    c_code character varying(10) NOT NULL,
    c_quantity numeric NOT NULL
);


--
-- Name: product; Type: TABLE; Schema: ssd_prod; Owner: -
--

CREATE TABLE ssd_prod.product (
    p_code character varying(10) NOT NULL,
    p_name character varying(100) NOT NULL,
    p_price numeric NOT NULL,
    p_desc character varying(500) NOT NULL,
    img character varying(255)
);


--
-- Name: stock; Type: TABLE; Schema: ssd_prod; Owner: -
--

CREATE TABLE ssd_prod.stock (
    s_code character varying(10) NOT NULL,
    s_quantity numeric NOT NULL
);


--
-- Data for Name: cart; Type: TABLE DATA; Schema: ssd_prod; Owner: -
--

COPY ssd_prod.cart (c_code, c_quantity) FROM stdin;
p01	3
\.


--
-- Data for Name: product; Type: TABLE DATA; Schema: ssd_prod; Owner: -
--

COPY ssd_prod.product (p_code, p_name, p_price, p_desc, img) FROM stdin;
p06	Gant de l'infini	2000.99	Un gant de soirée parfait pour vos cocktails. <br/><br/>Ce magnifique gant en or sertis de 6 pierres précieuses se mariera parfaitement à tout vos événements mondain. <br/><br/>Vous aurez une classe folle quand vos claquerez des doigts pour appeler un serveur.<br/><br/>	https://target.scene7.com/is/image/Target/GUEST_bbce35d0-46bc-4e74-8edf-aad4dd0f12ee?wid=488&hei=488&fmt=pjpeg
p07	Stargate The Game	68.99	Le Jeu vidéo officiel tiré de la série et du film éponyme. <br/><br/>Bon ok là on rêve tout debout.<br/><br/>	https://www.gamepressure.com/gallery/html/article/172044267.jpg
p08	Apple AirPower	2000.99	Ceci est une révolution. <br/><br/>La charge sans-fil réinventée par Apple. Pourra s'utiliser parfaitement pour vos iPhones et AirPods. <br/><br/>Note : Ne peut charger que des produits Apple (juste parce que voila).<br/><br/>	https://cdn.macrumors.com/article-new/2018/06/airpower-airpods-800x642.jpg
p01	Half-Life 3	45.99	Le fameux jeux. Après 10ans d'attente. <br/><br/> Développé par Valve (espèrons le... un jour...)<br/><br/>	https://image.redbull.com/rbcom/010/2014-07-04/1331663352536_2/0010/1/2100/1400/1/half-life-3-esports-game-logo.jpg
p02	Le Film Kaamelott	21.99	<i>Sire ! Sire ! On en a Gros !</i> <br/><br/> La conclusion de la série Kaamelott, développée par son créateur. <br/><br/> Et en bonus, une version karaoké de "A la Volette".<br/><br/>	https://static.papergeek.fr/2017/04/kaamelott-film-tournage.jpg
p03	Nez de Voldemort	30.0	Un Objet de Légende. <br/><br/>On dit que son possesseur l'a perdu durant une partie de "Je t'ai volé ton nez" qui aurait mal tourné. <br/><br/>Certifié sans Horcruxe.<br/><br/>	https://storage.googleapis.com/salle-sur-demande/image/voldemort_nose.png
p04	Anneau Unique	9.99	Une merveille d'orfèvrerie.<br/><br/>Cet anneau en or serait investi de grands pouvoirs selon les rumeurs... <br/><br/>Note : attention une utilisation prolongée peut entrainer perte de l'âme et pulsion sanguinaire.<br/><br/>	https://external-preview.redd.it/NT4m3-Wh0tBCpanCv5dyEki42bFgKF02w39LGwO_mhQ.jpg?width=960&crop=smart&auto=webp&s=9c81b459d7565fc1dfddafece84107674af258a3
p05	The Elder Scrolls VI	59.08	Le dernier né des studios Bethesda. <br/><br/>Partez dans les contrées de Tamriel avec ce nouvel opus des Elder Scrolls ! <br/><br/>Note: Il ne s'agit que d'une prévente, le jeu sera disponible après la sortie de Skyrim sur tous les appareils fonctionnant à l'électricité dans le monde.<br/><br/>	https://i0.wp.com/billionaire365.com/wp-content/uploads/2019/06/The-Elder-Scrolls-6.jpg?resize=744%2C446&ssl=1
\.


--
-- Data for Name: stock; Type: TABLE DATA; Schema: ssd_prod; Owner: -
--

COPY ssd_prod.stock (s_code, s_quantity) FROM stdin;
p07	99
p08	183
p03	50
p06	0
p02	46
p04	33
p01	42
p05	85
\.


--
-- Name: cart pk_cart; Type: CONSTRAINT; Schema: ssd_prod; Owner: -
--

ALTER TABLE ONLY ssd_prod.cart
    ADD CONSTRAINT pk_cart PRIMARY KEY (c_code);


--
-- Name: product pk_product; Type: CONSTRAINT; Schema: ssd_prod; Owner: -
--

ALTER TABLE ONLY ssd_prod.product
    ADD CONSTRAINT pk_product PRIMARY KEY (p_code);


--
-- Name: stock pk_stock; Type: CONSTRAINT; Schema: ssd_prod; Owner: -
--

ALTER TABLE ONLY ssd_prod.stock
    ADD CONSTRAINT pk_stock PRIMARY KEY (s_code);


--
-- Name: cart fk_stock_cart; Type: FK CONSTRAINT; Schema: ssd_prod; Owner: -
--

ALTER TABLE ONLY ssd_prod.cart
    ADD CONSTRAINT fk_stock_cart FOREIGN KEY (c_code) REFERENCES ssd_prod.product(p_code);


--
-- Name: stock fk_stock_product; Type: FK CONSTRAINT; Schema: ssd_prod; Owner: -
--

ALTER TABLE ONLY ssd_prod.stock
    ADD CONSTRAINT fk_stock_product FOREIGN KEY (s_code) REFERENCES ssd_prod.product(p_code);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: -
--

REVOKE ALL ON SCHEMA public FROM cloudsqladmin;
REVOKE ALL ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO cloudsqlsuperuser;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--
