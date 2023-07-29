package br.com.juliomoraes.api.dtos;

import br.com.juliomoraes.model.enums.TipoDespesa;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class MovimentoCriacaoDto {

    private String titulo;
    private TipoDespesa tipoDespesa;
    private BigDecimal valor;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private boolean pago;
    private boolean recorrente;
    private String observacoes;
}
