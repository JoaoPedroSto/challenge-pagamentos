package br.com.challenge.pagamentos.app.entrypoint;

import br.com.challenge.pagamentos.app.entrypoint.dto.PagamentosRequestDto;
import br.com.challenge.pagamentos.app.entrypoint.filter.FiltroBuscaPagamento;
import br.com.challenge.pagamentos.core.services.SalvarPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("challenge/v1")
public class PagamentosController {

    @Autowired
    private SalvarPagamentoService salvarService;

    @PostMapping(value = "/pagamentos")
    public ResponseEntity incluirPagamento(@RequestBody @Valid PagamentosRequestDto pagamentos){
        salvarService.persistPagamento(pagamentos);
        return ResponseEntity.ok(null);
    }

    @GetMapping(value = "/pagamentos/{id}")
    public ResponseEntity consultarPagamento (@PathVariable Integer id){
        return null;
    }

    @GetMapping(value = "/pagamentos")
    public ResponseEntity consultarPagamentosPorFiltro (@RequestParam FiltroBuscaPagamento filtro){
        return null;
    }

    @PutMapping(value = "/pagamentos/{id}")
    public ResponseEntity atualizarPagamento(@PathVariable Integer id ,@RequestBody @Valid PagamentosRequestDto pagamentos){
        return null;
    }

    @DeleteMapping(value = "/pagamentos/{id}")
    public RequestEntity deletarPagamento(@PathVariable Integer id){
        return null;
    }

}
