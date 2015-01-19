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
            action = [PUT:"update", DELETE:"delete", POST:"save"]
        }
        "/data/$id"(controller:"data") {
            action = [GET:"show", PUT:"updateById", DELETE: "deleteById"]
        }
        "/data/all"(controller:"data", action:"findAll")
        "/data/push"(controller:"data", action:"push")


		"500"(view:'/error')
	}
}
