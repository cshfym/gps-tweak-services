package com.gpstweak.services

import com.gpstweak.domain.GPSData
import com.gpstweak.domain.PayloadType
import grails.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class GPSDataService extends BaseEntityManager {

  def createGPSData() {

    GPSData gps = new GPSData()
    gps.with {
      userId = "charward"
      createDate = new Date()
      payloadType = PayloadType.GPX.getType()
      payload = ""
    }

    create(gps)
  }
}
