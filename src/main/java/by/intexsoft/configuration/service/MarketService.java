package by.intexsoft.configuration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import by.intexsoft.entity.Market;
import by.intexsoft.repository.MarketRepository;

@Service
public class MarketService {
	private MarketRepository marketRepository;

	@Autowired
	MarketService(MarketRepository marketRepository) {
		this.marketRepository = marketRepository;
	}

	public Market findByName(String nameMarket) {
		return marketRepository.findByName(nameMarket);
	}

	public Market save(Market market) {
		return marketRepository.save(market);
	}

	public Market findOne(int id) {
		return marketRepository.findOne(id);
	}

	/**
	 * Deletes the market by id.
	 */
	public void delete(int id) {
		marketRepository.delete(id);
	}

	public List<Market> findAllMarket() {
		return marketRepository.findAll();
	}

	public void signMarketInStock(int idMarket, int idStock) {
		//marketRepository.signMarketinStock(idMarket, idStock);
	}
}
