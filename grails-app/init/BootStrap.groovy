import agiletrack.Role
import agiletrack.User
import agiletrack.UserRole

class BootStrap {

   def init = {

      def adminRole = new Role('ROLE_ADMIN').save()
      def userRole = new Role('ROLE_USER').save()

      def testUser = new User('root', 'root').save()

      UserRole.create testUser, adminRole

      UserRole.withSession {
         it.flush()
         it.clear()
      }

      assert User.count() == 1
      assert Role.count() == 2
      assert UserRole.count() == 1
   }
}

