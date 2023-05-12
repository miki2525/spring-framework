package pl.training.shop.payments.adapters.web;

import org.mapstruct.Mapper;
import pl.training.shop.commons.MoneyMapper;
import pl.training.shop.payments.domain.PaymentDomain;
import pl.training.shop.payments.domain.PaymentRequestDomain;

@Mapper(componentModel = "spring", uses = MoneyMapper.class)
public interface WebPaymentMapper {

    PaymentRequestDomain toDomain(PaymentRequestViewModel  paymentRequestViewModel);

    PaymentViewModel toViewModel(PaymentDomain paymentDomain);

}
