package agiletrack

class Features {

    String name
    String description
    String initials

    static belongsTo = [epic:Epic]
    static hasMany = [userStories: UserStory]

    static constraints = {
    }

    String toString() {
    	return this.name
    }
}
