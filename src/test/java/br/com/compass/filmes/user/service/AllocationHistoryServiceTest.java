package br.com.compass.filmes.user.service;

import br.com.compass.filmes.user.client.AllocationHistoryProxy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = AllocationHistoryService.class)
class AllocationHistoryServiceTest {

    @Autowired
    private AllocationHistoryService allocationHistoryService;

    @MockBean
    private AllocationHistoryProxy allocationHistoryProxy;

    @Test
    @DisplayName("should get history allocation from a user")
    void getAllocationHistory() {
        String userId = "userTest";

        allocationHistoryService.getAllocationHistory(userId);

        Mockito.verify(allocationHistoryProxy).getHistoryByUser(userId);
    }
}