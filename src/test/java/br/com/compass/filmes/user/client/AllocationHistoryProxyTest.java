package br.com.compass.filmes.user.client;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


@SpringBootTest(classes = AllocationHistoryProxy.class)
class AllocationHistoryProxyTest {

    @Autowired
    private AllocationHistoryProxy allocationHistoryProxy;

    @MockBean
    private AllocationHistory allocationHistory;


    @Test
    @DisplayName("should get history allocation from a user")
    void getHistoryByUser() {
        String userId = "test user";

        allocationHistoryProxy.getHistoryByUser(userId);

        Mockito.verify(allocationHistory).getHistoryByUser(userId);
    }
}