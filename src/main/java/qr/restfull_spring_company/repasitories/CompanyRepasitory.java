package qr.restfull_spring_company.repasitories;

import org.springframework.data.jpa.repository.JpaRepository;
import qr.restfull_spring_company.entities.Company;

public interface CompanyRepasitory extends JpaRepository<Company,Integer> {
}
