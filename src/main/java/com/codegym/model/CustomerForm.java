package com.codegym.model;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CustomerForm {
    private Long id;

    @NotBlank(message = "Không được để trống")
    @Size(min = 1, message = "Độ dài  > 1")
    private String firstName;

    @NotBlank
    @Size(min = 1)
    private String lastName;
    private Province province;
    private MultipartFile avatar;

    public CustomerForm() {
    }

    public CustomerForm(String firstName, String lastName, Province province, MultipartFile avatar) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.province = province;
        this.avatar = avatar;
    }

    public CustomerForm(Long id, String firstName, String lastName, Province province, MultipartFile avatar) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.province = province;
        this.avatar = avatar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public MultipartFile getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }
}
