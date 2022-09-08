package br.com.compass.filmes.user.controller;

import br.com.compass.filmes.user.dto.allocation.history.response.ResponseAllocationDTO;
import br.com.compass.filmes.user.service.AllocationHistoryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/allocation-history")
public class AllocationHistoryController {

    @Autowired
    private AllocationHistoryService service;

    @ApiOperation(value = "return the allocation history from user")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 200, message = "Ok")
    })
    @GetMapping("/{userId}")
    public ResponseEntity<List<ResponseAllocationDTO>> findByUserId(@PathVariable String userId){
        List<ResponseAllocationDTO> allocationHistory = service.getAllocationHistory(userId);
        return ResponseEntity.ok(allocationHistory);
    }
}
