package fr.afcepf.algeek.repository;

import fr.afcepf.algeek.entity.Devise;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeviseRepository extends MongoRepository<Devise, String> {

}
