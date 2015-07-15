package org.kyll.myserver.business.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: Kyll
 * Date: 2015-07-15 8:42
 */
@Entity
@Table(name = "MS_LI_PROJECT")
public class Project implements Serializable {
    private Long id;
    private Customer customer;
    private String name;
    private String linkMan;
    private String phone;
    private String fund;

    public Project() {
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "ID_")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID_")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Column(name = "NAME_")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "LINK_MAN_")
    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    @Column(name = "PHONE_")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "FUND_")
    public String getFund() {
        return fund;
    }

    public void setFund(String fund) {
        this.fund = fund;
    }
}
