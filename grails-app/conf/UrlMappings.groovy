class UrlMappings {

	static mappings = {

        /*
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
		"/"(view:"/index")
		*/
        "/data"(controller:"data") {
            action = [GET:"show", PUT:"update", DELETE:"delete", POST:"save"]
        }

		"500"(view:'/error')
	}
}
