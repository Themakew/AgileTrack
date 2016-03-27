package agiletrack

class Features {

    String name
    String description

    static belongsTo = [epic:Epic]
    static hasMany = [userStories: UserStory]

    static constraints = {
    }

    String toString() {
    	return this.name
    }
}
