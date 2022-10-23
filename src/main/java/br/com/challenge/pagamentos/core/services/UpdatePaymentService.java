package br.com.challenge.pagamentos.core.services;

import br.com.challenge.pagamentos.app.entrypoint.dto.PaymentsRequestDto;

public interface UpdatePaymentService {
    public void updatePayment(PaymentsRequestDto paymentsRequestDto, String id);
}
