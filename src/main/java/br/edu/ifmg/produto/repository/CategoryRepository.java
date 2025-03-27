package br.edu.ifmg.produto.repository;

import br.edu.ifmg.produto.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository //me diz que e' da camada do repositorio, age como componente
//@Component //spring boot gerencia a dependencia
public interface CategoryRepository extends JpaRepository<Category, Long> {
    //category classe e long minha chave primaria


}
