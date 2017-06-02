exports.config={
    seleniumAddress: 'http://localhost:4444/wd/hub',

    specs: ['editingMenuDisplayFromSensor.spec.js'],

    capabilities: {
        browserName: 'chrome'
    },

		allScriptsTimeout: 10000000

}
