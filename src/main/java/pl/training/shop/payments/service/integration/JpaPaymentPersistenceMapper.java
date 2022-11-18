package pl.training.shop.payments.service.integration;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.training.shop.payments.persistence.PaymentEntity;
import pl.training.shop.payments.service.PaymentDomain;

@Mapper(componentModel = "spring", imports = {java.math.BigDecimal.class, org.javamoney.moneta.Money.class})
public interface JpaPaymentPersistenceMapper {

    @Mapping(target = "value", expression = "java(BigDecimal.valueOf(paymentDomain.getValue().getNumber().doubleValueExact()))")
    @Mapping(target = "currency", expression = "java(paymentDomain.getValue().getCurrency().getCurrencyCode())")
    PaymentEntity toEntity(PaymentDomain paymentDomain);

    @Mapping(target = "value", expression = "java(Money.of(paymentEntity.getValue(), paymentEntity.getCurrency()))")
    PaymentDomain toDomain(PaymentEntity paymentEntity);

}
