package com.app.topo.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity(name="ROCKS")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Rock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NonNull
    private String name;

    private Double latitude;

    private Double longitude;

    private Double elevation;

    @OneToMany(mappedBy = "rock", fetch = FetchType.EAGER)
    private Set<Wall> walls;
}
