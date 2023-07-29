package br.com.juliomoraes.services.utils;

import br.com.juliomoraes.api.dtos.MovimentoCriacaoDto;
import br.com.juliomoraes.model.Movimento;

public abstract class MovimentoFactory {

    private MovimentoFactory() {
    }

    public static Movimento.MovimentoBuilder toEntity(MovimentoCriacaoDto dto) {
        return Movimento.builder()
                .titulo(dto.getTitulo())
                .tipoDespesa(dto.getTipoDespesa())
                .valor(dto.getValor())
                .dataVencimento(dto.getDataVencimento())
                .dataPagamento(dto.getDataPagamento())
                .pago(dto.isPago())
                .recorrente(dto.isRecorrente())
                .observacoes(dto.getObservacoes());
    }
}
