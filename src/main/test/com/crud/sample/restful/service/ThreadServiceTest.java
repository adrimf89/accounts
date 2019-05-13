package com.crud.sample.restful.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static junit.framework.TestCase.assertTrue;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ThreadServiceTest {

    private static final long WAIT_TIME = 4000;

    @InjectMocks
    private ThreadService threadService;

    @Test
    public void testThread() throws InterruptedException, ExecutionException {
        Date currentDate = new Date();

        CompletableFuture<Date> result = threadService.getDate(WAIT_TIME);
        Date dateResult = result.get();

        long difference = Math.abs(currentDate.getTime() - dateResult.getTime());

        System.out.println(difference);

        assertTrue(difference >= WAIT_TIME);
    }
}
