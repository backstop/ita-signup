package ita.signup



import org.junit.*
import grails.test.mixin.*

@TestFor(RespondentController)
@Mock(Respondent)
class RespondentControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/respondent/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.respondentInstanceList.size() == 0
        assert model.respondentInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.respondentInstance != null
    }

    void testSave() {
        controller.save()

        assert model.respondentInstance != null
        assert view == '/respondent/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/respondent/show/1'
        assert controller.flash.message != null
        assert Respondent.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/respondent/list'


        populateValidParams(params)
        def respondent = new Respondent(params)

        assert respondent.save() != null

        params.id = respondent.id

        def model = controller.show()

        assert model.respondentInstance == respondent
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/respondent/list'


        populateValidParams(params)
        def respondent = new Respondent(params)

        assert respondent.save() != null

        params.id = respondent.id

        def model = controller.edit()

        assert model.respondentInstance == respondent
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/respondent/list'

        response.reset()


        populateValidParams(params)
        def respondent = new Respondent(params)

        assert respondent.save() != null

        // test invalid parameters in update
        params.id = respondent.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/respondent/edit"
        assert model.respondentInstance != null

        respondent.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/respondent/show/$respondent.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        respondent.clearErrors()

        populateValidParams(params)
        params.id = respondent.id
        params.version = -1
        controller.update()

        assert view == "/respondent/edit"
        assert model.respondentInstance != null
        assert model.respondentInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/respondent/list'

        response.reset()

        populateValidParams(params)
        def respondent = new Respondent(params)

        assert respondent.save() != null
        assert Respondent.count() == 1

        params.id = respondent.id

        controller.delete()

        assert Respondent.count() == 0
        assert Respondent.get(respondent.id) == null
        assert response.redirectedUrl == '/respondent/list'
    }
}
