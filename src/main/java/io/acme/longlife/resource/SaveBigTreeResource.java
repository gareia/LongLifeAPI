package io.acme.longlife.resource;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
public class SaveBigTreeResource {
    @NotNull
    @NotBlank
    @Size(max = 20)
    @NaturalId
    private String userName;

    @NotNull
    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

    @NotNull
    @NotBlank
    @Size(max = 40)
    private String firstName;

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String lastName;

    @Size(max = 20)
    private String gender;

    @NotNull
    @NotBlank
    @Temporal(TemporalType.DATE)
    private Date bornAt;
}
