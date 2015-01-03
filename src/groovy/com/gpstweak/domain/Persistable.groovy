package com.gpstweak.domain

import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Temporal
import javax.persistence.TemporalType


abstract class Persistable implements Serializable {

  @Temporal(TemporalType.DATE)
  @Column(name = "create_date")
  Date createDate

  @Temporal(TemporalType.DATE)
  @Column(name = "update_date")
  Date updateDate

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
