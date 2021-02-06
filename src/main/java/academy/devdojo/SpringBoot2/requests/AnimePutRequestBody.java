package academy.devdojo.SpringBoot2.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class AnimePutRequestBody {
    private Long id;
    private String name;
}
