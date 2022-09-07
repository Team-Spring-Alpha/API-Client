package br.com.compass.filmes.cliente.service;

import br.com.compass.filmes.cliente.dto.user.response.apiAllocationHistory.ResponseAllocation;
import br.com.compass.filmes.cliente.proxy.AllocationHistoryProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllocationHistoryService {

    @Autowired
    private AllocationHistoryProxy proxy;

    public List<ResponseAllocation> getAllocationHistory(String userId) {
        return proxy.getHistoryByUser(userId);
    }
}
