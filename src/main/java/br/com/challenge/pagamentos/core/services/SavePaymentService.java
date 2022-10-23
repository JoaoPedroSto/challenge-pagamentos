package br.com.challenge.pagamentos.core.services;

import br.com.challenge.pagamentos.app.entrypoint.dto.PaymentsRequestDto;
import br.com.challenge.pagamentos.app.entrypoint.dto.PaymentsResponseDTO;

public interface SavePaymentService {
    public PaymentsResponseDTO persistPayment(PaymentsRequestDto paymentDto);
}
