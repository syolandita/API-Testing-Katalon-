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

// Requirement
// 1. Jumlahnya tidak boleh lebih dari 30
// 2. Tidak boleh ada yang kosong / null
// 3. Response code harus 200


// Fungsi mengambil API
response = WS.sendRequest(findTestObject('Object Repository/Actvity/Get All Activities'));

// Verifikasi status code
WS.verifyResponseStatusCode(response, 200)

// Verifikasi jumlah data = 30
//WS.verifyElementsCount(response, "", 30)

// Verifikasi jumlah data tidak lebih dari 30
jmlElement = WS.getElementsCount(response, "")

assert jmlElement <= 30


// Verifikasi semua data 
for (int i = 0; i < jmlElement; i++ ) {
	// Ambil semua value dari data
	id = WS.getElementPropertyValue(response, "["+i+"].id");
	title = WS.getElementPropertyValue(response, "["+i+"].title");
	dueDate = WS.getElementPropertyValue(response, "["+i+"].dueDate");
	completed = WS.getElementPropertyValue(response, "["+i+"].completed");
	
	// Verifikasi data tidak boleh null
	assert id != null && id != "";
	assert title != null && title != "";
	assert dueDate != null && dueDate != "";
	assert completed != null && completed != "";
}