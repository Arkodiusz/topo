package com.app.topo.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity(name = "MEMBERS")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NonNull
    private String name;

    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    private Set<Route> routes;
}