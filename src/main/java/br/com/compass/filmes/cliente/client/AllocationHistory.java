package br.com.compass.filmes.cliente.client;

import br.com.compass.filmes.cliente.dto.apiAllocationHistory.response.ResponseAllocation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "allocationHistory", url = "${custom.allocation-history-url}")
public interface AllocationHistory {

    @GetMapping(value = "/{userId}")
    List<ResponseAllocation> getHistoryByUser(@PathVariable String userId);
}
