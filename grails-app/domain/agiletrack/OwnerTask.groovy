package agiletrack

class OwnerTask {

    String name

    static hasMany = [tasks: Task]
    static belongsTo = Task

    static constraints = {
    }

    String toString() {
    	return this.name
    }
}
