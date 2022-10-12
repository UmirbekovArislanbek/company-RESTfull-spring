package qr.restfull_spring_company.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import qr.restfull_spring_company.dto.CompanyDto;
import qr.restfull_spring_company.entities.Adress;
import qr.restfull_spring_company.entities.Company;
import qr.restfull_spring_company.payloads.Result;
import qr.restfull_spring_company.repasitories.AdressRepasitory;
import qr.restfull_spring_company.repasitories.CompanyRepasitory;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {
    CompanyRepasitory companyRepasitory;

    AdressRepasitory addressRepository;

    public List<Company> getAllCompanies() {
        return (List<Company>) companyRepasitory.findAll();
    }

    public Company getCompanyById(Integer companyId) {
        return companyRepasitory.findById(companyId).orElse(null);
    }

    public Result addNewCompany(CompanyDto companyDTO) {
        Company newCompany = new Company();
        newCompany.setCompanyName(companyDTO.getCompanyName());
        newCompany.setDirectorName(companyDTO.getDirectorName());
        Optional<Adress> addressOptional = addressRepository.findById(companyDTO.getAddressId());
        if (addressOptional.isPresent()) {
            newCompany.setAddress(addressOptional.get());
            companyRepasitory.save(newCompany);
            return new Result("Company successfully saved!", true, HttpStatus.CREATED.value());
        }
        return new Result("Company not found!", false, HttpStatus.NOT_FOUND.value());
    }

    public Result updateCompanyById(Integer companyId, CompanyDto companyDTO) {
        Optional<Company> companyOptional = companyRepasitory.findById(companyId);
        if (companyOptional.isPresent()) {
            Company updatingCompany = companyOptional.get();
            updatingCompany.setCompanyName(companyDTO.getCompanyName());
            updatingCompany.setDirectorName(companyDTO.getDirectorName());
            Optional<Adress> addressOptional = addressRepository.findById(companyDTO.getAddressId());
            if (addressOptional.isPresent()) {
                updatingCompany.setAddress(addressOptional.get());
                companyRepasitory.save(updatingCompany);
                return new Result("Company successfully updated!", true, HttpStatus.ACCEPTED.value());
            }
            return new Result("Address not found!", false, HttpStatus.NOT_FOUND.value());
        }
        return new Result("Address not found!", false, HttpStatus.NOT_FOUND.value());
    }

    public Result deleteCompanyById(Integer companyId) {
        Optional<Company> companyOptional = companyRepasitory.findById(companyId);
        if (companyOptional.isPresent()) {
            companyRepasitory.deleteById(companyId);
            return new Result("Company deleted!", true, HttpStatus.ACCEPTED.value());
        }
        return new Result("Company not found!", false, HttpStatus.NOT_FOUND.value());
    }
}
