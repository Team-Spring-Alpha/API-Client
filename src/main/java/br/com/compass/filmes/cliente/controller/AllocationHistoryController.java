package br.com.compass.filmes.cliente.controller;

import br.com.compass.filmes.cliente.dto.apiAllocationHistory.response.ResponseAllocationDTO;
import br.com.compass.filmes.cliente.service.AllocationHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/allocation-history")
public class AllocationHistoryController {

    @Autowired
    private AllocationHistoryService service;


    @GetMapping("/{userId}")
    public ResponseEntity<List<ResponseAllocationDTO>> findByUserId(@PathVariable String userId){
        List<ResponseAllocationDTO> allocationHistory = service.getAllocationHistory(userId);
        return ResponseEntity.ok(allocationHistory);
    }
}
