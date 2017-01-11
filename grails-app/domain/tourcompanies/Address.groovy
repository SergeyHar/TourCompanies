package tourcompanies

class Address {

    String country
    String city
    String street
    String home
    String room


    static constraints = {
        country nullable: true
        city nullable: true
        street nullable: true
        home nullable: true
        room nullable: true
    }
}
