describe("go to sensor list page", function() {

	beforeEach(function() {
        jasmine.DEFAULT_TIMEOUT_INTERVAL = 1000000;
    });
	
	it("should login and redirect to sensor list page", function(){
        browser.get('http://localhost:3000/login');
        browser.driver.sleep(1000).then(function(){
            var inputUsername = element(by.css('input.user'))
            inputUsername.sendKeys("ideas");
            var inputPassword = element(by.css('input.password'))
            inputPassword.sendKeys("ideaspw");

            browser.driver.sleep(1000).then(function(){
                element.all(by.buttonText("Login")).click();

                    element.all(by.buttonText("View sensor list")).click();
                    expect(browser.getCurrentUrl()).toEqual('http://localhost:3000/table/sensor');
            });
        });
    });
});
