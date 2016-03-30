package agiletrack

class InvestimentTheme {

    String name
    String description
    String initials

    static hasMany = [epics: Epic]

    static constraints = {
    }

    String toString() {
    	return this.name
    }
}
