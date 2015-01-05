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
        "/data/findAll"(controller:"data", action:"findAll")
        "/data/push"(controller:"data", action:"push")


		"500"(view:'/error')
	}
}
