package br.com.compass.filmes.cliente.client;

import br.com.compass.filmes.cliente.dto.apiAllocationHistory.response.ResponseAllocation;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AllocationHistoryProxy {

    @Autowired
    private AllocationHistory allocationHistory;

    public List<ResponseAllocation> getHistoryByUser(String userId) {
        try {
            return allocationHistory.getHistoryByUser(userId);
        } catch (FeignException.FeignClientException.NotFound exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
