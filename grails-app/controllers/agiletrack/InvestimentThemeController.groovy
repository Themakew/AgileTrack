package agiletrack

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured('ROLE_ADMIN')
@Transactional(readOnly = true)
class InvestimentThemeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond InvestimentTheme.list(params), model:[investimentThemeCount: InvestimentTheme.count()]
    }

    def show(InvestimentTheme investimentTheme) {
        respond investimentTheme
    }

    def create() {
        respond new InvestimentTheme(params)
    }

    @Transactional
    def save(InvestimentTheme investimentTheme) {
        if (investimentTheme == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (investimentTheme.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond investimentTheme.errors, view:'create'
            return
        }

        investimentTheme.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'investimentTheme.label', default: 'InvestimentTheme'), investimentTheme.id])
                redirect investimentTheme
            }
            '*' { respond investimentTheme, [status: CREATED] }
        }
    }

    def edit(InvestimentTheme investimentTheme) {
        respond investimentTheme
    }

    @Transactional
    def update(InvestimentTheme investimentTheme) {
        if (investimentTheme == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (investimentTheme.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond investimentTheme.errors, view:'edit'
            return
        }

        investimentTheme.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'investimentTheme.label', default: 'InvestimentTheme'), investimentTheme.id])
                redirect investimentTheme
            }
            '*'{ respond investimentTheme, [status: OK] }
        }
    }

    @Transactional
    def delete(InvestimentTheme investimentTheme) {

        if (investimentTheme == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        investimentTheme.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'investimentTheme.label', default: 'InvestimentTheme'), investimentTheme.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'investimentTheme.label', default: 'InvestimentTheme'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
