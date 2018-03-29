/**
 * Created by mayankthirani on 3/29/18.
 */
@TesterInfo(
        priority = TesterInfo.Priority.HIGH,
        createdBy = "Mayank Thirani"
)
public class TestExample {

    @TestingAnnotations
    void testA() {
        if (true)
            throw new RuntimeException ("This test always failed");
    }

    @TestingAnnotations(enabled = false)
    void testB() {
        if (false)
            throw new RuntimeException ("This test always passed");
    }

    @TestingAnnotations(enabled = true)
    void testC() {
        if (10 > 1) {
            // do nothing, this test always passed.
        }
    }

}