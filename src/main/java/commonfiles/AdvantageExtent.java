package commonfiles;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class AdvantageExtent {
    private static ExtentReports extent;
    private static ExtentTest test;

    public static ExtentReports getInstance() {
        if (extent == null) {
            extent = createInstance();
        }
        return extent;
    }

    private static ExtentReports createInstance() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extent-report.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        return extent;
    }

    public static ExtentTest createTest(String testName) {
        test = extent.createTest(testName);
        return test;
    }
}
