package br.com.juliomoraes.repositories.specifications;

import br.com.juliomoraes.model.Movimento;
import br.com.juliomoraes.model.Usuario;
import br.com.juliomoraes.model.enums.TipoDespesa;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class MovimentoSpecifications {

    private MovimentoSpecifications() {
    }

    public static Specification<Movimento> byTipoDespesa(TipoDespesa tipoDespesa) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("tipoDespesa"), tipoDespesa);
    }

    public static Specification<Movimento> byPago(Boolean pago) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("pago"), pago);
    }

    public static Specification<Movimento> byDataBetween(LocalDate dataInicio, LocalDate dataFim) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("dataCadastro"), dataInicio, dataFim);
    }

    public static Specification<Movimento> byUsuarioAutenticado(Usuario usuario) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("usuario"), usuario);
    }
}
