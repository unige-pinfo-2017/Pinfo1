describe("basic user test", function() {

	beforeEach(function() {
		jasmine.DEFAULT_TIMEOUT_INTERVAL = 1000000;
	});

it("shouldn't go to user list", function(){

        browser.get('http://localhost:3000/login');
        browser.driver.sleep(1000).then(function(){
            var inputUsername = element(by.css('input.user'))
            inputUsername.sendKeys("Antoine");
            var inputPassword = element(by.css('input.password'))
            inputPassword.sendKeys("Benquet");
            browser.driver.sleep(1000).then(function(){
                element.all(by.buttonText("Login")).click();
                browser.sleep(2000);
                browser.get('http://localhost:3000/table/user');
                // means that it's impossible for a basic user to go to localhost:3000/table/user
                });
            }).then(function(){
            expect(browser.getCurrentUrl()).toEqual('http://localhost:3000/overview');
            });

        });
    });
