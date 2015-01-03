package com.gpstweak.controller

import com.gpstweak.domain.Employee
import com.gpstweak.domain.GPSPayloadWrapper
import com.gpstweak.services.GPSDataService
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

    def show(String userId) {
        List<GPSPayloadWrapper> list = mongoService.getGPSPayloadWrapperDataByUserId(userId)
        render gson.toJson(list)
    }

    def save() {
      /*
        GPSPayloadWrapper payload = new GPSPayloadWrapper(request.JSON)
        mongoService.saveData(payload)
        response.setStatus(201)
        render gson.toJson(payload)
        */

      gpsDataService.createGPSData()

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
