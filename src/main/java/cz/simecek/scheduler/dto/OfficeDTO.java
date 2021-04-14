package cz.simecek.scheduler.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class OfficeDTO {

    private Long id;

    @NotBlank(message = "City is mandatory")
    private String city;

    @NotBlank(message = "Address is mandatory")
    private String address;

    @NotNull(message = "Capacity is mandatory")
    private Integer capacity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
