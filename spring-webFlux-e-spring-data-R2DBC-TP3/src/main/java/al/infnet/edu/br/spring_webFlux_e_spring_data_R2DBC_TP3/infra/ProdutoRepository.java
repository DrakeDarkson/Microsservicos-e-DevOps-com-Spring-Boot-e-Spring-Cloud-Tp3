import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ProdutoRepository extends ReactiveCrudRepository<Produto, Long> {
}
