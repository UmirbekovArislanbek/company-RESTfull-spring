package qr.restfull_spring_company.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import qr.restfull_spring_company.entities.Adress;
import qr.restfull_spring_company.payloads.Result;
import qr.restfull_spring_company.repasitories.AdressRepasitory;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdressService {
    AdressRepasitory adressRepasitory;


    public List<Adress> getAllAddresses() {
        return (List<Adress>) adressRepasitory.findAll();
    }


    public Adress getAddressById(Integer addressId) {
        Optional<Adress> addressOptional = adressRepasitory.findById(addressId);
        return addressOptional.orElse(null);
    }

    public Result addNewAddress(Adress address) {
        boolean existsByStreet = adressRepasitory.existsAdressByStreet(address.getStreet());
        if (existsByStreet) {
            return new Result("This street already exist!", false, HttpStatus.CONFLICT.value());
        } else {
            adressRepasitory.save(address);
            return new Result("Address successfully saved!", true, HttpStatus.CREATED.value());
        }
    }

    public Result updateAddressById(Integer addressId, Adress address) {
        boolean existsByStreet = adressRepasitory.existsAdressByStreet(address.getStreet());
        if (existsByStreet) {
            return new Result("This street already exist!", false, HttpStatus.CONTINUE.value());
        }
        Optional<Adress> addressOptional = adressRepasitory.findById(addressId);
        if (addressOptional.isPresent()) {
            Adress updatingAddress = addressOptional.get();
            updatingAddress.setStreet(address.getStreet());
            updatingAddress.setHomeNumber(address.getHomeNumber());
            adressRepasitory.save(updatingAddress);
            return new Result("Address successfully updated!", true, HttpStatus.ACCEPTED.value());
        }
        return new Result("Address not found!", false, HttpStatus.NOT_FOUND.value());
    }

    public Result deletAddressById(Integer addressId) {
        Optional<Adress> addressOptional = adressRepasitory.findById(addressId);
        if (addressOptional.isPresent()) {
            adressRepasitory.deleteById(addressId);
            return new Result("Address deleted!", true, HttpStatus.ACCEPTED.value());
        }
        return new Result("Address not found!", false, HttpStatus.NOT_FOUND.value());
    }
}
