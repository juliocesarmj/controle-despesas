package br.com.juliomoraes.model;

import br.com.juliomoraes.model.enums.TipoPerfil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_usuario")
public class Usuario implements UserDetails {

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

    @ManyToMany
    @JoinTable(name = "tb_usuario_perfil",
            joinColumns = @JoinColumn(name = "usuario_id"),
    inverseJoinColumns = @JoinColumn(name = "perfil_id"))
    private Set<Perfil> roles = new HashSet<>();

    @JsonIgnore
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

    public void addPerfil(Perfil perfil) {
        this.roles.add(perfil);
    }

    public boolean isAdmin() {
        return this.roles.stream().anyMatch(role -> TipoPerfil.ROLE_ADMIN.name().equals(role.getAuthority()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return ativo;
    }
}
