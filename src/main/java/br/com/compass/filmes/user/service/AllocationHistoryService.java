package br.com.compass.filmes.user.service;

import br.com.compass.filmes.user.client.AllocationHistoryProxy;
import br.com.compass.filmes.user.dto.allocation.history.response.ResponseAllocationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllocationHistoryService {

    @Autowired
    private AllocationHistoryProxy proxy;

    public List<ResponseAllocationDTO> getAllocationHistory(String userId) {
        return proxy.getHistoryByUser(userId);
    }
}
