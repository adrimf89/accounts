package com.crud.sample.restful.api;

import com.crud.sample.restful.service.ThreadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/v1/thread")
@Slf4j
@RequiredArgsConstructor
public class ThreadAPI {
    private final ThreadService threadService;

    @GetMapping
    public ResponseEntity thread() throws ExecutionException, InterruptedException {
        CompletableFuture<Date> result1 = threadService.getDate(10000);
        CompletableFuture<Date> result2 = threadService.getDate(10000);
        CompletableFuture<Date> result3 = threadService.getDate(10000);

        CompletableFuture.allOf(result1,result2,result3).join();

        Date date4 = threadService.getDate(10000).get();

        return ResponseEntity.ok(Arrays.asList(result1.get(),
                result2.get(),
                result3.get(),
                date4));
    }
}
