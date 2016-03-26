package agiletrack

class Epic {

    String name
    String description

	static hasMany = [features: Features]

    static constraints = {
    }

    String toString() {
    	return this.name
    }
}
