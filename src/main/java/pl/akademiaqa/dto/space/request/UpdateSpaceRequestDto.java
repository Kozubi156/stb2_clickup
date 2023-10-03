package pl.akademiaqa.dto.space.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateSpaceRequestDto {
    private String name;
}
