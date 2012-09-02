class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(controller: "respondent", action:"/create")
		"500"(view:'/error')
	}
}
