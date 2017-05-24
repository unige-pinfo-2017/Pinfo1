exports.config = {
	seleniumAddress: 'http://localhost:4444/wd/hub',
	specs: ['src/test/e2e/**/*spec.js'],
	capabilities: {
		browserName: 'firefox'
	}
};
