package ita.signup

import org.springframework.dao.DataIntegrityViolationException

class RespondentController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [respondentInstanceList: Respondent.list(params), respondentInstanceTotal: Respondent.count()]
    }

    def create() {
        [respondentInstance: new Respondent(params)]
    }

    def save() {
        def respondentInstance = new Respondent(params)
        if (!respondentInstance.save(flush: true)) {
            render(view: "create", model: [respondentInstance: respondentInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'respondent.label', default: 'Respondent'), respondentInstance.id])
        redirect(action: "show", id: respondentInstance.id)
    }

    def show() {
        def respondentInstance = Respondent.get(params.id)
        if (!respondentInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'respondent.label', default: 'Respondent'), params.id])
            redirect(action: "list")
            return
        }

        [respondentInstance: respondentInstance]
    }

    def edit() {
        def respondentInstance = Respondent.get(params.id)
        if (!respondentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'respondent.label', default: 'Respondent'), params.id])
            redirect(action: "list")
            return
        }

        [respondentInstance: respondentInstance]
    }

    def update() {
        def respondentInstance = Respondent.get(params.id)
        if (!respondentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'respondent.label', default: 'Respondent'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (respondentInstance.version > version) {
                respondentInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'respondent.label', default: 'Respondent')] as Object[],
                          "Another user has updated this Respondent while you were editing")
                render(view: "edit", model: [respondentInstance: respondentInstance])
                return
            }
        }

        respondentInstance.properties = params

        if (!respondentInstance.save(flush: true)) {
            render(view: "edit", model: [respondentInstance: respondentInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'respondent.label', default: 'Respondent'), respondentInstance.id])
        redirect(action: "show", id: respondentInstance.id)
    }

    def delete() {
        def respondentInstance = Respondent.get(params.id)
        if (!respondentInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'respondent.label', default: 'Respondent'), params.id])
            redirect(action: "list")
            return
        }

        try {
            respondentInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'respondent.label', default: 'Respondent'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'respondent.label', default: 'Respondent'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
