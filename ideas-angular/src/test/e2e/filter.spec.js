describe("filter device list", function() {
	it("should login and redirect to device list page and filtrer device list", function(){
        browser.get('http://localhost:3000/login');
        browser.driver.sleep(1000).then(function(){
            var inputUsername = element(by.css('input.user'))
            inputUsername.sendKeys("ideas");
            var inputPassword = element(by.css('input.password'))
            inputPassword.sendKeys("ideaspw");

            browser.driver.sleep(1000).then(function(){
                element.all(by.buttonText("Login")).click();
                //element.all(by.buttonText("View device list")).click();
                browser.driver.sleep(6000).then(function() {
                    browser.ignoreSynchronization = true;
                    //browser.get('http://localhost:3000/table/device');
                    element.all(by.buttonText("View device list")).click();
                    //
                    //element.all(by.buttonText("Overview")).click();
                    browser.driver.sleep(2000).then(function() {
                        var inputFilter = element(by.css('input.filter'))
                        browser.sleep(2000);
                        inputFilter.sendKeys("Antoine");
                        inputFilter.sendKeys(protractor.Key.ENTER);
                        //element.all(by.buttonText("Overview")).click();
                        browser.driver.sleep(9000).then(function() {
                            var containFilter = element.all(by.css(".ngx-datatable"));

                            expect(containFilter.count()).toEqual(1);
                            //expect("Antoine test").toContain("Antoine");

                        });
                    });
                });
            });
        });
    });
});