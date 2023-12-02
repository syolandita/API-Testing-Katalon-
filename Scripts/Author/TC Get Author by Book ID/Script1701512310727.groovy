import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

//Requirement

//a. Id harus sama dengan parameter id di request
//b. Data tidak boleh ada yang null
//c. Response code harus 200

response = WS.sendRequest(findTestObject('Object Repository/Author/Get Author by Book ID'))

WS.verifyResponseStatusCode(response, 200)

id = WS.getElementPropertyValue(response, "id");
idBook = WS.getElementPropertyValue(response, "idBook");
firstName = WS.getElementPropertyValue(response, "firstName");
lastName = WS.getElementPropertyValue(response, "lastName");

assert id != null && id != "";
assert idBook != null && idBook != "";
assert firstName != null && firstName != "";
assert lastName != null && lastName != "";

assert idBook[0] == 3;

responseArray = new groovy.json.JsonSlurper().parseText(response.getResponseText())

assert responseArray.size() > 0

responseArray.each { responseObject ->
	assert responseObject.idBook.any { it == 3 }
}
