import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public Flux<Produto> listarTodos() {
        return produtoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Mono<Produto> buscarPorId(@PathVariable Long id) {
        return produtoService.buscarPorId(id);
    }

    @PostMapping
    public Mono<Produto> criarProduto(@RequestBody Produto produto) {
        return produtoService.salvar(produto);
    }

    @PutMapping("/{id}")
    public Mono<Produto> atualizarProduto(@PathVariable Long id, @RequestBody Produto produto) {
        return produtoService.buscarPorId(id)
                .flatMap(p -> {
                    p.setNome(produto.getNome());
                    p.setDescricao(produto.getDescricao());
                    p.setPreco(produto.getPreco());
                    return produtoService.salvar(p);
                });
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deletarProduto(@PathVariable Long id) {
        return produtoService.deletarPorId(id);
    }
}
