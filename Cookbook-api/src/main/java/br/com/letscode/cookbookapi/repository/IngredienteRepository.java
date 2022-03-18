package br.com.letscode.cookbookapi.repository;

import br.com.letscode.cookbookapi.model.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredienteRepository extends JpaRepository<Ingrediente, String> {
}
