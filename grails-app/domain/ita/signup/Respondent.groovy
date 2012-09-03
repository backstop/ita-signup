package ita.signup

class Respondent {
    String response
    String emailAddress

    static constraints = {
        response inList: ["Joe", "Fred", "Bob"]
        emailAddress email: true, blank: false
    }
}
