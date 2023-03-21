package com.bookstore.yes24.pageResponse;

import com.bookstore.yes24.book.dto.BookResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class MultiResponseDto<T> {

    private List<T> data;

    private PageInfo pageInfo;

    public MultiResponseDto(List<T> data, Page page) {

        this.data = data;

        this.pageInfo = new PageInfo(page.getNumber() + 1,
                page.getSize(), page.getTotalElements(), page.getTotalPages());
    }
}
