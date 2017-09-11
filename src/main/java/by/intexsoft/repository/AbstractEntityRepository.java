package by.intexsoft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * An abstract interface for configuring all common methods in the repository
 * class for working with the database. The interface extends the interface
 * JpaRepository.
 * 
 * @see {@link JpaRepository}
 */
@NoRepositoryBean
public interface AbstractEntityRepository<T> extends JpaRepository<T, Integer> {
	List<T> findAll();
}
