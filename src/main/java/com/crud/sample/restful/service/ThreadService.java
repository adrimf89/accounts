package com.crud.sample.restful.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class ThreadService {

    /**
     * Method that return the current date after waiting n ms
     * This method can be executed asynchronously
     * @param wait
     * @return
     * @throws InterruptedException
     */
    @Async
    public CompletableFuture<Date> getDate(long wait) throws InterruptedException {
        Thread.sleep(wait);
        Date date = new Date();

        return CompletableFuture.completedFuture(date);
    }
}
