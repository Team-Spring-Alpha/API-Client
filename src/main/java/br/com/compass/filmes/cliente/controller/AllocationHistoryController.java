package br.com.compass.filmes.cliente.controller;

import br.com.compass.filmes.cliente.dto.user.response.apiAllocationHistory.ResponseAllocation;
import br.com.compass.filmes.cliente.service.AllocationHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/allocation-history/history")
public class AllocationHistoryController {

    @Autowired
    private AllocationHistoryService service;


    @GetMapping("/{userId}")
    public ResponseEntity<List<ResponseAllocation>> findByUserId(@PathVariable String userId){
        List<ResponseAllocation> allocationHistory = service.getAllocationHistory(userId);
        return ResponseEntity.ok(allocationHistory);
    }
}
