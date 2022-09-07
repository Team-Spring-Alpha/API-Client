package br.com.compass.filmes.user.client;

import br.com.compass.filmes.user.dto.user.response.apiAllocationHistory.ResponseAllocationDTO;
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

    public List<ResponseAllocationDTO> getHistoryByUser(String userId) {
        try {
            return allocationHistory.getHistoryByUser(userId);
        } catch (FeignException.FeignServerException.NotFound exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
