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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SECTOR_ID")
    private Sector sector;

    @OneToMany(mappedBy = "rock", fetch = FetchType.EAGER)
    private Set<Wall> walls;
}
