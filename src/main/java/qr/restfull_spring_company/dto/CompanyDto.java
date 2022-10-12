package qr.restfull_spring_company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {
    private String companyName;
    private String directorName;
    private Integer addressId;
}
