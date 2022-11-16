package pl.training.shop.payments.adapters.rest;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.training.shop.commons.data.ResultPage;
import pl.training.shop.commons.money.FastMoneyMapper;
import pl.training.shop.commons.web.ResultPageDto;
import pl.training.shop.payments.domain.PaymentDomain;
import pl.training.shop.payments.domain.PaymentRequestDomain;

import java.util.List;

@Mapper(componentModel = "spring", uses = FastMoneyMapper.class)
public interface RestPaymentMapper {

    @Mapping(source = "requestId", target = "id")
    PaymentRequestDomain toDomain(PaymentRequestDto paymentRequestDto);

    PaymentDto toDto(PaymentDomain paymentDomain);

    @IterableMapping(elementTargetType = PaymentDto.class)
    List<PaymentDto> toDto(List<PaymentDomain> paymentDomains);

    ResultPageDto<PaymentDto> toDto(ResultPage<PaymentDomain> paymentDomainResultPage);

}
