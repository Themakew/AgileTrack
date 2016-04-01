package agiletrack

class Sprint {

    String sprintID
    String initials

    static hasMany = [userStories: UserStory]
    static belongsTo = UserStory

    static constraints = {
      sprintID blank:false
      initials blank:false
    }

    String toString() {
    	return "Sprint " + this.sprintID
    }
}
