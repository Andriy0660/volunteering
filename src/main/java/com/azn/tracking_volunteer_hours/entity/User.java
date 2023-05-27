package com.azn.tracking_volunteer_hours.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "users")
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private Long id;

    @Column(name = "email", length = 319,nullable = false,unique = true)
    private String email;

    @Column(name = "password", length = 70)
    @JsonIgnore
    private String password;

    @Column(name = "first_name", length = 20,nullable = false)
    private String firstname;

    @Column(name = "last_name", length = 20,nullable = false)
    private String lastname;



}
