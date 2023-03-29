package com.example.employeeservice.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name="employees")
public class Employee {

    @SequenceGenerator(name = "employee_sequence",
            sequenceName = "employee_sequence",
            allocationSize = 1,initialValue = 34)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator ="employee_sequence"
    )
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String region;
    private String postalZip;
    private String country;
}
