package qr.restfull_spring_company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerDto {

    private String name;
    private String phoneNumber;
    private Integer addressId;
    private Integer departmentId;
}
