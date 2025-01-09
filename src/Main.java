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
        int[] unAvailableSeat,availableSeat,movie = new int[1];
        int cn = 0;
        String[] movieName = new String[1];
        String[] movieType =  new String[1];
        int[] duration = new int[1];

        String [][] newHallArray;

        int countName = 1;
        int countType = 1;
        int countDuration=1;
        int available =0;
        int unavailable =0;
        boolean checkNewMovie = true,showrecord = true;
        int countMovieBooked = 0;
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
        newHallArray = new String[numberHall][numberSeat];
        availableSeat=new int[numberHall];
        unAvailableSeat=new int[numberHall];
        for (int i = 0; i < numberHall; i++) {
            for (int j = 0; j < numberSeat; j++) {
                newHallArray[i][j] = "+";
                available++;
            }
            availableSeat[i]=available;
            unAvailableSeat[i] = unavailable;
            available = 0;
        }
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
                            // Resize arrays if needed
                        if (cn >= movieName.length) {
                                movieName = Arrays.copyOf(movieName, cn+1);
                                movieType = Arrays.copyOf(movieType, cn+1);
                                duration = Arrays.copyOf(duration, cn+1);
                            }
                            // Input movie details
                            if(cn>=numberHall){
                                System.out.println(RED+"Unavailable hall to show movie!"+RESET);
                                System.out.print("Please press any key to continue!");
                                scanner.nextLine();
                                break;
                            }
                            System.out.print("Enter Movie Name: ");
                            movieName[cn] = scanner.nextLine();

                            System.out.print("Enter Movie Type: ");
                            movieType[cn] = scanner.nextLine();

                            System.out.print("Enter Duration (min): ");
                            duration[cn] = Integer.parseInt(scanner.nextLine());

                        System.out.println("Movie "+BLUE+movieName[cn] +RESET+" will show in hall #"+(cn+1)+"");
                            cn++; // Increment counter
                        // Ask to continue
                            System.out.print("Do you want to continue? (Y/N): ");
                            String continueString = scanner.nextLine();

                            if (continueString.equalsIgnoreCase("Y")) {
                                checkAgain = true;
                            } else if (continueString.equalsIgnoreCase("N")) {
                                checkAgain = false;
                            } else {
                                System.out.println("Please enter a valid option (Y or N)");
                                checkAgain = true;
                            }
                    } while (checkAgain);
                    break;
                case 2:
                    do {
                        Table t = new Table(8, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
                        t.setColumnWidth(0, 10, 26);
                        t.setColumnWidth(1, 20, 26);
                        t.setColumnWidth(2, 30, 26);
                        t.setColumnWidth(3, 20, 26);
                        t.setColumnWidth(4, 30, 26);
                        t.setColumnWidth(5, 20, 26);
                        t.setColumnWidth(6, 30, 26);
                        t.setColumnWidth(7, 30, 26);


                        t.addCell("Display All Movies ", numberStyle, (8));
                        t.addCell(GREEN+" ID"+RESET, numberStyle);
                        t.addCell(GREEN+"Movie"+RESET, numberStyle);
                        t.addCell(GREEN+"Type"+RESET, numberStyle);
                        t.addCell(GREEN+"Duration"+RESET, numberStyle);
                        t.addCell(GREEN+"Hall"+RESET, numberStyle);
                        t.addCell(GREEN+"Seat"+RESET, numberStyle);
                        t.addCell(GREEN+"AvailableSeat"+RESET, numberStyle);
                        t.addCell(RED+"Unavailable Seat"+RESET, numberStyle);

                        for (int i = 0; i < movieName.length; i++) {
                            t.addCell(String.valueOf((i+1)), numberStyle);
                            t.addCell(BLUE + String.valueOf(movieName[i]), numberStyle);
                            t.addCell( String.valueOf(movieType[i]), numberStyle);
                            t.addCell(String.valueOf(duration[i]+" min") + RESET, numberStyle);
                            t.addCell(String.valueOf(i+1) + RESET, numberStyle);
                            t.addCell(String.valueOf(numberSeat) + RESET, numberStyle);
                            t.addCell(String.valueOf(availableSeat[i]) + RESET, numberStyle);
                            t.addCell(String.valueOf(unAvailableSeat[i]) + RESET, numberStyle);
                        }
                        System.out.println(t.render());
                        System.out.println("1. Detail Movie \t 2. First \t 3. Next \t 4. Previous \t 5. Exit");
                        String optionString = scanner.nextLine();
                        switch (optionString) {
                            case "1":
                                // New code
                                System.out.print("Enter Movie ID: ");
                                movieID = Integer.parseInt(scanner.nextLine());
                                Table t1 = new Table(15, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
                                for (int i = 0; i < 15; i++) {
                                    t1.setColumnWidth(i, 10, 20);
                                }
                                t1.addCell("SCREEN HALL #" + (movieID), numberStyle, (15));
                                for (int i = 0; i < newHallArray[movieID - 1].length; i++) {
                                    if (newHallArray[movieID - 1][i].equals("+")) {
                                        t1.addCell(GREEN + "( " + newHallArray[movieID - 1][i] + " ) " + (i + 1) + RESET, numberStyle);
                                    } else {
                                        t1.addCell(RED + "( " + newHallArray[movieID - 1][i] + " ) " + (i + 1) + RESET, numberStyle);
                                    }
                                }
                                System.out.println(t1.render());
                                System.out.println("1. Booking Ticket\t\t2. Back");
                                String optionString2 = scanner.nextLine();
                                switch (optionString2) {
                                    case "1":
                                        System.out.print("Choose seat that you want to booking(e.g:1,2,3,4): ");
                                        String bookingID = scanner.nextLine();
                                        String[] parts = bookingID.split(",");
                                        for (int i = 0; i < parts.length; i++) {
                                            int seat = Integer.parseInt(parts[i]);
                                            newHallArray[movieID - 1][seat - 1] = "-";
                                            unAvailableSeat[movieID - 1]++;
                                            availableSeat[movieID - 1]--;
                                        }
                                        checkAgain = true;
                                        for (int i = 0; i < movie.length; i++) {
                                            if(movieID != movie[i]) {
                                                movie = Arrays.copyOf(movie, countMovieBooked + 1);
                                                movie[countMovieBooked] = movieID;
                                                countMovieBooked++;
                                            }
                                        }


                                        break;
                                    case "2":
                                        checkAgain = true;
                                        break;
                                    default:
                                        checkAgain = false;

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
                    for (int i = 0; i < movie.length; i++) {
                        System.out.println("Hall: #"+(i+1));
                        System.out.println("\t"+movieName[movie[i]-1]);
                        System.out.print("Seat Booked: " );
                        boolean firstSeat = true;
                        for (int j = 0; j < newHallArray[movie[i] - 1].length; j++) {
                            if (newHallArray[movie[i] - 1][j].equals("-")) {
                                if (!firstSeat) {
                                    System.out.print(",");
                                }
                                System.out.print(j + 1);
                                firstSeat = false;
                            }
                        }
                        System.out.println("\n-----------------------------");

                    }


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