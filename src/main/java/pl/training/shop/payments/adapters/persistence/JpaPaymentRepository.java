package pl.training.shop.payments.adapters.persistence;

import lombok.Setter;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class JpaPaymentRepository {

    @Setter
    @PersistenceContext
    private EntityManager entityManager;

    public PaymentEntity save(PaymentEntity payment) {
        entityManager.persist(payment);
        return payment;
    }

    public Optional<PaymentEntity> getById(String id) {
        return Optional.ofNullable(entityManager.find(PaymentEntity.class, id));
    }

}
