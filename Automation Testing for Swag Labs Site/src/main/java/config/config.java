package config;

public class config {
    public static final String base_url = "https://www.saucedemo.com";
    public static final String inventory_url = "https://www.saucedemo.com/inventory.html";
    public static final String testFolderPath = "D:\\Digital Egypt Pioneers Initiative\\Content\\Tech\\My_Project\\My_Test\\src\\main\\java\\test_data\\";

    //Enum files
    public enum JsonFiles {
        AUTH_TEST_DATA("authTestData.json");

        private final String fileName;

        JsonFiles(String fileName) {
            this.fileName = fileName;
        }

        public String getFileName() {
            return testFolderPath + fileName;
        }
    }
}

