package agiletrack

class Sprint {

    String sprintID

    static hasMany = [userStories: UserStory]
    static belongsTo = UserStory
    static constraints = {
    }

    String toString() {
    	return "Sprint " + this.sprintID
    }
}
