package com.training.alterra.miniproject.remindmeapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginatedBaseResponseDTO<S, M, O> implements Serializable {
    private S status;
    private M message;
    private O data;
    private Long numberOfItems;
    private int numberOfPages;
    private int currentPage;

}
