import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String numberString;
        boolean checkAgain = false,checkseat = false;
        int numberHall,numberSeat,option = 0;
        String[][][] hallArray;
        int[] availableSeate,seatBooked = new int[1];
        int cn = 0;
        String[] movieName = new String[1];
        String[] movieType =  new String[1];
        int[] duration = new int[1];


        int countName = 1;
        int countType = 1;
        int countDuration=1;
        boolean checkNewMovie = true,showrecord = true;
        int countSeatBooked = 0;
        int movieID = 0;

        // color
        String RESET = "\u001B[0m";
        String RED = "\u001B[31m";
        String GREEN = "\u001B[32m";
        String YELLOW = "\u001B[33m";
        String BLUE="\u001B[34m";
        CellStyle numberStyle = new CellStyle(CellStyle.HorizontalAlign.center);

        // set up cinema
        System.out.println("\t================= Set Up Hall In Cinema =================");
        do {
            System.out.print("\tEnter number of Hall in Cinema: ");
            numberString = scanner.nextLine();
            if(!numberString.matches("[0-9]+")){
                System.out.println(RED+"\tNumber of hall is allow only number!"+RESET);
                checkAgain = true;
            }else{
                checkAgain = false;
            }
        }while (checkAgain);
        numberHall = Integer.parseInt(numberString);
        do{
        System.out.print("\tEnter number of seat in each Hall: ");
        numberString = scanner.nextLine();
        if(!numberString.matches("[0-9]+")){
            System.out.println(RED+"\tNumber of seat is allow only number!"+RESET);
            checkAgain = true;
        }else {
            checkAgain = false;
        }
        }while (checkAgain);
        numberSeat = Integer.parseInt(numberString);
     //   Menu main Feature
        hallArray = new String[1][numberHall][numberSeat];
        do {
            System.out.println("\n=====================================================");
            System.out.println(GREEN + "\t\t\t Cinema Management System " + RESET);
            System.out.println("=====================================================");
            System.out.println(BLUE + "1, Insert Movie");
            System.out.println("2, Check & Booking Movie");
            System.out.println("3, Check Ticket");
            System.out.println("4, Reset Hall");
            System.out.println("5, Set row to show record");
            System.out.println("6, Exit" + RESET);
            System.out.println("--------------------------------------------------------");
            do {
                System.out.print("please input your choice(1-6): ");
                String optionString = scanner.nextLine();
                if (!optionString.matches("[1-6]")) {
                    System.out.println(RED + "\tInvalid option!" + RESET);
                    checkAgain = true;
                } else {
                    option = Integer.parseInt(optionString);
                    checkAgain = false;
                }
            } while (checkAgain);

            switch (option) {
                case 1:
                    do {
                        if(checkNewMovie) {
                            hallArray = Arrays.copyOf(hallArray, cn+1);
                            hallArray[cn] = new String[numberHall][numberSeat];
                                for (int j = 0; j < numberHall; j++) {
                                    for (int k = 0; k < numberSeat; k++) {
                                        hallArray[cn][j][k] = "+";
                                    }
                            }
                        }
                            // Resize arrays if needed
                        if (cn >= movieName.length) {
                                movieName = Arrays.copyOf(movieName, cn+1);
                                movieType = Arrays.copyOf(movieType, cn+1);
                                duration = Arrays.copyOf(duration, cn+1);
                            }
                            // Input movie details
                            System.out.print("Enter Movie Name: ");
                            movieName[cn] = scanner.nextLine();

                            System.out.print("Enter Movie Type: ");
                            movieType[cn] = scanner.nextLine();

                            System.out.print("Enter Duration (min): ");
                            duration[cn] = Integer.parseInt(scanner.nextLine());

                            cn++; // Increment counter
                            // Ask to continue
                            System.out.print("Do you want to continue? (Y/N): ");
                            String continueString = scanner.nextLine();

                            if (continueString.equalsIgnoreCase("Y")) {
                                checkAgain = true;
                                checkNewMovie = true;
                            } else if (continueString.equalsIgnoreCase("N")) {
                                checkNewMovie = false;
                                checkAgain = false;
                            } else {
                                System.out.println("Please enter a valid option (Y or N)");
                                checkAgain = true;
                            }
                    } while (checkAgain);
                    break;
                case 2:
                    do {
                        int hallNum = 0;
                        Table t = new Table(4, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
                        t.setColumnWidth(0, 10, 26);
                        t.setColumnWidth(1, 20, 26);
                        t.setColumnWidth(2, 30, 26);
                        t.setColumnWidth(3, 20, 26);

                        t.addCell("Display All Movies ", numberStyle, (4));
                        t.addCell(GREEN+" ID"+RESET, numberStyle);
                        t.addCell(GREEN+"Name"+RESET, numberStyle);
                        t.addCell(GREEN+"Type"+RESET, numberStyle);
                        t.addCell(RED+"Duration"+RESET, numberStyle);
//
                        for (int i = 0; i < movieName.length; i++) {
                            t.addCell(String.valueOf((i + 1)), numberStyle);
                            t.addCell(BLUE + String.valueOf(movieName[i]), numberStyle);
                            t.addCell( String.valueOf(movieType[i]), numberStyle);
                            t.addCell(String.valueOf(duration[i]+" min") + RESET, numberStyle);
                        }
                        System.out.println(t.render());
                        System.out.println("1. Booking Movie \t 2. First \t 3. Next \t 4. Previous \t 5. Exit");
                        String optionString = scanner.nextLine();
                        switch (optionString) {
                            case "1":
                                    System.out.print("Enter Movie ID: ");
                                     movieID = Integer.parseInt(scanner.nextLine());
                                    for (int j = 0; j < hallNum+1; j++) {
                                        int n = 0;
                                        if( hallNum == (hallArray[movieID - 1].length)){
                                            System.out.println("Movie id #"+movieID+" is not available to booking!");
                                            checkAgain = true;
                                            break;
                                        }
                                        for (int i = 0; i < hallArray[movieID - 1][hallNum].length; i++) {
                                            if (hallArray[movieID - 1][hallNum][i].equals("-")) {
                                                n++;
                                            }
                                                if(hallArray[movieID - 1][hallNum][i].equals("-")) {
                                                    for (int k =1 ;k<hallArray[movieID - 1][hallNum].length;k++) {
                                                        if (hallArray[movieID - 1][hallNum][i].equals(hallArray[movieID - 1][hallNum][k])) {
                                                            showrecord = false;
                                                        } else {
                                                            showrecord = true;
                                                        }
                                                    }
                                                }else{
                                                    showrecord = true;
                                                }

                                            if (numberSeat == n) {
                                                hallNum++;
                                            }
                                        }
                                        Table t1 = new Table(15, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);

                                        for (int i = 0; i < 15; i++) {
                                            t1.setColumnWidth(i, 10, 20);
                                        }

                                        for (int i = 0; i < hallArray[movieID - 1][hallNum].length; i++) {
                                            if(showrecord){
                                                if(i==0){
                                                    t1.addCell("SCREEN HALL #"+(j+1), numberStyle, (15));
                                                }
                                                if(hallArray[movieID-1][hallNum][i].equals("+")) {
                                                    t1.addCell(GREEN+"( "+hallArray[movieID - 1][hallNum][i]+" ) "+(i + 1)+RESET, numberStyle);
                                                }else{
                                                    t1.addCell(RED+"( "+hallArray[movieID - 1][hallNum][i]+" ) "+(i + 1)+RESET, numberStyle);
                                                }
                                            }
                                        }
                                        System.out.println(t1.render());
                                    }
                                    if(!checkAgain){
                                        do {
                                            System.out.print("\nPlease choose Seat that you want to booking: ");
                                            int bookingID = Integer.parseInt(scanner.nextLine());
                                            hallArray[movieID - 1][hallNum][bookingID - 1] = "-";
                                            seatBooked = Arrays.copyOf(seatBooked,countSeatBooked+1);
                                            seatBooked[countSeatBooked] = bookingID;
                                            System.out.print("Do you want to continue? (Y/N): ");
                                            String condition = scanner.nextLine();
                                            if (condition.equalsIgnoreCase("Y")) {
                                                countSeatBooked++;
                                                checkAgain = true;
                                            }else{
                                                checkAgain = false;
                                            }
                                        }while (checkAgain);
                                    }
                                break;
                            case "2": break;
                            case "3": break;
                            case "4": break;
                            case "5":
                                checkAgain = false;
                                break;
                            default:
                                System.out.println("Invalid option!");
                                checkAgain = true;
                                break;
                        }
                    }while (checkAgain);
                    break;
                case 3:
                    System.out.println("==================================");
                    System.out.println("Your ticket has been booked!");
                    System.out.println("==================================");
                    System.out.println("id\t\tMovie name\t\ttype\t\tduration\t\tseat");
                    System.out.println("1\t\t"+movieName[movieID-1]+"\t\t"+movieType[movieID-1]+"\t\t"+duration[movieID-1]+"\t\t"+Arrays.toString(seatBooked));
                    break;
                case 4:
                    hallArray = new String[movieName.length][numberHall][numberSeat];
                    for (int i = 0; i < movieName.length; i++) {
                        for (int j = 0; j < numberHall; j++) {
                            for (int k = 0; k < numberSeat; k++) {
                                hallArray[i][j][k] = "+";
                            }
                        }
                        System.out.println("All Hall is already reset!");
                    }
                    break;
                case 5:
                    break;
                case 6:
                    System.out.println("Good bye :)");
                    System.exit(0);
                    break;
                default:
                    System.out.println(RED + "Invalid!" + RESET);
                    break;
            }
        }while (true);

    }
}