package qr.restfull_spring_company.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import qr.restfull_spring_company.dto.WorkerDto;
import qr.restfull_spring_company.entities.Adress;
import qr.restfull_spring_company.entities.Department;
import qr.restfull_spring_company.entities.Worker;
import qr.restfull_spring_company.payloads.Result;
import qr.restfull_spring_company.repasitories.AdressRepasitory;
import qr.restfull_spring_company.repasitories.DepartmentRepasitory;
import qr.restfull_spring_company.repasitories.WorkerRepasitory;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkerService {
    WorkerRepasitory workerRepasitory;
    DepartmentRepasitory departmentRepasitory;
    AdressRepasitory adressRepasitory;

    public List<Worker> getAllWorkers() {
        return (List<Worker>) workerRepasitory.findAll();
    }

    public Worker getWorkerById(Integer workerId) {
        return workerRepasitory.findById(workerId).orElse(null);
    }

    public Result addWorker(WorkerDto workerDTO) {
        boolean existsByPhoneNumber = workerRepasitory.existsWorkerByPhoneNumber(workerDTO.getPhoneNumber());
        if (existsByPhoneNumber) {
            return new Result("This phone number already exist!", false, HttpStatus.CONFLICT.value());
        }
        Worker Worker = new Worker();
        Worker.setName(workerDTO.getName());
        Worker.setPhoneNumber(workerDTO.getPhoneNumber());

        Optional<Adress> addressId = adressRepasitory.findById(workerDTO.getAddressId());
        if (addressId.isPresent()) {
            Worker.setAddress(addressId.get());

        }

        Optional<Department> departmentId = departmentRepasitory.findById(workerDTO.getDepartmentId());
        if (departmentId.isPresent()) {
            Worker.setDepartment(departmentId.get());
        }

        workerRepasitory.save(Worker);
        return new Result("Worker successfully added!", true, HttpStatus.CREATED.value());
    }

    public Result updateWorker(WorkerDto workerDTO, Integer workerId) {
        boolean existsByPhoneNumber = workerRepasitory.existsWorkerByPhoneNumber(workerDTO.getPhoneNumber());
        if (existsByPhoneNumber) {
            return new Result("This phone number already exist!", false, HttpStatus.CONFLICT.value());
        }
        Optional<Worker> optionalWorker = workerRepasitory.findById(workerId);
        if (optionalWorker.isPresent()) {
            Worker updatingWorker = optionalWorker.get();
            updatingWorker.setName(workerDTO.getName());
            updatingWorker.setPhoneNumber(workerDTO.getPhoneNumber());

            Optional<Adress> addressOptional = adressRepasitory.findById(workerDTO.getAddressId());
            if (addressOptional.isPresent()) {
                updatingWorker.setAddress(addressOptional.get());
            }

            departmentRepasitory.findById(workerDTO.getDepartmentId()).ifPresent(updatingWorker::setDepartment);

            workerRepasitory.save(updatingWorker);
            return new Result("Worker successfully updated!", true, HttpStatus.ACCEPTED.value());
        }
        return new Result("Worker not found!", false, HttpStatus.NOT_FOUND.value());
    }

    public Result deleteWorker(Integer workerId) {
        Optional<Worker> optionalWorker = workerRepasitory.findById(workerId);
        if (optionalWorker.isPresent()) {
            workerRepasitory.deleteById(workerId);
            return new Result("Worker deleted!", true, HttpStatus.ACCEPTED.value());
        }
        return new Result("Worker not found!", false, HttpStatus.NOT_FOUND.value());
    }
}
