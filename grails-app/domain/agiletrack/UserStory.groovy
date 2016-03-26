package agiletrack

class UserStory {

    String title
    String description
    Integer pontuation

    static hasMany = [tasks: Task]
    static hasMany = [sprints: Sprint]
    static belongsTo = Sprint

    static constraints = {
    }

    String toString() {
    	return this.title
    }
}
