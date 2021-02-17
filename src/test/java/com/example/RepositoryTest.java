package com.example;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.mutiny.helpers.test.UniAssertSubscriber;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class RepositoryTest {

    @Inject
    Repository repository;

    @Test
    void getFirstBlocking() {
        var response = repository.getFirst().await().indefinitely();

        assertNotNull(response);
    }

    @Test
    void getFirst() {
        var response = repository.getFirst();

        UniAssertSubscriber<Entity> subscriber = response
                .subscribe().withSubscriber(UniAssertSubscriber.create());

        subscriber.assertCompleted().assertTerminated();
    }

    @Test
    void getFirstBlockingWithTransaction() {
        var response = Panache.withTransaction(() -> repository.getFirst()).await().indefinitely();

        assertNotNull(response);
    }

    @Test
    void getFirstWithTransaction() {
        var response = Panache.withTransaction(() -> repository.getFirst());

        UniAssertSubscriber<Entity> subscriber = response
                .subscribe().withSubscriber(UniAssertSubscriber.create());

        subscriber.assertCompleted().assertTerminated();
    }
}