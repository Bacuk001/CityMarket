package by.intexsoft.configuration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.intexsoft.entity.Category;
import by.intexsoft.repository.CategoryRepository;

@Service
public class CategoryService {
	private CategoryRepository categoryRepository;

	@Autowired
	CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public Category findByName(String nameCategory) {
		return categoryRepository.findByName(nameCategory);
	}

	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	public Category findOne(int id) {
		return categoryRepository.findOne(id);
	}

	/**
	 * Deletes the category by id.
	 */
	public void delete(int id) {
		categoryRepository.delete(id);
	}

	public Category save(Category category) {
		return categoryRepository.save(category);
	}
}
