package pl.sdacademy.autokomis.model;

import pl.sdacademy.autokomis.dto.OperationDto;

import javax.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private String firstName;
    @Column
    private String address;
    @Column
    private String nip;
    @Column (unique = true)
    private String pesel;

    public Customer() {
    }

    public Customer(OperationDto operationDto) {
        this.id = operationDto.getCustomerId();
        this.name = operationDto.getCustomerName();
        this.firstName = operationDto.getCustomerFirstName();
        this.address = operationDto.getCustomerAddress();
        this.nip = operationDto.getCustomerNip();
        this.pesel = operationDto.getCustomerPesel();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }
}
