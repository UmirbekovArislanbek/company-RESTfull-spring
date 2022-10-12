package qr.restfull_spring_company.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import qr.restfull_spring_company.dto.WorkerDto;
import qr.restfull_spring_company.entities.Worker;
import qr.restfull_spring_company.payloads.Result;
import qr.restfull_spring_company.services.WorkerService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/worker")
@RequiredArgsConstructor
public class WorkerController {
    WorkerService workerService;
    @GetMapping
    private ResponseEntity<List<Worker>> getAllWorkers() {
        return ResponseEntity.ok(workerService.getAllWorkers());
    }

    @GetMapping("/{workerId}")
    private ResponseEntity<Worker> getWorkerById(@PathVariable Integer workerId) {
        return ResponseEntity.ok(workerService.getWorkerById(workerId));
    }

    @PostMapping
    private ResponseEntity<Result> addWorker(@Valid @RequestBody WorkerDto workerDTO) {
        Result addWorker = workerService.addWorker(workerDTO);
        return ResponseEntity.status(addWorker.getStatus()).body(addWorker);
    }

    @PutMapping("/{workerId}")
    private ResponseEntity<Result> updateWorker(@Valid @RequestBody WorkerDto workerDTO,
                                                @PathVariable Integer workerId) {
        Result updateWorker = workerService.updateWorker(workerDTO, workerId);
        return ResponseEntity.status(updateWorker.getStatus()).body(updateWorker);
    }

    @DeleteMapping("/{workerId}")
    private ResponseEntity<Result> deleteWorker(@PathVariable Integer workerId) {
        Result deleteWorker = workerService.deleteWorker(workerId);
        return ResponseEntity.status(deleteWorker.getStatus()).body(deleteWorker);
    }
}
