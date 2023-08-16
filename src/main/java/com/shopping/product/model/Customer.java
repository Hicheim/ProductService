package com.shopping.product.model;

import java.util.Objects;

/**
 * In a real world, a customer have a lot of other information
 * The access credentials to the system, loyalty card, contact preferences, subscriptions for feeds ...
 */
public class Customer {

    private Integer id;
    private String firstName;
    private String lastName;
    /**
     * This a simple contact implementation, is real usage we can meet multi contact management to let customers manage more than one address
     * We can also separate delivery address from billing address.
     */
    private Address address;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) &&
                Objects.equals(firstName, customer.firstName) &&
                Objects.equals(lastName, customer.lastName) &&
                Objects.equals(address, customer.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, address);
    }
}