package tourcompanies

class BootStrap {

    def init = { servletContext ->

        def u1 = User.findByUsername("aram")
        if (!User.findByUsername("aram")) {
            u1 = new User(username: "aram", password: 123, email: 'test@test.com', enabled: true).save(flush: true)
        }

        def u2 = User.findByUsername("hayk") ? User.findByUsername("hayk") : new User(username: "hayk", password: 123, email: 'hayk@test.com', enabled: true).save(flush: true)

        // create Roles
        def roleAdmin, roleCompany, roleUser


        if (!Role.findByAuthority("ROLE_COMPANY")) {
            roleCompany = new Role(authority: "ROLE_COMPANY").save(flush: true)
        }

        roleAdmin = Role.findByAuthority("ROLE_ADMIN") ? Role.findByAuthority("ROLE_ADMIN") : new Role(authority: "ROLE_ADMIN").save(flush: true)
        roleCompany = Role.findByAuthority("ROLE_COMPANY") ? Role.findByAuthority("ROLE_COMPANY") : new Role(authority: "ROLE_COMPANY").save(flush: true)
        roleUser = Role.findByAuthority("ROLE_USER") ? Role.findByAuthority("ROLE_USER") : new Role(authority: "ROLE_USER").save(flush: true)

//        //Admin role users
//        if (!UserRole.findByUserAndRole(u2, roleAdmin)) {
//            UserRole.create u2, roleAdmin
//        }
//        //Company role users
//        if(!UserRole.findByUserAndRole(u1, roleCompany)) {
//            UserRole.create u1, roleCompany
//        }


    }
    def destroy = {
    }
}
