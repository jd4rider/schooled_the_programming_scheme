package schooled_the_programming_scheme

import grails.converters.JSON
import groovy.transform.Synchronized
import schooled_the_programming_scheme.Library
import grails.transaction.Transactional


class LibraryController {


	def maxID = 5;
	def files = Library.list();
	// [
	// 	[id:0, name:"myBookOnGroovy", folder:"/", content:"println 'Hello World!'", mode: "text/x-groovy", author: [name:"Wolfgang Haendler"]],
	// 	[id:1, name:"myBookOnJavascript", folder:"/", content:"console.log('Hello World!')", mode: "text/javascript", author: [name:"Wolfgang Haendler"]],
	// 	[id:2, name:"myBookOnHTML", folder:"/", content:"<html><head><title>Hello World!</title></head><body>Hallo Welt!</body></html>", mode: "text/html", author: [name:"Wolfgang Haendler"]],
	// 	[id:3, name:"myBookOnDynamicHTML", folder:"/published", content:"<html><body><script>document.title='Hello World'; document.write('Hallo Welt!');</script></body></html>", mode: "application/x-ejs", author: [name:"Wolfgang Haendler"]],
	// 	[id:4, name:"myBookOnCPP", folder:"/published", content:"std::cout << \"Lorem ipsum sit dolor et amet.\" << std::endl", mode: "text/x-c++src", author: [name:"Wolfgang Haendler"]],
	// 	[id:5, name:"myBrokenBook", folder:null, content:"Hidden in folder view, also opening this will fail.", author: [name:"Wolfgang Haendler"]]
	// ]

	@Synchronized
	def deleteFile(){
		def id = params.int('id')

		println("====== DELETE FILE CALLED ======")
		println(params as JSON)
		println("================================")

		if(id == 5)
			render ([status: "error", msg: "I failed. Told you so"] as JSON)


		def removeThis = null
		files.each( {
			if(it.id == id){
				removeThis = it
			}
		})

		if(removeThis != null){
			files.remove(removeThis)
			render ([status:"success", msg: "File deleted"] as JSON)
		}

		render ([status: "error", msg: "Unknown id"] as JSON)
	}

	@Synchronized
	def listFiles(){
		println(files as JSON)
		files = Library.list();

		println("======= LIST FILES CALLED ======")

		render ([status:"success",
			result: files.collect({
				[id:it.id, name: it.name, folder: it.folder]
			})
		] as JSON)
	}

	@Synchronized
	def loadFile(){
		def id = params.int('id')

		println("======= LOAD FILE CALLED =======")
		println(params as JSON)
		println("================================")

		//if(id == 5)
		//	render ([status: "error", msg: "I failed. Told you so"] as JSON)

		files.each( {
			if(it.id == id){
				render ([status:"success", result:it] as JSON)
			}
		})

		render ([status: "error", msg: "Unknown id"] as JSON)
	}

	@Synchronized
	def saveFile(Library files){
		def id = params.int('id')

		println("======= SAVE FILE CALLED =======")
		println(params as JSON)
		println("================================")

		//if(id == 5)
		//	render ([status: "error", msg: "I failed. Told you so"] as JSON)

		//if(id == null){
			//files.push([id:++maxID, name: params['name'], content: params['content'], mode: params["mode"], folder: params["folder"], author: [name: params["author.name"]]])

		if (files.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond files.errors, view:'create'  
            return
        }

		files.save flush:true
		render ([status: "success", msg: "file was saved", library: files] as JSON)
		//}else{
		//	files.each( {
		//		if(it.id == id){
		//			it.content = params['content']
		//			it.name = params['name']
		//			it.mode = params['mode']
		//			it.folder = params['folder']
		//			it.author = [name: params["author.name"]]
		//			files.save flush:true
		//			render ([status: "success", msg: "file was saved"] as JSON)
		//		}
		//	} )
		//}
		render ([status: "error", msg: "Unknown id"] as JSON)
	}
}