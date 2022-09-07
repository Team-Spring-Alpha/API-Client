package br.com.compass.filmes.user.client;

import br.com.compass.filmes.user.dto.allocation.history.response.ResponseAllocationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "allocationHistory", url = "${custom.allocation-history-url}")
public interface AllocationHistory {

    @GetMapping(value = "/{userId}")
    List<ResponseAllocationDTO> getHistoryByUser(@PathVariable String userId);
}
