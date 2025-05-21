import org.junit.Assert;
import org.junit.Test;

public class ExtensionServiceTest {

	public void testValidExtensionRequest() {
	    ExtensionService service = new ExtensionService();
	    boolean result = service.requestExtension("s12345", "8.1P", "Medical reason");
	    Assert.assertFalse(result); // ❌ INTENTIONAL FAIL 
	}

    @Test
    public void testValidRequestStoresReason() {
        ExtensionService service = new ExtensionService();
        service.requestExtension("s123", "8.1P", "Illness");
        String reason = service.getExtensionReason("s123", "8.1P");
        Assert.assertEquals("Illness", reason);
    }

    @Test
    public void testEmptyReasonRejected() {
        ExtensionService service = new ExtensionService();
        boolean result = service.requestExtension("s123", "8.1P", "");
        Assert.assertFalse(result);
    }

    @Test
    public void testNullReasonRejected() {
        ExtensionService service = new ExtensionService();
        boolean result = service.requestExtension("s123", "8.1P", null);
        Assert.assertFalse(result);
    }

    @Test
    public void testOverwritesPreviousRequest() {
        ExtensionService service = new ExtensionService();
        service.requestExtension("s123", "8.1P", "Medical");
        service.requestExtension("s123", "8.1P", "Family emergency");
        String reason = service.getExtensionReason("s123", "8.1P");
        Assert.assertEquals("Family emergency", reason);
    }
}
