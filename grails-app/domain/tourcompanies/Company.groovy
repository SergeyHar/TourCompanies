package tourcompanies

class Company {

    String companyName
    Contacts contacts


    static constraints = {
        companyName nullable: false, blank: true
        contacts nullable: true, blank: false
    }
}
