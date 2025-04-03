package ca.mcmaster.se2aa4.mazerunner;

public class Path {
    private final String path;

    public Path(String path) {
        this.path = path;
    }

    // Method to display path in canonical form

    public String getCanonical() {
        StringBuilder canonical = new StringBuilder();

        for (int i = 0; i < this.path.length(); i++) {
            canonical.append(this.path.charAt(i));
            if (i < this.path.length() - 1 && this.path.charAt(i + 1) != this.path.charAt(i)) {
                canonical.append(" ");
            }
        }
        return canonical.toString();
    }

    // Method to display path in factorized

    public String getFactorized() {
        StringBuilder factorized = new StringBuilder();
        int i = 0;

        while (i < this.path.length()) {
            char currentChar = this.path.charAt(i);
            int count = 1;

            while (i < this.path.length() - 1 && this.path.charAt(i + 1) == this.path.charAt(i)) {
                count++;
                i++;
            }

            if (count > 1) {
                factorized.append(count);
                factorized.append(currentChar);
            } else {
                factorized.append(currentChar);
            }
            factorized.append(" ");
            i++;
        }

        return factorized.toString();
    }
}
