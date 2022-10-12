package qr.restfull_spring_company.repasitories;

import org.springframework.data.jpa.repository.JpaRepository;
import qr.restfull_spring_company.entities.Worker;

import javax.validation.constraints.NotNull;

public interface WorkerRepasitory extends JpaRepository<Worker,Integer> {
    Boolean existsWorkerByPhoneNumber(@NotNull String phoneNumber);
}
