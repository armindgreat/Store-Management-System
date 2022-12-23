import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

class Stock {

    int product_id, quantity, price;
    String product_name = new String();
    static String customer_name = new String();
    static String customer_mobile = new String();

    Scanner input = new Scanner(System.in);

    Stock() {
        product_id = 0;
        product_name = "";
        quantity = 0;
        price = 0;
    }

    Stock(int product_id, String product_name, int quantity, int pr) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.quantity = quantity;
        this.price = pr;
    }

    void AddToStock(Stock stock[]) {

        int x = 1, i3 = 0;
        while (x == 1) {
            System.out.println("Enter the product ID: ");
            int p_id = input.nextInt();

            System.out.println("Enter name of the product: ");
            input.nextLine();
            String name = input.nextLine();

            System.out.println("Enter the quantity of the product: ");
            int q = input.nextInt();

            System.out.println("Enter price per unit of the product: ");
            int p = input.nextInt();

            stock[i3] = new Stock(p_id, name, q, p);
            i3++;
            System.out.println("Do you want to enter more (1. YES / 2. NO): ");
            x = input.nextInt();
            System.out.println();
        }
    }

    void DeleteStock(Stock stock[]) {
        if (stock[0] == null) {
            System.out.println("THE STOCK IS EMPTY!!");
            return;
        }
        int check = 0, i = 0;
        System.out.println("Enter the product ID: ");
        int p = input.nextInt();
        while (stock[i] != null) {
            if (stock[i].product_id == p) {
                while (stock[i] != null) {
                    stock[i] = stock[i + 1];
                    i++;
                }
                check = 1;
                break;
            } else {
                i++;
            }
        }
        if (check == 1) {
            System.out.println("Entire stock of product ID " + p + " has been DELETED. ");
        } else {
            System.out.println("Enter a valid product ID");
        }
    }

    void UpdateStock(Stock stock[]) {
        if (stock[0] == null) {
            System.out.println("THE STOCK IS EMPTY!!");
            return;
        }
        int check = 0, i = 0;
        System.out.println("Enter the product ID: ");
        int p = input.nextInt();
        while (stock[i] != null) {
            if (stock[i].product_id == p) {

                System.out.println("Enter updated name of the product: ");
                input.nextLine();
                String name = input.nextLine();

                System.out.println("Enter updated quantity of the product: ");
                int q = input.nextInt();

                System.out.println("Enter updated price per unit of the product: ");
                int pr = input.nextInt();

                stock[i].product_name = name;
                stock[i].quantity = q;
                stock[i].price = pr;

                check = 1;
                break;
            } else {
                i++;
            }
        }

        if (check == 1) {
            System.out.println("Entire stock of product ID " + p + " has been UPDATED. ");
        } else {
            System.out.println("Enter a valid product ID");
        }
    }

    void PrintStock(Stock stock[]) {
        int cnt = 0;
        if (stock[0] == null) {
            System.out.println("THE STOCK IS EMPTY!!");
        } else {
            System.out.println();
            System.out.println(
                    "=========================================================================================");
            System.out.println("PRODUCT ID \t\t PRODUCT NAME \t\t QUANTITY \t\t PRICE");
            System.out.println(
                    "=========================================================================================");
            while (stock[cnt] != null) {
                System.out.println(stock[cnt].product_id + "\t\t\t" + stock[cnt].product_name + "\t\t\t"
                        + stock[cnt].quantity + "\t\t\t" + stock[cnt].price);
                cnt++;
            }
            System.out.println(
                    "=========================================================================================");
        }

    }

}

class Order extends Stock {

    int product_id, qty;

    Order() {
        product_id = 0;
        qty = 0;
    }

    Order(int product_id, int qty) {
        this.product_id = product_id;
        this.qty = qty;
    }

