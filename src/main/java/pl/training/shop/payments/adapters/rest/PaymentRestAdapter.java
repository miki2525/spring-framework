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

    @GetMapping("{id}")
    public ResponseEntity<PaymentDto> getById(@PathVariable String id) {
        var paymentDomain = paymentService.getById(id);
        var paymentDto = mapper.toDto(paymentDomain);
        return ResponseEntity.ok(paymentDto);
    }

   @PostMapping
   public ResponseEntity<PaymentDto> process(@RequestBody PaymentRequestDto paymentRequestDto) {
        var paymentRequest = mapper.toDomain(paymentRequestDto);
        var paymentDomain = paymentService.process(paymentRequest);
        var paymentDto = mapper.toDto(paymentDomain);
        var locationUri = LocationUri.fromRequest(paymentDto.getId());
        return ResponseEntity.created(locationUri)
                .body(paymentDto);
   }


}
