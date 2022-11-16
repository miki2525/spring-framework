package pl.training.shop.commons.money;

import org.javamoney.moneta.FastMoney;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface FastMoneyMapper {

    default String toText(FastMoney money) {
        return Optional.ofNullable(money)
                .map(FastMoney::toString)
                .orElseThrow(IllegalArgumentException::new);
    }

    default FastMoney toMoney(String text) {
        return Optional.ofNullable(text)
                .map(FastMoney::parse)
                .orElseThrow(IllegalArgumentException::new);
    }

}
