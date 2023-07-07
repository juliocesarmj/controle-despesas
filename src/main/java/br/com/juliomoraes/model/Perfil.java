package br.com.juliomoraes.model;

import br.com.juliomoraes.model.enums.TipoPerfil;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_perfil")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoPerfil tipo;

    @OneToMany(mappedBy = "perfil")
    private List<Usuario> usuarios = new ArrayList<>();
}
