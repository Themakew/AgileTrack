package agiletrack

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class FeaturesController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Features.list(params), model:[featuresCount: Features.count()]
    }

    def show(Features features) {
        respond features
    }

    def create() {
        respond new Features(params)
    }

    @Transactional
    def save(Features features) {
        if (features == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (features.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond features.errors, view:'create'
            return
        }

        features.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'features.label', default: 'Features'), features.id])
                redirect features
            }
            '*' { respond features, [status: CREATED] }
        }
    }

    def edit(Features features) {
        respond features
    }

    @Transactional
    def update(Features features) {
        if (features == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (features.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond features.errors, view:'edit'
            return
        }

        features.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'features.label', default: 'Features'), features.id])
                redirect features
            }
            '*'{ respond features, [status: OK] }
        }
    }

    @Transactional
    def delete(Features features) {

        if (features == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        features.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'features.label', default: 'Features'), features.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'features.label', default: 'Features'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
