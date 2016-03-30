package agiletrack

class Epic {

    String name
    String description
    String initials

    static belongsTo = [theme:InvestimentTheme]

	  static hasMany = [features: Features]

    static constraints = {
    }

    String toString() {
    	return this.name
    }
}
