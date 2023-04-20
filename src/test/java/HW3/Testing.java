package HW3;

public enum Testing {
    TestSuccess("TEST PASSED ---> "),
    TestFail("TEST FAILED ---> ");

    private String testing;

    Testing(String testing) {
        this.testing = testing;
    }

    public String toString(String type) {
        return this.testing.concat(type);
    }
}
