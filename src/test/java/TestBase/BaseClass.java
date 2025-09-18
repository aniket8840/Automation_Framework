package TestBase;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import io.opentelemetry.sdk.autoconfigure.spi.ConfigProperties;

public class BaseClass {

	public static WebDriver driver;
	public Properties p;

	@BeforeClass(groups = { "Sanity", "Regression", "Master" })
	@Parameters({ "browser" })
	public void setup(String br) throws IOException {
		switch (br) {
		case "chrome":
			driver = new ChromeDriver();
			break;

		case "firefox":
			driver = new FirefoxDriver();
			break;

		case "edge":
			driver = new EdgeDriver();
			break;

		default:
			System.out.println("invalid browser method");
		}

		FileReader file = new FileReader(".\\src\\test\\resources\\config.properties"); //"./src//test//resources//conf.properties");
		p = new Properties();
		p.load(file);

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("ApplicationURL"));
		driver.manage().window().maximize();
	}

	@AfterClass(groups = { "Sanity", "Regression", "Master" })
	public void tearDown() {
		driver.quit();
	}

	public String randomString() {
		String genratedString = RandomStringUtils.randomAlphabetic(5);
		return genratedString;
	}

	public String randomNumber() {
		String genratedNumber = RandomStringUtils.randomNumeric(10);
		return genratedNumber;
	}

	public String randomAlphaNumeric() {
		String genratedString = RandomStringUtils.randomAlphabetic(5);
		String genratedNumber = RandomStringUtils.randomNumeric(10);
		return (genratedString + genratedNumber);
	}

}