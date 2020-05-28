package exercise1b;

public class AdvancedTriangle {
    public static void main(String[] args) {
        drawTriangle(8);
    }

    public static void drawTriangle(int N){
        for (int i = 0; i < N; i++){    // rows
            System.out.print("|");

            if (i + 1 == N) {
                for (int x = 1; x < N; x++) {
                    System.out.print("_");
                }
            }

            for (int j = 0; j <= i; j++){    //columns
                if (j < i && i + 1 != N) {
                    System.out.print(" ");
                }

                if (i == j) {
                    System.out.print("\\");
                }
            }
            System.out.println();
        }


    }

}
