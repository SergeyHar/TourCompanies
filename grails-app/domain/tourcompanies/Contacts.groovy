package tourcompanies


class Contacts {
    Address address
    String email
    String phone

    static constraints = {
        address nullable: true
        email nullable: false
        phone nullable: true
    }
}
