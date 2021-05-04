package com.app.topo.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity(name="REGIONS")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NonNull
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "AREA_ID")
    private Area area;

    @OneToMany(mappedBy = "region", fetch = FetchType.EAGER)
    private Set<Sector> sectors;
}
