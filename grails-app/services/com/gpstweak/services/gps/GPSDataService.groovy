package com.gpstweak.services.gps

import com.gpstweak.domain.GPSData
import com.gpstweak.services.db.BaseEntityManager
import grails.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class GPSDataService extends BaseEntityManager {

  def persistGPSData(GPSData data) {
    data.setCreateDate(new Date())
    data.setPayloadClass(GPSData.getName())
    create(data)
  }
}
