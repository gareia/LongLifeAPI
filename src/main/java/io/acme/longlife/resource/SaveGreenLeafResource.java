package io.acme.longlife.resource;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SaveGreenLeafResource {

    @NotNull
    @NotBlank
    @Size(max = 80)
    @Column(unique = true)
    private String title;

    @NotNull
    @NotBlank
    @Size(max = 300)
    private String scenario;

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String tip;
}
