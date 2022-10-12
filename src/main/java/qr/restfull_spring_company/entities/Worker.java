package qr.restfull_spring_company.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @NotNull
    private String name;

    @NotNull
    @Column(unique = true)
    private String phoneNumber;

    @OneToOne
    private Adress address;

    @OneToOne
    private Department department;
}
