package by.intexsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import by.intexsoft.entity.Market;

/**
 * The interface of centralized storage. for interaction with the database table
 * Market. Bean is created with the help of which it will be extracted from the
 * database and will be transmitted will return the entity.
 * 
 * @see {@link JpaRepository}
 */
public interface MarketRepository extends AbstractEntityRepository<Market> {
	/**
	 * Looks for in the repository of all stores in which the name corresponds to
	 * the passed parameter.
	 * 
	 * @see {@link Market}
	 */
	Market findByName(String nameMarket);

	/**
	 * The method signs the store for goods that are in stock.
	 */
	@Modifying(clearAutomatically = true)
	@Query("INSERT INTO market_product(products, markets) SELECT sp.product, m.id FROM market m, product_stock sp INNER JOIN product pr ON pr.id = sp.product INNER JOIN stock st ON st.id = sp.stocks WHERE st.id =:idStock AND m.id =:idMarket")
	void signMarketinStock(@Param("idMarket") int idMarket, @Param("idStock") int idStock);

}
