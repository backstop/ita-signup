package ita.signup

class Respondent {
    String emailAddress
    String response

    static constraints = {
        response inList: ["Joe", "Fred", "Bob"]
        emailAddress email: true, blank: false
    }
}
