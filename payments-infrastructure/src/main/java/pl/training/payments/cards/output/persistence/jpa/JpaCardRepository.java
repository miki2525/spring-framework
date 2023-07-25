package pl.training.payments.cards.output.persistence.jpa;

import org.springframework.data.repository.CrudRepository;

public interface JpaCardRepository extends CrudRepository<CardEntity, String> {
}