    void AddToCart(Order ord[], Stock stock[]) {
        int ch = 1, i = 0;
        label1: while (ch == 1) {
            int j = 0;

            if (stock[j] == null) {
                System.out.println("The stock is already empty! Cannot Add To Cart!!!");
                break;
            } else {
                if (i == 0) {
                    System.out.println("Enter name of the customer: ");
                    Order.customer_name = input.nextLine();
                    input.nextLine();

                    System.out.println("Enter mobile number of the customer: ");
                    Order.customer_mobile = input.nextLine();
                    if(Order.customer_mobile.length()!=10){
                        System.out.println("Invalid mobile number !");
                        return;
                    }
                    
                }
                System.out.println();
                System.out.println("Enter the product ID: ");
                int p_id = input.nextInt();

                while (stock[j] != null) { //
                    if (stock[j].product_id == p_id) {
                        break;
                    }
                    j++;

                }

                if (stock[j] == null) {
                    System.out.println("Invalid Product ID!!");
                    break label1;
                }

                System.out.println("Enter quantity of the product: ");
                int q = input.nextInt();

                if (q > stock[j].quantity) {
                    System.out.println("Out of stock!!!");
                    System.out.println("==>Quantity ordered is more than available stock.");
                    System.out.println("==>Quantity demanded: " + q);
                    System.out.println("==>Quantity available: " + stock[j].quantity);
                    System.out.println("==>Please try again!!");
                    break;
                } else {
                    stock[j].quantity = stock[j].quantity - q; // UPDATING THE STOCK
                    ord[i] = new Order(p_id, q);
                    System.out.println("Do you want to enter more (1. YES / 2. NO): ");
                    ch = input.nextInt();
                    i++;
                }
            }
        }
    }
}

class Bill extends Order {
    void PrintBill(Order ord[], Stock stock[]) {
        int j = 0, i2 = 0, total_price = 0;
        if (ord[j] == null) {
            System.out.println("There is no item in your cart!! Please Add To Cart first!!");
        } else {

            System.out.println();
            System.out.println();

            DateFormat date_obj = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
            Calendar cal_obj = Calendar.getInstance();

            System.out.println(
                    "===============================      BILL      ==========================================");
            System.out.println("CUSTOMER NAME: " + Order.customer_name);
            System.out.println("MOBILE NUMBER: " + Order.customer_mobile);
            System.out.println("DATE AND TIME: " + date_obj.format(cal_obj.getTime()));

            System.out.println(
                    "=========================================================================================");
            System.out.println("S. No. \t\t Product Name \t\t Quantity \t\t Total");
            System.out.println(
                    "=========================================================================================");
            while (ord[j] != null) {

                while (stock[i2] != null) {
                    if (stock[i2].product_id == ord[j].product_id) {
                        break;
                    }
                    i2++;
                }
                System.out.println(j + 1 + " \t\t " + stock[i2].product_name + " \t\t\t " + ord[j].qty + " \t\t\t "
                        + (stock[i2].price * ord[j].qty));
                total_price += (ord[j].qty * stock[i2].price);
                j++;
            }
            System.out.println(
                    "=========================================================================================");
            System.out.println("GRAND TOTAL: " + total_price);
            System.out.println(
                    "=========================================================================================");
            System.out.println("                   THANK YOU FOR SHOPPING!! PLEASE VISIT AGAIN :)");
            System.out.println(
                    "=========================================================================================");
        }
    }
}

class Innovative {
    public static void main(String args[]) {
        int flag = 1;
        Stock[] stock = new Stock[100];
        Order[] ord = new Order[100];
        Stock s = new Stock();
        while (flag == 1) {
            Scanner input = new Scanner(System.in);
            System.out.println("===========================GENERAL STORE MANAGEMENT SYSYEM===========================");
            System.out.println("1. Add to cart");
            System.out.println("2. Generate bill");
            System.out.println("3. Add stock");
            System.out.println("4. Delete stock");
            System.out.println("5. Update stock");
            System.out.println("6. Empty the cart");
            System.out.println("7. Show stock");
            System.out.println("8. Exit");
            System.out.println("=====================================================================================");
            System.out.print("Enter your choice: ");
            int x = 10;
            try {
                x = input.nextInt();
            } catch (Exception InputMismatch) {
                System.out.println("Please Enter valid Input (Integers).");
            }
            int choice = x;
            System.out.println("=====================================================================================");
            switch (choice) {
                case 1:
                    Order o = new Order();
                    o.AddToCart(ord, stock);
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    break;

                case 2:
                    Bill b = new Bill();
                    b.PrintBill(ord, stock);
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    break;

                case 3:

                    s.AddToStock(stock);
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    break;

                case 4:
                    s.DeleteStock(stock);
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    break;

                case 5:
                    s.UpdateStock(stock);
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    break;

                case 6:
                    int k = 0;
                    while (ord[k] != null) {
                        ord[k] = null;
                        k++;
                    }
                    System.out.println("Your cart is now empty!!");
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    break;

                case 7:
                    s.PrintStock(stock);
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    break;

                case 8:
                    flag = 0;
                    break;

                default:
                    System.out.println("Invalid choice!!");

            }
        }
    }
}
