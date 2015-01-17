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
//@SequenceGenerator(name="pk_sequence", sequenceName="seq_gps_data", allocationSize = 1)
class GPSData extends Persistable {

  public GPSData() { }

  @Id
  //@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="pk_sequence")
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name = "id")
  Long id

  @Column(name = "user_id")
  String userId

  @Column(name = "payload_class")
  String payloadClass

  @Column(name = "payload")
  String payload

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "create_date", columnDefinition="DATETIME")
  Date createDate

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "update_date", columnDefinition="DATETIME")
  Date updateDate

  Long getId() { id }
  void setId(Long id) { this.id = id }

  String getUserId() { userId }
  void setUserId(String userId) { this.userId = userId }

  String getPayloadClass() { payloadClass }
  void setPayloadClass(String payloadClass) { this.payloadClass = payloadClass }

  String getPayload() { payload }
  void setPayload(String payload) { this.payload = payload }

  Date getCreateDate() { createDate }
  void setCreateDate(Date createDate) { this.createDate = createDate }

  Date getUpdateDate() { updateDate }
  void setUpdateDate(Date updateDate) { this.updateDate = updateDate }

}
