package cards;

import java.util.Scanner;

public class Property implements GetCard {
    String name;
    int money;
    Color color;
    Color[] availableColors = new Color[10];
    int[] rent;

    public Property(Color c, String name) {
        color = c;
        availableColors[0] = c;
        this.name = name;
    }

    public Property(Color c1, Color c2, String name) {
        this.availableColors[0] = c1;
        this.availableColors[1] = c2;
        this.name = name;
    }

    public Property(Color c1, Color c2, Color c3, Color c4, Color c5, Color c6, Color c7,
                    Color c8, Color c9, Color c10, String name) {
        this.availableColors = new Color[]{c1, c2, c3, c4, c5, c6, c7, c8, c9, c10};
        this.name = name;
    }

    public void use() {
        boolean noExist = true;
        Scanner scanner = new Scanner(System.in);
        if (availableColors.length != 1) {
            while (noExist) {
                System.out.println("Please enter the color you choose:\n");
                for (int i = 0; i < availableColors.length; i++) {
                    System.out.println(availableColors[i] + " ");
                }
                try {
                    this.color = Color.valueOf(scanner.nextLine());
                } catch (IllegalArgumentException e) {
                    System.out.println("Please enter a valid character");
                }
                for (int i = 0; i < availableColors.length; i++) {
                    if (availableColors[i] == this.color) {
                        noExist = false;
                    }
                }
                if (noExist) {
                    System.out.println("Please enter a color that matches the criteria\n");
                }
            }
        }

        switch (this.color) {
            case RED -> {
                this.money = 3;
                this.rent = new int[]{2, 3, 6};
                break;
            }
            case Pink -> {
                this.money = 2;
                this.rent = new int[]{1, 2, 4};
                break;
            }
            case Black -> {
                this.money = 2;
                this.rent = new int[]{1, 2, 3, 4};
                break;
            }
            case Brown -> {
                this.money = 1;
                this.rent = new int[]{1, 2};
                break;
            }
            case Orange -> {
                this.money = 2;
                this.rent = new int[]{1, 3, 5};
                break;
            }
            case Yellow -> {
                this.money = 3;
                this.rent = new int[]{2, 4, 6};
                break;
            }
            case DarkBlue -> {
                this.money = 4;
                this.rent = new int[]{3, 8};
            }
            case DarkGreen -> {
                this.money = 4;
                this.rent = new int[]{2, 4, 7};
                break;
            }
            case LightBlue -> {
                this.money = 1;
                this.rent = new int[]{1, 2, 3};
                break;
            }
            case LightGreen -> {
                this.money = 2;
                this.rent = new int[]{1, 2};
                break;
            }
        }
    }

    public Color getColor() {
        return this.color;
    }

    public int getMoney() {
        return money;
    }

    public String getName() {
        return name;
    }
}
