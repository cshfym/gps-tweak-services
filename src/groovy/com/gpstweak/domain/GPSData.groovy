package com.gpstweak.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.SequenceGenerator
import javax.persistence.Table
import javax.persistence.Temporal
import javax.persistence.TemporalType

@Entity
@Table(name = "gps_data")
@SequenceGenerator(name="pk_sequence", sequenceName="seq_gps_data", allocationSize = 1)
class GPSData extends Persistable {

  public GPSData() { }

  @Id
  @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="pk_sequence")
  @Column(name = "id")
  Long id

  @Column(name = "user_id")
  String userId

  @Column(name = "payload_type")
  String payloadType

  @Column(name = "payload")
  String payload

  @Temporal(TemporalType.DATE)
  @Column(name = "create_date")
  Date createDate

  @Temporal(TemporalType.DATE)
  @Column(name = "update_date")
  Date updateDate

  Long getId() {
    return id
  }

  void setId(Long id) {
    this.id = id
  }

  String getUserId() {
    return userId
  }

  void setUserId(String userId) {
    this.userId = userId
  }

  String getPayloadType() {
    return payloadType
  }

  void setPayloadType(String payloadType) {
    this.payloadType = payloadType
  }

  String getPayload() {
    return payload
  }

  void setPayload(String payload) {
    this.payload = payload
  }

  Date getCreateDate() {
    return createDate
  }

  void setCreateDate(Date createDate) {
    this.createDate = createDate
  }

  Date getUpdateDate() {
    return updateDate
  }

  void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate
  }
}
