package br.com.juliomoraes.repositories.custom;

import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.CrudMethodMetadata;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.QuerydslJpaPredicateExecutor;
import org.springframework.data.querydsl.EntityPathResolver;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public abstract class CustomJpaRepositoryImpl<T, ID extends Serializable>
        extends QuerydslJpaPredicateExecutor<T> implements CustomJpaRepository<T, ID> {

    protected CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager, EntityPathResolver resolver, CrudMethodMetadata metadata) {
        super(entityInformation, entityManager, resolver, metadata);
    }
}
