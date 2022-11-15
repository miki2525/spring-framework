package pl.training.shop.payments.service.integration;

import lombok.RequiredArgsConstructor;
import pl.training.shop.commons.Adapter;
import pl.training.shop.payments.persistence.JpaPaymentRepository;
import pl.training.shop.payments.service.PaymentDomain;

import java.util.Optional;

@Adapter
@RequiredArgsConstructor
public class JpaPaymentRepositoryAdapter implements PaymentRepository {

    private final JpaPaymentRepository paymentRepository;
    private final JpaPersistenceMapper mapper;

    @Override
    public PaymentDomain save(PaymentDomain paymentDomain) {
        var paymentEntity = mapper.toEntity(paymentDomain);
        return mapper.toDomain(paymentRepository.save(paymentEntity));
    }

    @Override
    public Optional<PaymentDomain> getById(String id) {
        return paymentRepository.getById(id)
                .map(mapper::toDomain);
    }

}
