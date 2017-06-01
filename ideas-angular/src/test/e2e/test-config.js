exports.config={
    seleniumAddress: 'http://localhost:4444/wd/hub',
    specs: ['sysAdminTest.spec.js'],
    capabilities: {
        browserName: 'firefox'
    },

  

		allScriptsTimeout: 10000000

}
