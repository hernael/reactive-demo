package com.example;

import lombok.*;

import java.util.List;

@Getter
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(
        builderMethodName = "newBuilder",
        setterPrefix = "set",
        builderClassName = "Builder"
)
public class PageResponse<T> {
    private final long totalCount;
    private final List<T> elements;

    public PageResponse.Builder<T> toBuilder() {
        return PageResponse.<T>newBuilder()
                .setTotalCount(totalCount)
                .setElements(elements);
    }
}
