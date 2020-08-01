package io.acme.longlife.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "green_leafs")
@Getter
@Setter
public class GreenLeaf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "big_tree_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private BigTree bigTree;
}
