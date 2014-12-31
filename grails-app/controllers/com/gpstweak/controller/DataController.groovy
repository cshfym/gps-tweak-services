package com.gpstweak.controller

import com.gpstweak.domain.Employee
import grails.rest.RestfulController
import grails.transaction.*
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*
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

    Gson gson = new Gson()

    def index() {
        Employee e = new Employee(firstName: "Index", lastName: "Method")
        render gson.toJson(e)
    }

    def show(Integer id) {
        Employee e = new Employee(id: (null != id) ? id : 1, firstName: "Show", lastName: "Method")
        render gson.toJson(e)
    }

    def save() {
        def jsonObj = request.JSON
        Employee e = new Employee(jsonObj)
        render gson.toJson(e)
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
