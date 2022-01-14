package org.springframework.samples.petclinic.feeding;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FeedingRepository extends CrudRepository<Feeding, Integer>{
    
	@Query("SELECT f FROM Feeding f")
	List<Feeding> findAll();
    
	@Query("SELECT ft FROM FeedingType ft")
	List<FeedingType> findAllFeedingTypes();
    
	@Query("SELECT ft FROM FeedingType ft WHERE ft.name = ?1")
    FeedingType findFeedingTypeByName(String name);
	
	Optional<Feeding> findById(int id);
    Feeding save(Feeding p);
}
