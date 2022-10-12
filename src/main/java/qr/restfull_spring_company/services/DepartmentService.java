package qr.restfull_spring_company.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import qr.restfull_spring_company.dto.DepartmentDto;
import qr.restfull_spring_company.entities.Company;
import qr.restfull_spring_company.entities.Department;
import qr.restfull_spring_company.payloads.Result;
import qr.restfull_spring_company.repasitories.CompanyRepasitory;
import qr.restfull_spring_company.repasitories.DepartmentRepasitory;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    DepartmentRepasitory departmentRepository;
    CompanyRepasitory companyRepository;

    public List<Department> getDepartmentList() {
        return (List<Department>) departmentRepository.findAll();
    }

    public Department getDepartmentById(Integer departmentId) {
        return departmentRepository.findById(departmentId).orElse(null);
    }

    public Result addNewDepartment(DepartmentDto departmentDTO) {
        boolean existsDepartmentByName = departmentRepository.existsDepartmentByName(departmentDTO.getName());
        if (existsDepartmentByName) {
            return new Result("Department already exist!", false, HttpStatus.CONFLICT.value());
        }
        Department newDepartment = new Department();
        newDepartment.setName(departmentDTO.getName());
        Optional<Company> optionalCId = companyRepository.findById(departmentDTO.getCompanyId());
        if (optionalCId.isPresent()) {
            newDepartment.setCompany(optionalCId.get());
            departmentRepository.save(newDepartment);
            return new Result("Department successfully saved!", true, HttpStatus.CREATED.value());
        } else {
            return new Result("Company not found!", false, HttpStatus.NOT_FOUND.value());
        }
    }

    public Result updateDepartmentById(DepartmentDto departmentDTO, Integer departmentId) {
        boolean existsDepartmentByName = departmentRepository.existsDepartmentByName(departmentDTO.getName());
        if (existsDepartmentByName) {
            return new Result("This department already exist!", false, HttpStatus.CONFLICT.value());
        }
        Optional<Department> departmentOptional = departmentRepository.findById(departmentId);
        if (departmentOptional.isPresent()) {
            Department updatingDepartment = departmentOptional.get();
            updatingDepartment.setName(departmentDTO.getName());
            Optional<Company> companyOptional = companyRepository.findById(departmentDTO.getCompanyId());
            if (companyOptional.isPresent()) {
                updatingDepartment.setCompany(companyOptional.get());
                departmentRepository.save(updatingDepartment);
                return new Result("Department successfully updated!", true, HttpStatus.ACCEPTED.value());
            }
            return new Result("Company not found!", false, HttpStatus.NOT_FOUND.value());
        }
        return new Result("Department not found!", false, HttpStatus.NOT_FOUND.value());
    }

    public Result deleteDepartmentById(Integer departmentId) {
        Optional<Department> departmentOptional = departmentRepository.findById(departmentId);
        if (departmentOptional.isPresent()) {
            departmentRepository.deleteById(departmentId);
            return new Result("Department deleted!", true, HttpStatus.ACCEPTED.value());
        }
        return new Result("Department not found!", false, HttpStatus.NOT_FOUND.value());
    }
}
