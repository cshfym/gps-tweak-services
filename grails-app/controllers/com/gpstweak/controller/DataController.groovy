package com.gpstweak.controller

import com.gpstweak.domain.Employee
import com.gpstweak.domain.GPSData
import com.gpstweak.domain.GPSPayloadWrapper
import com.gpstweak.services.GPSDataService
import com.gpstweak.services.MQService
import com.gpstweak.services.MongoService
import org.springframework.beans.factory.annotation.Autowired
import com.gpstweak.services.GPSTweakSecurityService
import com.google.gson.*

/* Grails Controllers:
HTTP Method	URI	Controller Action
GET	/books	index
GET	/books/create	create
POST	/books	save
GET	/books/${id}	show
GET	/books/${id}/edit	edit
PUT	/books/${id}	update
DELETE	/books/${id}	delete

The 'create' and 'edit' actions are already required if you plan to implement an HTML interface for the REST resource.
They are there in order to render appropriate HTML forms to create and edit a resource.
If this is not a requirement they can be discarded.
*/

public class DataController {

    @Autowired
    GPSTweakSecurityService securityService

    @Autowired
    GPSDataService gpsDataService

    @Autowired
    MongoService mongoService



    Gson gson = new Gson()

    def index() {
        Employee e = new Employee(firstName: "Index", lastName: "Method")
        render gson.toJson(e)
    }

    def push() {
      MQService mqService = new MQService("54a78bd2a65b22000900007b", "xwvYCsVgSmla3CCe7JIhXf4YNs0")
      mqService.pushMessage("Test Message", "test-queue")
      response.setStatus(200)
    }

    def show(String id) {
      /*
        List<GPSPayloadWrapper> list = mongoService.getGPSPayloadWrapperDataByUserId(userId)
        render gson.toJson(list)
        */
      if (null == id) {
        response.setStatus(404)
        return
      }

      GPSData data = gpsDataService.find(GPSData.class, Long.valueOf(id))
      if (null == data) {
        response.setStatus(404)
        return
      }

      response.setStatus(200)
      render gson.toJson(data)
    }

    def findAll() {
      List<GPSData> list = gpsDataService.findAll(GPSData.canonicalName)
      response.setStatus(200)
      render gson.toJson(list)
    }

    def save() {
      /*
        GPSPayloadWrapper payload = new GPSPayloadWrapper(request.JSON)
        mongoService.saveData(payload)
        response.setStatus(201)
        render gson.toJson(payload)
        */

      GPSData data = gpsDataService.createGPSData()
      response.setStatus(201)
      render gson.toJson(data)
    }

    def update() {
        Employee e = new Employee(firstName: "Update", lastName: "Method")
        render gson.toJson(e)
    }

    def delete() {
        Employee e = new Employee(firstName: "Delete", lastName: "Method")
        render gson.toJson(e)
    }

}
