package io.datastreamer.adapters.database;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order", schema = "public")
public class OrderEntity {

  @Id
  @Column(name = "id")
  private String id;

  @Column(name = "creationDate")
  private OffsetDateTime creationdate;

  @Column(name = "customerName")
  private String customername;

  @Column(name = "orderTotal")
  private BigDecimal ordertotal;

  @Column(name = "membershipLevel")
  private String membershiplevel;

  public OffsetDateTime getCreationdate() {
    return creationdate;
  }

  public void setCreationdate(OffsetDateTime creationdate) {
    this.creationdate = creationdate;
  }

  public String getCustomername() {
    return customername;
  }

  public void setCustomername(String customername) {
    this.customername = customername;
  }

  public BigDecimal getOrdertotal() {
    return ordertotal;
  }

  public void setOrdertotal(BigDecimal orderTotal) {
    this.ordertotal = orderTotal;
  }

  public String getMembershiplevel() {
    return membershiplevel;
  }

  public void setMembershiplevel(String membershipLevel) {
    this.membershiplevel = membershipLevel;
  }

  public OrderEntity() {
  }

  public void setId(String id) {
    this.id = id;
  }

  @Id
  public String getId() {
    return id;
  }
}
