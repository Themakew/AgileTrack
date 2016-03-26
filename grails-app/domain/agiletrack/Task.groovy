package agiletrack

class Task {

    String description
    Integer plannedPontuation
    Integer realPontuation

    static hasMany = [acceptanceCriteria: AcceptanceCriteria]
    static hasMany = [ownerTasks: OwnerTask]

    static constraints = {
    }

    String toString() {
    	return this.description
    }
}
