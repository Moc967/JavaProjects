package br.com.letscode.cookbookapi.controller;

import br.com.letscode.cookbookapi.model.Ingrediente;
import br.com.letscode.cookbookapi.repository.IngredienteRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "ingrediente")
public class IngredienteController {
    private IngredienteRepository repository;

    public IngredienteController(IngredienteRepository repository){
        this.repository = repository;
    }
    @GetMapping
    public List<Ingrediente> getAll(){
        return repository.findAll();
    }
}
