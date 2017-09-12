package by.intexsoft.configuration.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.intexsoft.entity.Description;
import by.intexsoft.entity.Product;
import by.intexsoft.repository.DescriptionRepository;

@Service
public class DescriptionService {
	private DescriptionRepository descriptionRepository;

	@Autowired
	DescriptionService(DescriptionRepository descriptionRepository) {
		this.descriptionRepository = descriptionRepository;
	}

	public List<Description> findByName(String nameDescription) {
		return descriptionRepository.findByName(nameDescription);
	}

	public List<Description> findByValue(String nameValue) {
		return descriptionRepository.findByValue(nameValue);
	}

	public List<Description> findByProduct(Product product) {
		return descriptionRepository.findByProduct(product);
	}

	public Description seve(Description description) {
		return descriptionRepository.save(description);
	}

	public List<Description> findAll() {
		return descriptionRepository.findAll();
	}

	public Description findOne(int id) {
		return descriptionRepository.findOne(id);
	}

	@Transactional
	public List<Description> seveListDescription(List<Description> descriptions) {
		for (int index = 0; index < descriptions.size(); index++) {
			descriptionRepository.save(descriptions.get(index));
		}
		return descriptions;
	}

	/**
	 * Deletes the description by id.
	 */
	public void delete(int id) {
		descriptionRepository.delete(id);
	}

}
