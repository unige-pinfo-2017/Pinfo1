exports.config = {
	seleniumAddress: 'http://localhost:4444/wd/hub',
//	specs: ['src/test/e2e/**/*spec.js'],
	specs: ['src/test/e2e/**/logout.spec.js'],
	capabilities: {
		browserName: 'firefox'
	}
};
