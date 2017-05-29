exports.config={
    seleniumAddress: 'http://localhost:4444/wd/hub',
    specs: ['logout.spec.js'],
    capabilities: {
        browserName: 'firefox'
    }
}