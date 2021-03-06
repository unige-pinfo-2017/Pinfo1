describe("Logout Test", function() {

	beforeEach(function() {
	   jasmine.DEFAULT_TIMEOUT_INTERVAL = 1000000;
	});

	it("should Login and logout", function(){
        browser.get('http://localhost:3000/login');
        browser.driver.sleep(1000).then(function(){
            var inputUsername = element(by.css('input.user'))
            inputUsername.sendKeys("ideas");
            var inputPassword = element(by.css('input.password'))
            inputPassword.sendKeys("ideaspw");

            browser.driver.sleep(2000).then(function(){
                element.all(by.buttonText("Login")).click();
                element.all(by.buttonText("Logout")).click();
                expect(browser.getCurrentUrl()).toEqual('http://localhost:3000/login');
            });
        });
    });

	/*it("should redirect to login page", function() {
		browser.get("http://localhost:3000/overview");
		//browser.driver.sleep(10000);
		//var heroes = element.all(by.className("col-1-4"));

		browser.driver.sleep(1000).then(function(){
			element.all(by.tagName("svg")).click();
			expect(browser.getCurrentUrl().toEqual("http://localhost:3000/login"));
		});
	});*/
});
