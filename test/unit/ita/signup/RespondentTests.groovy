package ita.signup



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Respondent)
class RespondentTests {

    void testConstraints() {
        def respondent = new Respondent(emailAddress: "this is not an email address", response: "yes")
        mockForConstraintsTests(Respondent, [respondent])
        assert respondent.validate()
        assert "email" == respondent.errors["emailAddress"]
    }
}
