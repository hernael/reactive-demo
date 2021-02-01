package com.example;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.quarkus.panache.common.Page;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Repository implements PanacheRepository<Entity> {

    public Uni<PageResponse<Entity>> getPage() {
        var query = this.findAll();
        var list = query.page(Page.of(1, 10)).list();
        var total = query.count();
        return Uni.combine().all().unis(list, total).asTuple().onItem().transform((tuple) ->
            PageResponse.<Entity>newBuilder()
                .setElements(tuple.getItem1())
                .setTotalCount(tuple.getItem2())
                .build()
        );
    }
    public Uni<Entity> getFirst() {
        return this.findAll().firstResult();
    }
}
