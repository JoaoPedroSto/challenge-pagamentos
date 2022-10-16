package br.com.challenge.pagamentos.app.entrypoint;

import br.com.challenge.pagamentos.app.entrypoint.dto.PagamentosRequestDto;
import br.com.challenge.pagamentos.app.entrypoint.filter.FiltroBuscaPagamento;
import br.com.challenge.pagamentos.core.services.AtualizarPagamentoService;
import br.com.challenge.pagamentos.core.services.BuscarPagamentoService;
import br.com.challenge.pagamentos.core.services.DeletarPagamentoService;
import br.com.challenge.pagamentos.core.services.SalvarPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("challenge/v1")
public class PagamentosController {

    @Autowired
    private SalvarPagamentoService salvarService;
    @Autowired
    private BuscarPagamentoService buscarService;
    @Autowired
    private DeletarPagamentoService deletarService;
    @Autowired
    private AtualizarPagamentoService atualizarService;

    @PostMapping(value = "/pagamentos")
    public ResponseEntity incluirPagamento(@RequestBody @Valid PagamentosRequestDto pagamentos){
        salvarService.persistPagamento(pagamentos);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/pagamentos/{id}")
    public ResponseEntity consultarPagamento (@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(buscarService.searchById(id).orElse(null));
    }

    @GetMapping(value = "/pagamentos")
    public ResponseEntity consultarPagamentosPorFiltro (@RequestParam @NotNull FiltroBuscaPagamento filtro){
        return ResponseEntity.ok(buscarService.searchPagamentos(filtro));
    }

    @PutMapping(value = "/pagamentos/{id}")
    public ResponseEntity atualizarPagamento(@PathVariable String id ,@RequestBody @Valid PagamentosRequestDto pagamento){
        atualizarService.updatePagamento(pagamento, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping(value = "/pagamentos/{id}")
    public ResponseEntity deletarPagamento(@PathVariable String id){
        deletarService.deletePagamento(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
