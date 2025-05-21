import java.util.Map;
import java.util.HashMap;

public class ExtensionService {

    private final Map<String, String> extensionRequests = new HashMap<>();

    public boolean requestExtension(String studentId, String taskId, String reason) {
        if (isBlank(studentId) || isBlank(taskId) || isBlank(reason)) {
            return false;
        }
        String key = studentId + ":" + taskId;
        extensionRequests.put(key, reason.trim());
        return true;
    }

    public String getExtensionReason(String studentId, String taskId) {
        return extensionRequests.getOrDefault(studentId + ":" + taskId, null);
    }

    private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }
}
