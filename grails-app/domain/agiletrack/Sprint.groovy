package agiletrack

class Sprint {

    String sprintID
    String initials

    static hasMany = [userStories: UserStory]
    static belongsTo = UserStory
    
    static constraints = {
    }

    String toString() {
    	return "Sprint " + this.sprintID
    }
}
