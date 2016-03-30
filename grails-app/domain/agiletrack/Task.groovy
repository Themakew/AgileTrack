package agiletrack

class Task {

    String description
    Integer plannedPontuation
    Integer realPontuation
    String initials

    static hasMany = [acceptanceCriterias: AcceptanceCriteria, ownerTasks: OwnerTask]
    static belongsTo = [userStories:UserStory]
    static constraints = {
    }

    String toString() {
    	return this.description
    }
}
