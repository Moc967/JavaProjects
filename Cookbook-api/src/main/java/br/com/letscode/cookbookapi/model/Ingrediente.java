package br.com.letscode.cookbookapi.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Ingrediente {
    @Id
    @Column(name ="nome", length = 120 )
    @EqualsAndHashCode.Include
    private String nometeste;

    @Column(name ="descricao", length = 120 )
    private String descricao;
}
