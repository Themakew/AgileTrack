package agiletrack

class UserStory {

    String title
    String description
    Integer pontuation
    String initials

    static hasMany = [tasks: Task, sprints: Sprint]
    static belongsTo = [feature:Features]

    static constraints = {
    }

    String toString() {
    	return this.title
    }
}
