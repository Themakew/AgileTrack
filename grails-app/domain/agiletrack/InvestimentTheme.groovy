package agiletrack

class InvestimentTheme {

    String name
    String description

    static hasMany = [epics: Epic]

    static constraints = {
    }

    String toString() {
    	return this.name
    }
}
