package br.com.compass.filmes.user.proxy;

import br.com.compass.filmes.user.dto.user.response.apiAllocationHistory.ResponseAllocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllocationHistoryProxy {

    @Autowired
    private AllocationHistory allocationHistory;

    public List<ResponseAllocation> getHistoryByUser(String userId) {
        return allocationHistory.getHistoryByUser(userId);
    }
}
