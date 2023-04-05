public class Main {
    public static void main(String[] args) {
        FizzBuzzGame game = new FizzBuzzGame();
        game.play(1, 100);
    }

    static class FizzBuzzGame {
        private FizzBuzzRule[] rules;

        public FizzBuzzGame() {
            rules = new FizzBuzzRule[] {
                new FizzBuzzRule(3, "Fizz"),
                new FizzBuzzRule(5, "Buzz")
            };
        }

        public void play(int start, int end) {
            for (int i = start; i <= end; i++) {
                System.out.println((generateOutput(i)));
            }
        }

        private String generateOutput(int number) {
            StringBuilder output = new StringBuilder();

            for (FizzBuzzRule rule : rules) {
                if (rule.matches(number)) {
                    output.append(rule.getOutput());
                }
            }

            return output.length() > 0 ? output.toString() : String.valueOf(number);
        }
    }

    static class FizzBuzzRule {
        private int divisor;
        private String output;

        public FizzBuzzRule(int divisor, String output) {
            this.divisor = divisor;
            this.output = output;
        }

        public boolean matches(int number) {
            return number % divisor == 0;
        }

        public String getOutput() {
            return output;
        }
    }
}