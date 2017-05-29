exports.config={
    seleniumAddress: 'http://localhost:4444/wd/hub',
    specs: ['viewDevice.spec.js'],
    capabilities: {
        browserName: 'firefox'
    }
}