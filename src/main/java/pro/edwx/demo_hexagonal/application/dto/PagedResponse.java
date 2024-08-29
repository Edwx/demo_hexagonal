package pro.edwx.demo_hexagonal.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PagedResponse<T> {
    private List<T> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;

}
