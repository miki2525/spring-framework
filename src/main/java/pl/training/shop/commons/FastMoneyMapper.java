package pl.training.shop.commons;

import org.javamoney.moneta.FastMoney;
import org.mapstruct.Mapper;

import java.util.Optional;
import java.util.function.Function;

@Mapper(componentModel = "spring")
public interface FastMoneyMapper {

    default String toText(FastMoney money) {
        return map(money, FastMoney::toString);
    }

    default FastMoney toMoney(String text) {
        return map(text, FastMoney::parse);
    }

    private <I, O> O map(I input, Function<I, O> mapper) {
        return Optional.ofNullable(input)
                .map(mapper)
                .orElseThrow(IllegalArgumentException::new);
    }

}
