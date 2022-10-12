package qr.restfull_spring_company.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import qr.restfull_spring_company.entities.Adress;
import qr.restfull_spring_company.payloads.Result;
import qr.restfull_spring_company.services.AdressService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/address")
public class AdressController {
    AdressService adressService;
    @GetMapping
    private ResponseEntity<List<Adress>> getAddressesList() {
        return ResponseEntity.ok(adressService.getAllAddresses());
    }

    @GetMapping("/{addressId}")
    private ResponseEntity<Adress> getAddressById(@PathVariable Integer addressId) {
        return ResponseEntity.ok(adressService.getAddressById(addressId));
    }
    @PostMapping
    private ResponseEntity<Result> addAddress(@Valid @RequestBody Adress address) {
        return ResponseEntity.status(adressService.addNewAddress(address).getStatus()).body(adressService.addNewAddress(address));
    }
    @PutMapping("/{addressId}")
    private ResponseEntity<Result> updateAddress(@Valid @PathVariable Integer addressId,
                                                 @RequestBody Adress address) {
        return ResponseEntity
                .status(adressService.updateAddressById(addressId, address).getStatus())
                .body(adressService.updateAddressById(addressId, address));
    }
    @DeleteMapping("/{addressId}")
    private ResponseEntity<Result> deleteAddress(@Valid @PathVariable Integer addressId) {
        return ResponseEntity
                .status(adressService.deletAddressById(addressId).getStatus())
                .body(adressService.deletAddressById(addressId));
    }

}
