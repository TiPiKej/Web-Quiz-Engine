import java.util.*;

class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        final int N = scanner.nextInt();
        final Queue[] queries = new Queue[] {new Queue(), new Queue()};

        for (int i = 0; i < N; i++) {
            int id = scanner.nextInt();
            int indicator = scanner.nextInt();

            if (queries[0].getSumOfIndicators() <= queries[1].getSumOfIndicators()) {
                queries[0].addTask(id, indicator);
            } else {
                queries[1].addTask(id, indicator);
            }
        }

        scanner.close();

        for (Queue que : queries) {
            System.out.println(que.toString());
        }
    }
}

class Queue {
    private final List<Integer> ids;
    private int sumOfIndicators = 0;

    public Queue() {
        this.ids = new ArrayList<>();
    }

    public void addTask(int id, int indicator) {
        ids.add(id);
        sumOfIndicators += indicator;
    }

    public int getSumOfIndicators() {
        return sumOfIndicators;
    }

    @Override
    public String toString() {
        final StringBuilder ret = new StringBuilder();

        for (Integer id : ids) {
            ret.append(id + " ");
        }

        return ret.toString();
    }
}