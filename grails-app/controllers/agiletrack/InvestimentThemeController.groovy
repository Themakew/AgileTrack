package agiletrack

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured
import groovy.sql.Sql

@Secured('ROLE_ADMIN')
@Transactional(readOnly = true)
class InvestimentThemeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def dataSource() {

      def db = [url: "jdbc:mysql://localhost:3306/agiletrack?useUnicode=true&characterEncoding=UTF-8",
          user: "root", password: "root", driver: 'com.mysql.jdbc.Driver']
      def sql = Sql.newInstance(db.url, db.user, db.password, db.driver)
      println "DB connection ready"


      sql.eachRow('''select it.id as theme_id, it.initials as theme_initials, ep.id as epic_id, ep.initials as epic_initials, feat.id as feat_id, feat.initials as feat_initials, us.id as us_id, us.pontuation as us_pontuation, us.initials as us_initials, s.id as sprint_id,s.sprintid as sprint_num, t.id as task_id, t.initials as task_initials, ac.id as criteria_id, ac.accepted as criteria_accepted, ac.initials as criteria_initials, ot.id as owner_id, ot.name as owner_name, ot.initials as owner_initials from investiment_theme as it inner join epic as ep on it.id = ep.theme_id inner join features as feat on ep.id=feat.epic_id inner join user_story as us on feat.id = us.feature_id inner join user_story_sprints as uss on uss.user_story_id = us.id inner join sprint as s on s.id=uss.sprint_id inner join task as t on us.id = t.user_stories_id inner join acceptance_criteria as ac on t.id=ac.tasks_id inner join task_owner_tasks as tot on tot.task_id = t.id inner join owner_task as ot on ot.id = tot.owner_task_id where it.id=1''', {row ->
        println row
        println row[0]
        // Levar em conta o que está como alias na query
        println row.theme_id
      })


    }

    def index(Integer max) {

        dataSource()
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
