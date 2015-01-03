package com.gpstweak.controller

import com.gpstweak.domain.Employee
import grails.rest.RestfulController
import grails.transaction.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ResourceLoaderAware
import security.GPSTweakSecurityService

import javax.crypto.Cipher
import java.security.Key
import java.security.KeyFactory
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.PrivateKey
import java.security.PublicKey
import java.security.spec.RSAPrivateKeySpec
import java.security.spec.RSAPublicKeySpec

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

  @Autowired
  GPSTweakSecurityService securityService

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

      String header = request.getHeader("Authorization")
      if(!AUTH_TOKEN.equals(header)) {
        response.sendError(401, "Invalid authentication token.")
        return
      }

      byte[] data = "Hello from RSA!".bytes
      byte[] encrypted = securityService.rsaEncryptWithPublicKey(data)

      byte[] decrypted = securityService.rsaDecryptWithPrivateKey(encrypted)
      String d = new String(decrypted)
      println d

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
