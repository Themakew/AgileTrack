package agiletrack

class AcceptanceCriteria {

    String description
    boolean accepted
    String initials

    static belongsTo = [tasks:Task]
    
    static constraints = {
    }

    String toString() {
    	return this.description
    }
}
