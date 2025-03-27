

public class CategoryTest {
    public static void main(String[] args) {
        Category javaCategory = new Category("programming", "java");
        System.out.println(javaCategory);

        Category rustCategory = new Category("programming", "rust");
        System.out.println(rustCategory);

        if (javaCategory.equals(rustCategory)) {
            System.out.println("Java is Rust");
        } else {
            System.out.println("Java is not Rust");
        }

        if (javaCategory.equals(new Category("programming", "java"))) {
            System.out.println("Java is Java");
        }

        // Bad category, throws IllegalArgumentException
        //Category badCategory = Category.parse("foo|bar");

        Category goodCategory = Category.parse("foo/bar");
        System.out.println(goodCategory);
    }
}