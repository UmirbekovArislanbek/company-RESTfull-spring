package qr.restfull_spring_company.repasitories;

import org.springframework.data.jpa.repository.JpaRepository;
import qr.restfull_spring_company.entities.Adress;

import javax.validation.constraints.NotNull;

public interface AdressRepasitory extends JpaRepository<Adress,Integer> {
    Boolean existsAdressByStreet(@NotNull String street);
}
