package com.alcampoverde.ms_transactions.infraestructure.in.controller;


import com.alcampoverde.ms_transactions.infraestructure.in.dto.MovementDto;
import com.alcampoverde.ms_transactions.infraestructure.in.handler.MovementHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("v1/movements")
@RequiredArgsConstructor
public class AccountTransactionController {

    private final MovementHandler movementTransaction;


    @GetMapping
    public ResponseEntity<List<MovementDto>> findAllTransaction() {
        return new ResponseEntity<>(movementTransaction.findAll(), OK);
    }

    @PostMapping
    public ResponseEntity<MovementDto> createTransaction(@RequestBody MovementDto movementDto) {
        MovementDto movement = movementTransaction.transaction(movementDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(movement.getMovementId()).toUri();
        return  ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivateMovement(@PathVariable Integer id) {
        movementTransaction.deactivateMovement(id);
        return ResponseEntity.noContent().build();
    }
}

