package hiro.kitchenpos.menugroup.presentation.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMenuGroupRequest {

    @NotBlank
    private String name;

}
