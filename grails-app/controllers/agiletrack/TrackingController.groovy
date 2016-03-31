package agiletrack

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured
import groovy.sql.Sql

@Secured('ROLE_ADMIN')
@Transactional(readOnly = true)
class TrackingController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def requirementsForInvestimentThemeID() {

      def db = [url: "jdbc:mysql://localhost:3306/agiletrack?useUnicode=true&characterEncoding=UTF-8",
          user: "root", password: "root", driver: 'com.mysql.jdbc.Driver']
      def sql = Sql.newInstance(db.url, db.user, db.password, db.driver)
      println "DB connection ready"

      def rows = []

      sql.eachRow('''select it.id as theme_id, it.initials as theme_initials, ep.id as epic_id, ep.initials as epic_initials, feat.id as feat_id, feat.initials as feat_initials, us.id as us_id, us.pontuation as us_pontuation, us.initials as us_initials, s.id as sprint_id,s.sprintid as sprint_num, t.id as task_id, t.initials as task_initials, ac.id as criteria_id, ac.accepted as criteria_accepted, ac.initials as criteria_initials, ot.id as owner_id, ot.name as owner_name, ot.initials as owner_initials from investiment_theme as it inner join epic as ep on it.id = ep.theme_id inner join features as feat on ep.id=feat.epic_id inner join user_story as us on feat.id = us.feature_id inner join user_story_sprints as uss on uss.user_story_id = us.id inner join sprint as s on s.id=uss.sprint_id inner join task as t on us.id = t.user_stories_id inner join acceptance_criteria as ac on t.id=ac.tasks_id inner join task_owner_tasks as tot on tot.task_id = t.id inner join owner_task as ot on ot.id = tot.owner_task_id where it.id=1''', {row ->
        def rowMap = [:]
        rowMap["theme_id"] = row.theme_id
        rowMap["theme_initials"] = row.theme_initials
        rowMap["epic_id"] = row.epic_id
        rowMap["epic_initials"] = row.epic_initials
        rowMap["feature_id"] = row.feat_id
        rowMap["feature_initials"] = row.feat_initials
        rowMap["user_story_id"] = row.us_id
        rowMap["user_story_pontuation"] = row.us_pontuation
        rowMap["user_story_initials"] = row.us_initials
        rowMap["sprint_id"] = row.sprint_id
        rowMap["sprint_num"] = row.sprint_num
        rowMap["task_id"] = row.task_id
        rowMap["task_initials"] = row.task_initials
        rowMap["acceptance_criteria_id"] = row.criteria_id
        rowMap["acceptance_criteria_isAccepted"] = row.criteria_accepted
        rowMap["acceptance_criteria_initials"] = row.criteria_initials
        rowMap["owner_id"] = row.owner_id
        rowMap["owner_name"] = row.owner_name
        rowMap["owner_initials"] = row.owner_initials
        println rowMap
        rows.add(rowMap)
      })

      println rows
      return rows
    }


    def index(Integer max) {
        def rows = requirementsForInvestimentThemeID()
        params.max = Math.min(max ?: 10, 100)
        respond Tracking.list(params), model:[trackingCount: Tracking.count()]
        def result = [:]
        [result:rows]
    }

    def show(Tracking tracking) {
        respond tracking
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'tracking.label', default: 'Tracking'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
