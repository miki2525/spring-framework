package pl.training.shop.payments.adapters.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.training.shop.commons.web.LocationUri;
import pl.training.shop.payments.ports.PaymentService;

@RequestMapping("payments")
@RestController
@RequiredArgsConstructor
public class PaymentRestAdapter {

    private final PaymentService paymentService;
    private final RestPaymentMapper mapper;

    @PostMapping()
    public ResponseEntity<PaymentDto> process(@RequestBody PaymentRequestDto paymentRequestDto) {
        var paymentRequest = mapper.toDomain(paymentRequestDto);
        var payment = paymentService.process(paymentRequest);
        var paymentDto = mapper.toDto(payment);
        var locationUri = LocationUri.fromRequest(paymentDto.getId());
        return ResponseEntity.created(locationUri)
                .body(paymentDto);
    }

    @GetMapping("{id}")
    public ResponseEntity<PaymentDto> getById(@PathVariable String id) {
        return ResponseEntity.ok(new PaymentDto());
    }

}
