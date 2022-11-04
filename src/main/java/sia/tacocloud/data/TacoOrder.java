package sia.tacocloud.data;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Defines how customers specify the tacos that they want to order, along with payment and delivery information
 */
@Data
@Entity
public class TacoOrder  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // DB will generate the ID value automatically
    private Long id;

    private Date placedAt;

    @NotBlank(message="Delivery name is required")
    private String deliveryName;

    @NotBlank(message="Street is required")
    private String deliveryStreet;

    @NotBlank(message="City is required")
    private String deliveryCity;

    @NotBlank(message="State is required")
    private String deliveryState;

    @NotBlank(message="Zip code is required")
    private String deliveryZip;

    @CreditCardNumber(message="Not a valid credit card number")
    private String ccNumber;

    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$", message="Must be formatted MM/YY")
    private String ccExpiration;

    @Digits(integer=3, fraction=0, message="Invalid CVV")
    private String ccCVV;

    @OneToMany(cascade = CascadeType.ALL) // Indicating that the tacos are all specific to this one order + CascadeType.ALL ->
    // if the order is deleted, its related tacos will also be deleted
    private List<Taco> tacos = new ArrayList<>();

    @ManyToOne // To achieve the desired connection between an TacoOrder entity and a User entity:
    // an order belongs to a single user and, conversely, a user may have many orders
    private User user;

    public void addTaco(Taco taco) {
        this.tacos.add(taco);
    }
}
