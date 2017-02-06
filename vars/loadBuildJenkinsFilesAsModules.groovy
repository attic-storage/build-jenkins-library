def call() {
    def modules = [:]
    def moduleFiles = findFiles(glob: '**/build.jenkins')
    for(int i = 0; i < moduleFiles.size(); i++) {
        def modulePath = moduleFiles[i].path
        def directory = moduleFiles[i].path.replaceAll('/build.jenkins', '')
        if(fileExists(modulePath)) {
            def module = load path: modulePath
            modules.put(directory, {
                dir(directory) {
                    module.build()
                }
            })
        }
    }
    modules
}