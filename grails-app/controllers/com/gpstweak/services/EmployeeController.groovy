package com.gpstweak.services

import org.alternativevision.gpx.GPXParser
import org.alternativevision.gpx.beans.GPX

import java.text.SimpleDateFormat

class EmployeeController {

    private static final METERS_IN_FEET = new BigDecimal(0.3048)
    private static final FEET_IN_METERS = new BigDecimal(3.28084)
    private static final FEET_IN_KILOMETER = new BigDecimal(3280.84)
    private static final FEET_IN_MILE = new BigDecimal(5280)

    private static final GPS_DATE = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy")  //Ex: Tue Dec 23 16:20:53 MST 2014

    def index() {

        //EmployeeService service = new EmployeeService()
        //service.doStuff()

        MongoService mongoService = new MongoService()
        mongoService.doMongo()

        /*
        String path = request.getSession().getServletContext().getRealPath("/")

        GPXParser parser = new GPXParser()
        FileInputStream data = new FileInputStream("${path}/gpxdata/sample.gpx");
        GPX gpx = parser.parseGPX(data);

        def totalFeet = new BigDecimal(0.0)
        def totalTimeSeconds = new Integer(0)
        def totalElevationGain = new Integer(0)
        def totalElevationLoss = new Integer(0)

        gpx.getTracks().each { track ->
            def lastTrackPoint
            track.trackPoints.each { trackPoint ->
                println "TrackPoint: Longitude[${trackPoint.longitude}], Latitude[${trackPoint.latitude}], " +
                        "Elevation: ${trackPoint.elevation * FEET_IN_METERS}, " +
                        "Time: ${trackPoint.time.toString()}"
                if (lastTrackPoint) {
                    totalFeet += calculateDistanceInFeet(
                            (BigDecimal)lastTrackPoint.latitude, (BigDecimal)lastTrackPoint.longitude,
                            (BigDecimal)trackPoint.latitude, (BigDecimal)trackPoint.longitude)
                    totalTimeSeconds += calculateTotalSecondsElapsed(lastTrackPoint.time, trackPoint.time)
                    if(trackPoint.elevation >= lastTrackPoint.elevation) {
                        totalElevationGain += trackPoint.elevation - lastTrackPoint.elevation
                    } else {
                        totalElevationLoss += lastTrackPoint.elevation - trackPoint.elevation
                    }

                    // println "Distance from last: ${totalFeet}"
                }
                lastTrackPoint = trackPoint
            }
        }

        def miles = totalFeet / FEET_IN_MILE
        println "Total Miles: ${miles}"
        def minutes = totalTimeSeconds / 60
        println "Total Minutes: ${minutes}"
        println "Total Elevation Gain: ${totalElevationGain}"
        println "Total Elevation Loss: ${totalElevationLoss}"
        println "Net Elevation: ${totalElevationGain - totalElevationLoss}"

        def timeHours = minutes / 60
        def milesPerHour = calculateMilesPerHour(miles, timeHours)
        println "Miles/HR: ${milesPerHour}"

        def minutesPerMile = calculateMinutesPerMile(miles, totalTimeSeconds)
        def secondsPerMile = calculateSecondsPerMile(miles, totalTimeSeconds)
        println "Mins/Mile: ${minutesPerMile}:${secondsPerMile}"

        */
    }

    private Double calculateDistanceInFeet(BigDecimal lat1, BigDecimal lon1, BigDecimal lat2, BigDecimal lon2) {

        BigDecimal R = 6371; // km
        BigDecimal dLat = Math.toRadians(lat2-lat1)
        BigDecimal dLon = Math.toRadians(lon2-lon1)
        lat1 = Math.toRadians(lat1)
        lat2 = Math.toRadians(lat2)

        BigDecimal a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.sin(dLon/2) * Math.sin(dLon/2) * Math.cos(lat1) * Math.cos(lat2)
        BigDecimal c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a))
        BigDecimal d = R * c

        d * FEET_IN_KILOMETER
    }

    private Integer calculateTotalSecondsElapsed(Date previousDateTime, Date currentDateTime) {

        def seconds = (currentDateTime.time - previousDateTime.time) / 1000

        seconds
    }

    private BigDecimal calculateMilesPerHour(BigDecimal distanceMiles, BigDecimal timeHours) {
        if(timeHours > 0) {
            distanceMiles / timeHours
        } else {
            0
        }
    }

    private Integer calculateMinutesPerMile(BigDecimal distanceMiles, BigDecimal timeSeconds) {
        if(distanceMiles > 0) {
            def a = timeSeconds / distanceMiles
            Double b = a / 60
            b.trunc()
        } else {
            0
        }
    }

    private Integer calculateSecondsPerMile(BigDecimal distanceMiles, BigDecimal timeSeconds) {
        if(distanceMiles > 0) {
            def a = timeSeconds / distanceMiles
            Double b = a / 60
            Integer mins =b.trunc()
            def remainder = b - mins
            def secs = Math.round(60 * remainder)
            secs
        } else {
            0
        }
    }
}
