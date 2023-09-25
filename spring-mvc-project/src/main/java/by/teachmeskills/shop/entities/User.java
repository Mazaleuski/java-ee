package by.teachmeskills.shop.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public class User extends BaseEntity {
    private String name;
    private String surname;
    private LocalDate birthday;
    private String email;
    private String password;
    private int balance;
    private String address;
    private String phoneNumber;
}