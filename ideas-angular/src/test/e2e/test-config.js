exports.config={
    seleniumAddress: 'http://localhost:4444/wd/hub',
    specs: ['login.spec.js'],
    capabilities: {
        browserName: 'firefox'
    },

  

		//allScriptsTimeout: 10000000

}
