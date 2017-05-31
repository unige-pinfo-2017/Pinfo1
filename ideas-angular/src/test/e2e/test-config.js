exports.config={
    seleniumAddress: 'http://localhost:4444/wd/hub',
    specs: ['filter.spec.js'],
    capabilities: {
        browserName: 'firefox'
    },

  

		allScriptsTimeout: 10000000

}
