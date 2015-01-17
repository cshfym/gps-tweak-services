package com.gpstweak.services

import com.gpstweak.domain.GPSData
import com.gpstweak.domain.PayloadType
import grails.transaction.Transactional
import org.springframework.stereotype.Service

import java.text.SimpleDateFormat

@Service
@Transactional
class GPSDataService extends BaseEntityManager {

  def createMockGPSData() {

    GPSData gps = new GPSData()
    gps.with {
      userId = "charward"
      createDate = new Date()
      payloadClass = GPSData.getName()
      payload = ""
    }

    create(gps)
  }

  def persistGPSData(GPSData data) {
    data.setCreateDate(new Date())
    data.setPayloadClass(GPSData.getName())
    create(data)
  }
}
