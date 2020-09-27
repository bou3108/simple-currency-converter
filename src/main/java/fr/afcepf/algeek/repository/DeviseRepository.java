package fr.afcepf.algeek.repository;

import fr.afcepf.algeek.entity.Devise;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * DeviseRepository is the data access interface for the class Devise.
 * It extends MongoRepository
 *
 * @see org.springframework.data.mongodb.repository.MongoRepository
 * @see fr.afcepf.algeek.entity.Devise
 *
 * @author bou3108
 */
public interface DeviseRepository extends MongoRepository<Devise, String> {

}
