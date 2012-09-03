class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(controller: "respondent", action:"/create")

		// Added by Errors plugin
		"403"(controller: "errors", action: "error403")
		"404"(controller: "errors", action: "error404")
		"500"(controller: "errors", action: "error500")
	}
}
