package by.intexsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
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
}
