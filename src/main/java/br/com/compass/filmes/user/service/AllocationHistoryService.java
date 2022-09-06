package br.com.compass.filmes.user.service;

import br.com.compass.filmes.user.dto.user.response.apiAllocationHistory.ResponseAllocation;
import br.com.compass.filmes.user.proxy.AllocationHistoryProxy;
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
