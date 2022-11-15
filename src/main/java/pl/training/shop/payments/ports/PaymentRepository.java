package pl.training.shop.payments.ports;

import pl.training.shop.commons.data.Page;
import pl.training.shop.commons.data.ResultPage;
import pl.training.shop.payments.domain.PaymentDomain;
import pl.training.shop.payments.domain.PaymentStatusDomain;

import java.util.Optional;

public interface PaymentRepository {

    PaymentDomain save(PaymentDomain paymentDomain);

    Optional<PaymentDomain> getById(String id);

    ResultPage<PaymentDomain> getByStatus(PaymentStatusDomain paymentStatusDomain, Page page);

}
