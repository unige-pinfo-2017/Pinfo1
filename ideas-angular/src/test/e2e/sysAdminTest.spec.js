describe("filter device list", function() {

    //var originalTimeout;
    //var num=0;

    beforeEach(function() {
        jasmine.DEFAULT_TIMEOUT_INTERVAL = 1000000;
    });

    it("should login and redirect to device list page and filtrer device list", function(){

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
                    element.all(by.buttonText("View device list")).click();

                    browser.driver.sleep(4000).then(function() {
                    element.all(by.css(".datatable-body-row")).each(function(element,num) {
                        //console.log("num:"+ num);
                        if(num===0){
                            element.click().then(function () {
                                browser.sleep(3000);
                                num=num+1;
                            })
                        }
                        else{
                            return true;
                    }
                    })
                    //expect(num).toEqual(3);
                    expect(element.all(by.css(".small-2 columns"))).toEqual(true);

                    });

                });

            });
        });
    });
});
