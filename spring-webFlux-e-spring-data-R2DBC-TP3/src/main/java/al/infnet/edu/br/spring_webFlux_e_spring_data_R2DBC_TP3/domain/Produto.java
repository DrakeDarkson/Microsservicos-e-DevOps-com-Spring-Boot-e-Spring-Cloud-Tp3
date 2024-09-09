import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("produtos")
public class Produto {

    @Id
    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
}
