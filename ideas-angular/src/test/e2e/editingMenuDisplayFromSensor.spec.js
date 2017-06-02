describe("sysadmin with edit menu", function() {

	var originalTimeout;
	//var num=0;

	beforeEach(function() {
        jasmine.DEFAULT_TIMEOUT_INTERVAL = 1000000;
    });

	it("should open edit menu on click and update values in table from sensor", function(){

        browser.get('http://localhost:3000/login');
        browser.driver.sleep(1000).then(function(){
            var inputUsername = element(by.css('input.user'))
            inputUsername.sendKeys("Luca");
            var inputPassword = element(by.css('input.password'))
            inputPassword.sendKeys("Joss");

            browser.driver.sleep(1000).then(function(){
                element.all(by.buttonText("Login")).click();

                browser.driver.sleep(6000).then(function() {
                    browser.ignoreSynchronization = true;
                    element.all(by.buttonText("View sensor list")).click();

					browser.driver.sleep(4000).then(function() {
					element.all(by.css(".datatable-body-row")).each(function(element,num) {
						//console.log("num:"+ num);
						if(num===0){
							element.click().then(function() {
								num=num+1;
							})
						}
						else{
							return true;
					}
					})
					browser.driver.sleep(2000);
					expect(element(by.buttonText('Update')).isDisplayed()).toEqual(true);
					//element.all(by.buttonText(".Close")).click();
					//browser.driver.sleep(1000);

					});

                });

            });
        });
    });
});
