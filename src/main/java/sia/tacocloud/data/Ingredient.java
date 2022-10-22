package sia.tacocloud.data;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Ingredients are the essential building blocks of a taco
 */
@Data
@Entity
@AllArgsConstructor
// JPA requires that entities have a no-arguments constructor (implicity added a required arguments constructor is removed) -->
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
public class Ingredient {

    @Id
    private final String id;
    private final String name;
    private final Type type;

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
