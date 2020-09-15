package training.springinaction_chapter4.tacos;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="order_tab")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date placedAt = new Date();

    @NotBlank(message="Name is mandatory.")
    private String name;

    @NotBlank(message="City is mandatory.")
    private String city;

    @NotBlank(message="Street is mandatory.")
    private String street;

    @NotBlank(message="State is mandatory.")
    private String state;

    @NotBlank(message="Zip is mandatory.")
    private String zip;

    @CreditCardNumber(message="It is not valid card number.")
    private String ccNumber;

    //@Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message = "Value must be in format MM/RR.")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "No valid code CVV.")
    private String ccCVV;

    @ManyToMany
    private List<Taco> tacos = new ArrayList<>();

    public void addTaco(Taco taco) {
        this.tacos.add(taco);
    }

    @PrePersist
    void placedAt() {
        this.placedAt = new Date();
    }
}
