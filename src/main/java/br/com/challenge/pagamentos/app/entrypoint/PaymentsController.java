package br.com.challenge.pagamentos.app.entrypoint;

import br.com.challenge.pagamentos.app.entrypoint.dto.PaymentsRequestDto;
import br.com.challenge.pagamentos.app.entrypoint.filter.PaymentSearchFilter;
import br.com.challenge.pagamentos.core.services.UpdatePaymentService;
import br.com.challenge.pagamentos.core.services.FindPaymentService;
import br.com.challenge.pagamentos.core.services.DeletePaymentService;
import br.com.challenge.pagamentos.core.services.SavePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("challenge/v1")
public class PaymentsController {

    @Autowired
    private SavePaymentService saveService;
    @Autowired
    private FindPaymentService findService;
    @Autowired
    private DeletePaymentService deleteService;
    @Autowired
    private UpdatePaymentService updateService;

    @PostMapping(value = "/pagamentos")
    public ResponseEntity includePayment(@RequestBody @Valid PaymentsRequestDto payments){
        var response = saveService.persistPayment(payments);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(value = "/pagamentos/{id}")
    public ResponseEntity consultPayment(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(findService.searchById(id).orElse(null));
    }

    @GetMapping(value = "/pagamentos")
    public ResponseEntity consultPaymentsByFilter(@RequestParam @NotNull PaymentSearchFilter filter){
        return ResponseEntity.ok(findService.searchPayment(filter));
    }

    @PutMapping(value = "/pagamentos/{id}")
    public ResponseEntity updatePayment(@PathVariable @NotNull String id , @RequestBody @Valid PaymentsRequestDto payment){
        updateService.updatePayment(payment, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping(value = "/pagamentos/{id}")
    public ResponseEntity deletePayment(@PathVariable String id){
        deleteService.deletePayment(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
