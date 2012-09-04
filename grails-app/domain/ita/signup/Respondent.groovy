package ita.signup

class Respondent {
    String response
    String emailAddress

    static constraints = {
        response inList: ["A","B","C","D"]
        emailAddress email: true, blank: false
    }
}
