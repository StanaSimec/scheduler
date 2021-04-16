package cz.simecek.scheduler.model;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
public class Employee {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    @Column(unique = true)
    private String code;

    private String address;

    private String positionName;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private Set<Event> events;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "office_id")
    private Office office;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }
}
