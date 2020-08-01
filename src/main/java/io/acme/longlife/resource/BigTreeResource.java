package io.acme.longlife.resource;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BigTreeResource {

    private Long id;
    private String userName;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private Date bornAt;
}
