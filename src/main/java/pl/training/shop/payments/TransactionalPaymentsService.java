package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import pl.training.shop.commons.data.Page;
import pl.training.shop.commons.data.ResultPage;
import pl.training.shop.payments.domain.PaymentDomain;
import pl.training.shop.payments.domain.PaymentRequestDomain;
import pl.training.shop.payments.domain.PaymentStatusDomain;
import pl.training.shop.payments.ports.PaymentService;

//@Primary
//@Transactional
//@Component
@RequiredArgsConstructor
public class TransactionalPaymentsService implements PaymentService {

    private final PaymentService paymentService;

    @Override
    public PaymentDomain process(PaymentRequestDomain paymentRequest) {
        return paymentService.process(paymentRequest);
    }

    @Override
    public PaymentDomain getById(String id) {
        return paymentService.getById(id);
    }

    @Override
    public ResultPage<PaymentDomain> getByStatus(PaymentStatusDomain paymentStatusDomain, Page page) {
        return paymentService.getByStatus(paymentStatusDomain, page);
    }

}
