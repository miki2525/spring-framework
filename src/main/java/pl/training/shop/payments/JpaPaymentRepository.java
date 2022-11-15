package pl.training.shop.payments;

import lombok.Setter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

public class JpaPaymentRepository implements PaymentRepository {

    @Setter
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Payment save(Payment payment) {
        entityManager.persist(payment);
        return payment;
    }

    @Override
    public Optional<Payment> getById(String id) {
        return Optional.ofNullable(entityManager.find(Payment.class, id));
    }

}
