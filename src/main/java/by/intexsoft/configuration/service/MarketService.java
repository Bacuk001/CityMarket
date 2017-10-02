package by.intexsoft.configuration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import by.intexsoft.entity.Market;
import by.intexsoft.repository.MarketRepository;

/**
 * Service to serve the market. the service adds the market, deletes, returns
 * the market by id, searches for the name. {@link JpaRepository},
 * {@link Market}, {@link MarketRepository}
 */
@Service
public class MarketService {
	private MarketRepository marketRepository;

	@Autowired
	public MarketService(MarketRepository marketRepository) {
		this.marketRepository = marketRepository;
	}

	/**
	 * The service method takes the name of the string, accesses the repository to
	 * search the database of the market whose name is the same. and returns the
	 * market object.
	 * 
	 * @return {@link Market}
	 */
	public Market findByName(String nameMarket) {
		return marketRepository.findByName(nameMarket);
	}

	/**
	 * The service method accepts the market object and sends it to the repository
	 * for saving in the database.
	 */
	public Market save(Market market) {
		return marketRepository.save(market);
	}

	/**
	 * The service method takes the market's id and requests from the repository the
	 * market in which the id matches the parameters being passed.
	 */
	public Market findOne(int id) {
		return marketRepository.findOne(id);
	}

	/**
	 * Deletes the market by id.
	 */
	public void delete(int id) {
		marketRepository.delete(id);
	}

	/**
	 * The service method requests information about all markets from the repository
	 * and returns them to the user of the class.
	 * 
	 * @return {@link List} <{@link Market}>
	 */
	public List<Market> findAllMarket() {
		return marketRepository.findAll();
	}
}
