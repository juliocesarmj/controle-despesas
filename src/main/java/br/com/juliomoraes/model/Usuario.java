package br.com.juliomoraes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true, updatable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(updatable = false, nullable = false)
    private LocalDateTime dataCadastro;

    @Column(insertable = false)
    private LocalDateTime dataAtualizacao;

    private boolean ativo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "perfil_id", nullable = false)
    private Perfil perfil;
    @OneToMany(mappedBy = "usuario")
    private List<Movimento> movimentos = new ArrayList<>();

    @PrePersist
    public void doPersist() {
        this.dataCadastro = LocalDateTime.now();
        this.ativo = true;
    }

    @PreUpdate
    public void doUpdate() {
        this.dataAtualizacao = LocalDateTime.now();
    }
}
