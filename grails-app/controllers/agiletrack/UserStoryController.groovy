package agiletrack

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UserStoryController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond UserStory.list(params), model:[userStoryCount: UserStory.count()]
    }

    def show(UserStory userStory) {
        respond userStory
    }

    def create() {
        respond new UserStory(params)
    }

    @Transactional
    def save(UserStory userStory) {
        if (userStory == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (userStory.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond userStory.errors, view:'create'
            return
        }

        userStory.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'userStory.label', default: 'UserStory'), userStory.id])
                redirect userStory
            }
            '*' { respond userStory, [status: CREATED] }
        }
    }

    def edit(UserStory userStory) {
        respond userStory
    }

    @Transactional
    def update(UserStory userStory) {
        if (userStory == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (userStory.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond userStory.errors, view:'edit'
            return
        }

        userStory.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'userStory.label', default: 'UserStory'), userStory.id])
                redirect userStory
            }
            '*'{ respond userStory, [status: OK] }
        }
    }

    @Transactional
    def delete(UserStory userStory) {

        if (userStory == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        userStory.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'userStory.label', default: 'UserStory'), userStory.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'userStory.label', default: 'UserStory'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
