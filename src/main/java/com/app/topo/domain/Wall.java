package com.app.topo.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity(name="WALLS")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Wall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NonNull
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ROCK_ID")
    private Rock rock;

    private String exposure;

    @OneToMany(mappedBy = "wall", fetch = FetchType.EAGER)
    private Set<Route> routes;
}
