package nfjs.geocoder

import groovy.json.JsonSlurper

class GeocoderJSON {
    public static final String BASE = 
            'http://maps.google.com/maps/api/geocode/json?'

    Stadium fillInLatLng(Stadium stadium) {
        String encoded =
            [stadium.street, stadium.city, stadium.state].collect { 
                URLEncoder.encode(it,'UTF-8')
            }.join(',')
        String qs = "address=$encoded"
        String url = "$BASE$qs"
        println url
        def response = new JsonSlurper().parse(url.toURL())
        def loc = response.results[0].geometry.location
        stadium.latitude = loc.lat.toBigDecimal()
        stadium.longitude = loc.lng.toBigDecimal()
        stadium
    }
}
