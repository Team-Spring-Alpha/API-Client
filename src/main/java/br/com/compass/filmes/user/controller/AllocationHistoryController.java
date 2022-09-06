package br.com.compass.filmes.user.controller;

import br.com.compass.filmes.user.dto.user.response.apiAllocationHistory.ResponseAllocation;
import br.com.compass.filmes.user.service.AllocationHistoryService;
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
