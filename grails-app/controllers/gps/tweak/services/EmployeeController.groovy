package gps.tweak.services

import com.gpstweak.service.EmployeeService

class EmployeeController {

    def index() {

        EmployeeService service = new EmployeeService()
        service.doStuff()
    }
}
