package ru.tacocloud.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.CreditCardNumber;
import lombok.Data;

@Data
@Entity
public class TacoOrder implements Serializable {

    // persisting object
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Date placedAt = new Date();

    // 新建taco order 物件須符合條件
    @NotBlank(message = "Delivery name is required")
    private String deliveryName;

    @NotBlank(message = "Street is required")
    private String deliveryStreet;

    @NotBlank(message = "City is required")
    private String deliveryCity;

    @NotBlank(message = "State is required")
    private String deliveryState;

    @NotBlank(message = "Zip code is required")
    private String deliveryZip;

    // the Luhn algorithm check:盧恩算法，一般用於驗證身份辨識碼
    @CreditCardNumber(message = "Not a valid credit card number")
    private String ccNumber;

    // regular expression:常規表示式，一個字串
    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$", message = "Must be formatted MM/YY")
    private String ccExpiration;

    // 確認輸入值為3位數
    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;

    @OneToMany(cascade= CascadeType.ALL) // Tacos只對應此Order，若刪除此Order將一併刪除所對應的Tacos
    private List<Taco> tacos = new ArrayList<>();

    public void addTaco(Taco taco) {
        this.tacos.add(taco);
    }
}