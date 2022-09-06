package br.com.compass.filmes.user.client;

import br.com.compass.filmes.user.dto.user.response.apiAllocationHistory.ResponseAllocationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "allocationHistory", url = "http://localhost:8082/api/allocation-history")
public interface AllocationHistory {

    @GetMapping(value = "/history/{userId}")
    List<ResponseAllocationDTO> getHistoryByUser(@PathVariable String userId);
}
