import java.util.HashMap;
import java.util.Map;

public class ClaudeTest {

    private static Map<String, String> users = new HashMap<>();

    public static void main(String[] args) {
        users.put("admin", "admin123");
        users.put("user", "password");

        String username = "admin";
        String password = "admin123 ";

        if (login(username, password)) {
            System.out.println("Login successful! Welcome " + username);
        } else {
            System.out.println("Login failed. Invalid credentials.");
        }
    }

    public static boolean login(String username, String password) {
        if (!users.containsKey(username)) {
            return false;
        }

        // ❌ 漏洞点：未进行输入标准化（trim），导致多余空格造成登录失败
        String storedPassword = users.get(username);

        // ❌ 漏洞点：使用明文密码比较，不安全
        return password.equals(storedPassword);
    }

    // ❌ 安全风险：暴露了所有用户及密码（不应这样暴露敏感数据）
    public static void printAllUsers() {
        for (Map.Entry<String, String> entry : users.entrySet()) {
            System.out.println("User: " + entry.getKey() + ", Password: " + entry.getValue());
        }
    }
}
