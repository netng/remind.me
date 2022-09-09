/**
 * @Author Nandang Sopyan
 * @ApplicationName remind.me app
 * @CreatedAt Sept 2022
 * @Description This is a REST API application as mini project task at alterra training academy program
 */

/**
 * @Reference https://javatodev.com/spring-boot-pagination-sorting-and-filtering/
 */
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