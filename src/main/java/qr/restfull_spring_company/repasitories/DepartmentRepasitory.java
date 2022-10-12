package qr.restfull_spring_company.repasitories;

import org.springframework.data.jpa.repository.JpaRepository;
import qr.restfull_spring_company.entities.Department;

import javax.validation.constraints.NotNull;

public interface DepartmentRepasitory extends JpaRepository<Department,Integer> {
    Boolean existsDepartmentByName(@NotNull String name);
}
