package br.com.juliomoraes.model;

import br.com.juliomoraes.model.enums.TipoDespesa;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_movimento")
public class Movimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoDespesa tipoDespesa;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false, updatable = false)
    private LocalDate dataCadastro;

    @Column(nullable = false)
    private LocalDate dataVencimento;

    private LocalDate dataPagamento;

    @Column(nullable = false)
    private boolean pago;

    @Column(nullable = false)
    private boolean recorrente;

    private String observacoes;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @PrePersist
    public void doPersist() {
        this.dataCadastro = LocalDate.now();
    }
}
