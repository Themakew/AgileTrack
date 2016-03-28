package agiletrack

class Task {

    String description
    Integer plannedPontuation
    Integer realPontuation

    static hasMany = [acceptanceCriteria: AcceptanceCriteria, ownerTasks: OwnerTask]
    static belongsTo = [userStories:UserStory]
    static constraints = {
    }

    String toString() {
    	return this.description
    }
}
